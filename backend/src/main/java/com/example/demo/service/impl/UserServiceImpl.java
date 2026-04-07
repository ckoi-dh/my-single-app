package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.enums.UserRoleEnum;
import com.example.demo.common.enums.UserStatusEnum;
import com.example.demo.common.mapstruct.UserMapstruct;
import com.example.demo.domain.dto.req.PageReq;
import com.example.demo.domain.dto.req.UserCreateReq;
import com.example.demo.domain.dto.req.UserUpdateReq;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.domain.vo.UserVO;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** User service implementation. 用户服务实现。 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

  private final UserMapstruct userMapstruct;
  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public UserVO create(UserCreateReq req) {
    // Check username exists
    // 检查用户名是否存在
    if (existsByUsername(req.getUsername())) {
      throw new BusinessException("Username already exists");
    }

    // Check email exists if provided
    // 检查邮箱是否存在（如果提供）
    if (StringUtils.isNotBlank(req.getEmail()) && existsByEmail(req.getEmail())) {
      throw new BusinessException("Email already exists");
    }

    // Validate role type
    // 验证角色类型
    if (UserRoleEnum.getByType(req.getRoleType()) == null) {
      throw new BusinessException("Invalid role type");
    }

    // Convert request to entity and encode password
    // 将请求转换为实体并加密密码
    UserEntity entity = userMapstruct.toEntity(req);
    entity.setPassword(passwordEncoder.encode(req.getPassword()));
    entity.setStatus(UserStatusEnum.ACTIVE.getStatus());

    // Save user
    // 保存用户
    save(entity);
    log.info("Created user: {}", entity.getUsername());

    return userMapstruct.toVO(entity);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public UserVO update(UserUpdateReq req) {
    // Get existing user
    // 获取现有用户
    UserEntity entity = getByIdInternal(req.getId());
    if (entity == null) {
      throw new BusinessException("User not found");
    }

    // Check email if changed
    // 检查邮箱是否变更
    if (StringUtils.isNotBlank(req.getEmail()) && !req.getEmail().equals(entity.getEmail())) {
      if (existsByEmail(req.getEmail())) {
        throw new BusinessException("Email already exists");
      }
    }

    // Validate role type if changed
    // 验证角色类型（如果变更）
    if (req.getRoleType() != null && UserRoleEnum.getByType(req.getRoleType()) == null) {
      throw new BusinessException("Invalid role type");
    }

    // Update entity
    // 更新实体
    userMapstruct.updateEntity(entity, req);
    updateById(entity);
    log.info("Updated user: {}", entity.getUsername());

    return userMapstruct.toVO(entity);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void delete(Long id) {
    // Get existing user
    // 获取现有用户
    UserEntity entity = getByIdInternal(id);
    if (entity == null) {
      throw new BusinessException("User not found");
    }

    // Soft delete
    // 软删除
    removeById(id);
    log.info("Deleted user: {}", entity.getUsername());
  }

  @Override
  public UserVO getById(Long id) {
    UserEntity entity = getByIdInternal(id);
    if (entity == null) {
      throw new BusinessException("User not found");
    }
    return userMapstruct.toVO(entity);
  }

  @Override
  public Page<UserVO> getPage(PageReq req) {
    // Create page object
    // 创建分页对象
    Page<UserEntity> page = new Page<>(req.getCurrent(), req.getSize());

    // Build query wrapper
    // 构建查询包装器
    LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.orderByDesc(UserEntity::getCreateTime);

    // Query page
    // 查询分页
    Page<UserEntity> entityPage = page(page, wrapper);

    // Convert to VO page
    // 转换为VO分页
    Page<UserVO> voPage =
        new Page<>(entityPage.getCurrent(), entityPage.getSize(), entityPage.getTotal());
    voPage.setRecords(
        entityPage.getRecords().stream()
            .map(userMapstruct::toVO)
            .collect(java.util.stream.Collectors.toList()));

    return voPage;
  }

  @Override
  public UserVO getByUsername(String username) {
    LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(UserEntity::getUsername, username);
    UserEntity entity = getOne(wrapper);
    return entity != null ? userMapstruct.toVO(entity) : null;
  }

  @Override
  public boolean existsByUsername(String username) {
    LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(UserEntity::getUsername, username);
    return count(wrapper) > 0;
  }

  @Override
  public boolean existsByEmail(String email) {
    LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(UserEntity::getEmail, email);
    return count(wrapper) > 0;
  }

  /**
   * Get user entity by ID internally. 内部根据ID获取用户实体。
   *
   * @param id User ID
   * @return UserEntity
   */
  private UserEntity getByIdInternal(Long id) {
    return this.baseMapper.selectById(id);
  }
}
