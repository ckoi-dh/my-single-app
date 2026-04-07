package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.domain.dto.req.InteractCreateReq;
import com.example.demo.domain.dto.req.PageReq;
import com.example.demo.domain.vo.InteractVO;
import java.util.List;

/** Interact service interface. 互动服务接口。 */
public interface InteractService {

  /**
   * Create interact. 创建互动。
   *
   * @param req Interact create request
   * @return Interact VO
   */
  InteractVO create(InteractCreateReq req);

  /**
   * Delete interact. 删除互动。
   *
   * @param id Interact ID
   */
  void delete(Long id);

  /**
   * Get interact by ID. 根据ID获取互动。
   *
   * @param id Interact ID
   * @return Interact VO
   */
  InteractVO getById(Long id);

  /**
   * Get interact page. 获取互动分页列表。
   *
   * @param req Page request
   * @return Interact page
   */
  Page<InteractVO> getPage(PageReq req);

  /**
   * Get interacts by user. 获取用户互动列表。
   *
   * @param userId User ID
   * @return Interact list
   */
  List<InteractVO> getByUserId(Long userId);

  /**
   * Get interacts by target. 获取目标互动列表。
   *
   * @param targetId Target ID
   * @param targetType Target type
   * @return Interact list
   */
  List<InteractVO> getByTarget(Long targetId, Integer targetType);

  /**
   * Check if interact exists. 检查互动是否存在。
   *
   * @param userId User ID
   * @param targetId Target ID
   * @param targetType Target type
   * @return True if exists
   */
  boolean exists(Long userId, Long targetId, Integer targetType);

  /**
   * Toggle interact. 切换互动状态。
   *
   * @param req Interact create request
   * @return Interact VO or null if deleted
   */
  InteractVO toggle(InteractCreateReq req);
}
