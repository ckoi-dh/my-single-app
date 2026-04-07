package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.domain.dto.req.InteractCreateReq;
import com.example.demo.domain.dto.req.PageReq;
import com.example.demo.domain.entity.InteractEntity;
import com.example.demo.domain.vo.InteractVO;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.InteractMapper;
import com.example.demo.service.InteractService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Interact service implementation. 互动服务实现。 */
@Slf4j
@Service
@RequiredArgsConstructor
public class InteractServiceImpl implements InteractService {

  private final InteractMapper interactMapper;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public InteractVO create(InteractCreateReq req) {
    if (exists(getCurrentUserId(), req.getTargetId(), req.getTargetType())) {
      throw new BusinessException("Already interacted");
    }

    InteractEntity entity = new InteractEntity();
    entity.setUserId(getCurrentUserId());
    entity.setTargetId(req.getTargetId());
    entity.setTargetType(req.getTargetType());

    interactMapper.insert(entity);
    log.info(
        "Created interact: user={}, target={}, type={}",
        entity.getUserId(),
        entity.getTargetId(),
        entity.getTargetType());

    return getById(entity.getId());
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void delete(Long id) {
    InteractEntity entity = interactMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Interact not found");
    }

    interactMapper.deleteById(id);
    log.info("Deleted interact: id={}", id);
  }

  @Override
  public InteractVO getById(Long id) {
    InteractEntity entity = interactMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Interact not found");
    }
    return convertToVO(entity);
  }

  @Override
  public Page<InteractVO> getPage(PageReq req) {
    Page<InteractEntity> page = new Page<>(req.getCurrent(), req.getSize());

    LambdaQueryWrapper<InteractEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.orderByDesc(InteractEntity::getCreateTime);

    Page<InteractEntity> entityPage = interactMapper.selectPage(page, wrapper);
    return convertToVOPage(entityPage);
  }

  @Override
  public List<InteractVO> getByUserId(Long userId) {
    LambdaQueryWrapper<InteractEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(InteractEntity::getUserId, userId);
    wrapper.orderByDesc(InteractEntity::getCreateTime);

    List<InteractEntity> entities = interactMapper.selectList(wrapper);
    return entities.stream().map(this::convertToVO).collect(Collectors.toList());
  }

  @Override
  public List<InteractVO> getByTarget(Long targetId, Integer targetType) {
    LambdaQueryWrapper<InteractEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(InteractEntity::getTargetId, targetId);
    wrapper.eq(InteractEntity::getTargetType, targetType);
    wrapper.orderByDesc(InteractEntity::getCreateTime);

    List<InteractEntity> entities = interactMapper.selectList(wrapper);
    return entities.stream().map(this::convertToVO).collect(Collectors.toList());
  }

  @Override
  public boolean exists(Long userId, Long targetId, Integer targetType) {
    LambdaQueryWrapper<InteractEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(InteractEntity::getUserId, userId);
    wrapper.eq(InteractEntity::getTargetId, targetId);
    wrapper.eq(InteractEntity::getTargetType, targetType);

    return interactMapper.selectCount(wrapper) > 0;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public InteractVO toggle(InteractCreateReq req) {
    Long userId = getCurrentUserId();
    LambdaQueryWrapper<InteractEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(InteractEntity::getUserId, userId);
    wrapper.eq(InteractEntity::getTargetId, req.getTargetId());
    wrapper.eq(InteractEntity::getTargetType, req.getTargetType());

    InteractEntity existing = interactMapper.selectOne(wrapper);
    if (existing != null) {
      delete(existing.getId());
      return null;
    } else {
      return create(req);
    }
  }

  private InteractVO convertToVO(InteractEntity entity) {
    InteractVO vo = new InteractVO();
    vo.setId(entity.getId());
    vo.setUserId(entity.getUserId());
    vo.setTargetId(entity.getTargetId());
    vo.setTargetType(entity.getTargetType());
    vo.setCreateTime(entity.getCreateTime());
    vo.setUpdateTime(entity.getUpdateTime());
    return vo;
  }

  private Page<InteractVO> convertToVOPage(Page<InteractEntity> entityPage) {
    Page<InteractVO> voPage =
        new Page<>(entityPage.getCurrent(), entityPage.getSize(), entityPage.getTotal());
    voPage.setRecords(
        entityPage.getRecords().stream().map(this::convertToVO).collect(Collectors.toList()));
    return voPage;
  }

  private Long getCurrentUserId() {
    return 1L; // Default to admin
  }
}
