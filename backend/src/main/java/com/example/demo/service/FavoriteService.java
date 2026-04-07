package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.domain.dto.req.FavoriteCreateReq;
import com.example.demo.domain.dto.req.PageReq;
import com.example.demo.domain.vo.FavoriteVO;
import java.util.List;

/** Favorite service interface. 收藏服务接口。 */
public interface FavoriteService {

  /**
   * Create favorite. 创建收藏。
   *
   * @param req Favorite create request
   * @return Favorite VO
   */
  FavoriteVO create(FavoriteCreateReq req);

  /**
   * Delete favorite. 删除收藏。
   *
   * @param id Favorite ID
   */
  void delete(Long id);

  /**
   * Get favorite by ID. 根据ID获取收藏。
   *
   * @param id Favorite ID
   * @return Favorite VO
   */
  FavoriteVO getById(Long id);

  /**
   * Get favorite page. 获取收藏分页列表。
   *
   * @param req Page request
   * @return Favorite page
   */
  Page<FavoriteVO> getPage(PageReq req);

  /**
   * Get favorites by user. 获取用户收藏列表。
   *
   * @param userId User ID
   * @return Favorite list
   */
  List<FavoriteVO> getByUserId(Long userId);

  /**
   * Get favorites by target. 获取目标收藏列表。
   *
   * @param targetId Target ID
   * @param targetType Target type
   * @return Favorite list
   */
  List<FavoriteVO> getByTarget(Long targetId, Integer targetType);

  /**
   * Check if favorite exists. 检查收藏是否存在。
   *
   * @param userId User ID
   * @param targetId Target ID
   * @param targetType Target type
   * @return True if exists
   */
  boolean exists(Long userId, Long targetId, Integer targetType);

  /**
   * Toggle favorite. 切换收藏状态。
   *
   * @param req Favorite create request
   * @return Favorite VO or null if deleted
   */
  FavoriteVO toggle(FavoriteCreateReq req);
}
