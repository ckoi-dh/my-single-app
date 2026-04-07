package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.domain.dto.req.PageReq;
import com.example.demo.domain.dto.req.TagCreateReq;
import com.example.demo.domain.dto.req.TagUpdateReq;
import com.example.demo.domain.entity.TagEntity;
import com.example.demo.domain.vo.TagVO;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.TagMapper;
import com.example.demo.service.TagService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Tag service implementation. 标签服务实现。 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

  private final TagMapper tagMapper;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public TagVO create(TagCreateReq req) {
    if (existsByName(req.getName())) {
      throw new BusinessException("Tag name already exists");
    }

    TagEntity entity = new TagEntity();
    entity.setName(req.getName());
    entity.setDescription(req.getDescription());
    entity.setColor(req.getColor() != null ? req.getColor() : "#000000");
    entity.setSortOrder(req.getSortOrder() != null ? req.getSortOrder() : 0);
    entity.setStatus(1);

    tagMapper.insert(entity);
    log.info("Created tag: {}", entity.getName());

    return getById(entity.getId());
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public TagVO update(TagUpdateReq req) {
    TagEntity entity = tagMapper.selectById(req.getId());
    if (entity == null) {
      throw new BusinessException("Tag not found");
    }

    if (StringUtils.isNotBlank(req.getName()) && !req.getName().equals(entity.getName())) {
      if (existsByName(req.getName())) {
        throw new BusinessException("Tag name already exists");
      }
      entity.setName(req.getName());
    }

    if (StringUtils.isNotBlank(req.getDescription())) {
      entity.setDescription(req.getDescription());
    }
    if (StringUtils.isNotBlank(req.getColor())) {
      entity.setColor(req.getColor());
    }
    if (req.getSortOrder() != null) {
      entity.setSortOrder(req.getSortOrder());
    }

    tagMapper.updateById(entity);
    log.info("Updated tag: {}", entity.getName());

    return getById(entity.getId());
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void delete(Long id) {
    TagEntity entity = tagMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Tag not found");
    }

    tagMapper.deleteById(id);
    log.info("Deleted tag: {}", entity.getName());
  }

  @Override
  public TagVO getById(Long id) {
    TagEntity entity = tagMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Tag not found");
    }
    return convertToVO(entity);
  }

  @Override
  public Page<TagVO> getPage(PageReq req) {
    Page<TagEntity> page = new Page<>(req.getCurrent(), req.getSize());

    LambdaQueryWrapper<TagEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.orderByAsc(TagEntity::getSortOrder, TagEntity::getCreateTime);

    Page<TagEntity> entityPage = tagMapper.selectPage(page, wrapper);
    return convertToVOPage(entityPage);
  }

  @Override
  public List<TagVO> getList() {
    LambdaQueryWrapper<TagEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.orderByAsc(TagEntity::getSortOrder, TagEntity::getCreateTime);

    List<TagEntity> entities = tagMapper.selectList(wrapper);
    return entities.stream().map(this::convertToVO).collect(Collectors.toList());
  }

  @Override
  public TagVO getByName(String name) {
    LambdaQueryWrapper<TagEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(TagEntity::getName, name);

    TagEntity entity = tagMapper.selectOne(wrapper);
    return entity != null ? convertToVO(entity) : null;
  }

  @Override
  public boolean existsByName(String name) {
    LambdaQueryWrapper<TagEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(TagEntity::getName, name);

    return tagMapper.selectCount(wrapper) > 0;
  }

  @Override
  public List<TagVO> search(String keyword) {
    LambdaQueryWrapper<TagEntity> wrapper = new LambdaQueryWrapper<>();
    if (StringUtils.isNotBlank(keyword)) {
      wrapper.like(TagEntity::getName, keyword).or().like(TagEntity::getDescription, keyword);
    }
    wrapper.orderByAsc(TagEntity::getSortOrder, TagEntity::getCreateTime);

    List<TagEntity> entities = tagMapper.selectList(wrapper);
    return entities.stream().map(this::convertToVO).collect(Collectors.toList());
  }

  private TagVO convertToVO(TagEntity entity) {
    TagVO vo = new TagVO();
    vo.setId(entity.getId());
    vo.setName(entity.getName());
    vo.setDescription(entity.getDescription());
    vo.setArticleCount(0L);
    vo.setCreateTime(entity.getCreateTime());
    vo.setUpdateTime(entity.getUpdateTime());
    return vo;
  }

  private Page<TagVO> convertToVOPage(Page<TagEntity> entityPage) {
    Page<TagVO> voPage =
        new Page<>(entityPage.getCurrent(), entityPage.getSize(), entityPage.getTotal());
    voPage.setRecords(
        entityPage.getRecords().stream().map(this::convertToVO).collect(Collectors.toList()));
    return voPage;
  }
}
