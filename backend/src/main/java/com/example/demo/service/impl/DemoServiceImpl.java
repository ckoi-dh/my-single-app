package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.ErrorCode;
import com.example.demo.common.mapstruct.DemoMapstruct;
import com.example.demo.domain.dto.req.DemoCreateReq;
import com.example.demo.domain.dto.req.DemoUpdateReq;
import com.example.demo.domain.entity.DemoEntity;
import com.example.demo.domain.vo.DemoVO;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.DemoMapper;
import com.example.demo.service.DemoService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Demo service implementation. Demo 服务实现类。 */
@Service
@RequiredArgsConstructor
public class DemoServiceImpl implements DemoService {

  private final DemoMapper demoMapper;
  private final DemoMapstruct demoMapstruct;

  @Override
  public DemoVO create(DemoCreateReq req) {
    // Check if name already exists
    // 检查名称是否已存在
    LambdaQueryWrapper<DemoEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(DemoEntity::getName, req.getName());
    if (demoMapper.selectCount(wrapper) > 0) {
      throw new BusinessException("Name already exists");
    }

    // Convert DTO to entity and save
    // 转换 DTO 为实体并保存
    DemoEntity demoEntity = demoMapstruct.createReqToEntity(req);
    demoMapper.insert(demoEntity);

    // Return VO
    // 返回 VO
    return demoMapstruct.entityToVO(demoEntity);
  }

  @Override
  public DemoVO update(DemoUpdateReq req) {
    // Get demo by ID
    // 根据 ID 获取 Demo
    DemoEntity demoEntity = demoMapper.selectById(req.getId());
    if (demoEntity == null) {
      throw new BusinessException(ErrorCode.NOT_FOUND);
    }

    // Update fields
    // 更新字段
    if (req.getName() != null) {
      demoEntity.setName(req.getName());
    }
    if (req.getDescription() != null) {
      demoEntity.setDescription(req.getDescription());
    }
    if (req.getStatus() != null) {
      demoEntity.setStatus(req.getStatus());
    }

    // Save and return
    // 保存并返回
    demoMapper.updateById(demoEntity);
    return demoMapstruct.entityToVO(demoEntity);
  }

  @Override
  public void delete(Long id) {
    // Check if demo exists
    // 检查 Demo 是否存在
    if (demoMapper.selectById(id) == null) {
      throw new BusinessException(ErrorCode.NOT_FOUND);
    }

    // Delete demo (logic delete)
    // 删除 Demo（逻辑删除）
    demoMapper.deleteById(id);
  }

  @Override
  public DemoVO getById(Long id) {
    // Get demo by ID
    // 根据 ID 获取 Demo
    DemoEntity demoEntity = demoMapper.selectById(id);
    if (demoEntity == null) {
      throw new BusinessException(ErrorCode.NOT_FOUND);
    }

    // Return VO
    // 返回 VO
    return demoMapstruct.entityToVO(demoEntity);
  }

  @Override
  public Page<DemoVO> getPage(Integer pageNum, Integer pageSize) {
    // Create page object
    // 创建分页对象
    Page<DemoEntity> page = new Page<>(pageNum, pageSize);

    // Query page
    // 查询分页
    Page<DemoEntity> demoPage = demoMapper.selectPage(page, new LambdaQueryWrapper<>());

    // Convert to VO page
    // 转换为 VO 分页
    List<DemoVO> voList =
        demoPage.getRecords().stream().map(demoMapstruct::entityToVO).collect(Collectors.toList());

    Page<DemoVO> voPage = new Page<>(pageNum, pageSize, demoPage.getTotal());
    voPage.setRecords(voList);

    return voPage;
  }
}
