package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.domain.dto.req.PageReq;
import com.example.demo.domain.dto.req.TagCreateReq;
import com.example.demo.domain.dto.req.TagUpdateReq;
import com.example.demo.domain.vo.TagVO;
import java.util.List;

/** Tag service interface. 标签服务接口。 */
public interface TagService {

  /**
   * Create tag. 创建标签。
   *
   * @param req Tag create request
   * @return Tag VO
   */
  TagVO create(TagCreateReq req);

  /**
   * Update tag. 更新标签。
   *
   * @param req Tag update request
   * @return Tag VO
   */
  TagVO update(TagUpdateReq req);

  /**
   * Delete tag. 删除标签。
   *
   * @param id Tag ID
   */
  void delete(Long id);

  /**
   * Get tag by ID. 根据ID获取标签。
   *
   * @param id Tag ID
   * @return Tag VO
   */
  TagVO getById(Long id);

  /**
   * Get tag page. 获取标签分页列表。
   *
   * @param req Page request
   * @return Tag page
   */
  Page<TagVO> getPage(PageReq req);

  /**
   * Get all tags. 获取所有标签。
   *
   * @return Tag list
   */
  List<TagVO> getList();

  /**
   * Get tag by name. 根据名称获取标签。
   *
   * @param name Tag name
   * @return Tag VO
   */
  TagVO getByName(String name);

  /**
   * Check if tag exists. 检查标签是否存在。
   *
   * @param name Tag name
   * @return True if exists
   */
  boolean existsByName(String name);

  /**
   * Search tags. 搜索标签。
   *
   * @param keyword Search keyword
   * @return Tag list
   */
  List<TagVO> search(String keyword);
}
