package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.domain.dto.req.CategoryCreateReq;
import com.example.demo.domain.dto.req.CategoryUpdateReq;
import com.example.demo.domain.dto.req.PageReq;
import com.example.demo.domain.vo.CategoryVO;
import java.util.List;

/** Category service interface. 分类服务接口。 */
public interface CategoryService {

  /**
   * Create category. 创建分类。
   *
   * @param req Category create request
   * @return Category VO
   */
  CategoryVO create(CategoryCreateReq req);

  /**
   * Update category. 更新分类。
   *
   * @param req Category update request
   * @return Category VO
   */
  CategoryVO update(CategoryUpdateReq req);

  /**
   * Delete category. 删除分类。
   *
   * @param id Category ID
   */
  void delete(Long id);

  /**
   * Get category by ID. 根据ID获取分类。
   *
   * @param id Category ID
   * @return Category VO
   */
  CategoryVO getById(Long id);

  /**
   * Get category page. 获取分类分页列表。
   *
   * @param req Page request
   * @return Category page
   */
  Page<CategoryVO> getPage(PageReq req);

  /**
   * Get all categories. 获取所有分类。
   *
   * @return Category list
   */
  List<CategoryVO> getList();

  /**
   * Get category tree. 获取分类树。
   *
   * @return Category tree
   */
  List<CategoryVO> getTree();

  /**
   * Get category by name. 根据名称获取分类。
   *
   * @param name Category name
   * @return Category VO
   */
  CategoryVO getByName(String name);

  /**
   * Check if category exists. 检查分类是否存在。
   *
   * @param name Category name
   * @return True if exists
   */
  boolean existsByName(String name);
}
