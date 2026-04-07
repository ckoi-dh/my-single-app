package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.domain.dto.req.CategoryCreateReq;
import com.example.demo.domain.dto.req.CategoryUpdateReq;
import com.example.demo.domain.dto.req.PageReq;
import com.example.demo.domain.entity.CategoryEntity;
import com.example.demo.domain.vo.CategoryVO;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.service.CategoryService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Category service implementation. 分类服务实现。 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryMapper categoryMapper;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public CategoryVO create(CategoryCreateReq req) {
    // Check if category name exists
    if (existsByName(req.getName())) {
      throw new BusinessException("Category name already exists");
    }

    // Create category entity
    CategoryEntity entity = new CategoryEntity();
    entity.setName(req.getName());
    entity.setDescription(req.getDescription());
    entity.setParentId(req.getParentId());
    entity.setSortOrder(req.getSortOrder() != null ? req.getSortOrder() : 0);
    entity.setStatus(1); // Active status

    categoryMapper.insert(entity);
    log.info("Created category: {}", entity.getName());

    return convertToVO(entity);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public CategoryVO update(CategoryUpdateReq req) {
    CategoryEntity entity = categoryMapper.selectById(req.getId());
    if (entity == null) {
      throw new BusinessException("Category not found");
    }

    // Check if name is changing and already exists
    if (StringUtils.isNotBlank(req.getName()) && !req.getName().equals(entity.getName())) {
      if (existsByName(req.getName())) {
        throw new BusinessException("Category name already exists");
      }
      entity.setName(req.getName());
    }

    // Update description and other fields
    if (StringUtils.isNotBlank(req.getDescription())) {
      entity.setDescription(req.getDescription());
    }
    if (req.getParentId() != null) {
      entity.setParentId(req.getParentId());
    }
    if (req.getSortOrder() != null) {
      entity.setSortOrder(req.getSortOrder());
    }

    categoryMapper.updateById(entity);
    log.info("Updated category: {}", entity.getName());

    return convertToVO(entity);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void delete(Long id) {
    CategoryEntity entity = categoryMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Category not found");
    }

    // Check if has children
    List<CategoryEntity> children =
        categoryMapper.selectList(
            new LambdaQueryWrapper<CategoryEntity>().eq(CategoryEntity::getParentId, id));
    if (!children.isEmpty()) {
      throw new BusinessException("Category has children, cannot delete");
    }

    categoryMapper.deleteById(id);
    log.info("Deleted category: {}", entity.getName());
  }

  @Override
  public CategoryVO getById(Long id) {
    CategoryEntity entity = categoryMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Category not found");
    }
    return convertToVO(entity);
  }

  @Override
  public Page<CategoryVO> getPage(PageReq req) {
    Page<CategoryEntity> page = new Page<>(req.getCurrent(), req.getSize());

    LambdaQueryWrapper<CategoryEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.orderByAsc(CategoryEntity::getSortOrder, CategoryEntity::getCreateTime);

    Page<CategoryEntity> entityPage = categoryMapper.selectPage(page, wrapper);
    return convertToVOPage(entityPage);
  }

  @Override
  public List<CategoryVO> getList() {
    LambdaQueryWrapper<CategoryEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.orderByAsc(CategoryEntity::getSortOrder, CategoryEntity::getCreateTime);

    List<CategoryEntity> entities = categoryMapper.selectList(wrapper);
    return entities.stream().map(this::convertToVO).collect(Collectors.toList());
  }

  @Override
  public List<CategoryVO> getTree() {
    List<CategoryVO> categories = getList();
    return buildTree(categories, null);
  }

  @Override
  public CategoryVO getByName(String name) {
    LambdaQueryWrapper<CategoryEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(CategoryEntity::getName, name);

    CategoryEntity entity = categoryMapper.selectOne(wrapper);
    return entity != null ? convertToVO(entity) : null;
  }

  @Override
  public boolean existsByName(String name) {
    LambdaQueryWrapper<CategoryEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(CategoryEntity::getName, name);

    return categoryMapper.selectCount(wrapper) > 0;
  }

  /**
   * Build category tree. 构建分类树。
   *
   * @param categories All categories
   * @param parentId Parent category ID
   * @return Category tree
   */
  private List<CategoryVO> buildTree(List<CategoryVO> categories, Long parentId) {
    List<CategoryVO> tree = new ArrayList<>();

    for (CategoryVO category : categories) {
      if ((parentId == null && category.getParentId() == null)
          || (parentId != null && parentId.equals(category.getParentId()))) {
        List<CategoryVO> children = buildTree(categories, category.getId());
        category.setChildren(children);
        tree.add(category);
      }
    }

    return tree;
  }

  /**
   * Convert entity to VO. 将实体转换为VO。
   *
   * @param entity Category entity
   * @return Category VO
   */
  private CategoryVO convertToVO(CategoryEntity entity) {
    CategoryVO vo = new CategoryVO();
    vo.setId(entity.getId());
    vo.setName(entity.getName());
    vo.setDescription(entity.getDescription());
    vo.setParentId(entity.getParentId());
    vo.setSortOrder(entity.getSortOrder());
    vo.setCreateTime(entity.getCreateTime());
    vo.setUpdateTime(entity.getUpdateTime());

    // TODO: Calculate article count
    vo.setArticleCount(0L);

    return vo;
  }

  /**
   * Convert entity page to VO page. 将实体分页转换为VO分页。
   *
   * @param entityPage Entity page
   * @return VO page
   */
  private Page<CategoryVO> convertToVOPage(Page<CategoryEntity> entityPage) {
    Page<CategoryVO> voPage =
        new Page<>(entityPage.getCurrent(), entityPage.getSize(), entityPage.getTotal());
    voPage.setRecords(
        entityPage.getRecords().stream().map(this::convertToVO).collect(Collectors.toList()));
    return voPage;
  }
}
