package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.domain.dto.req.ArticleCreateReq;
import com.example.demo.domain.dto.req.ArticleQueryReq;
import com.example.demo.domain.dto.req.ArticleUpdateReq;
import com.example.demo.domain.vo.ArticleVO;

/** Article service interface. 文章服务接口。 */
public interface ArticleService {

  /**
   * Create article. 创建文章。
   *
   * @param req Article create request
   * @return Article VO
   */
  ArticleVO create(ArticleCreateReq req);

  /**
   * Update article. 更新文章。
   *
   * @param req Article update request
   * @return Article VO
   */
  ArticleVO update(ArticleUpdateReq req);

  /**
   * Delete article. 删除文章。
   *
   * @param id Article ID
   */
  void delete(Long id);

  /**
   * Get article by ID. 根据ID获取文章。
   *
   * @param id Article ID
   * @return Article VO
   */
  ArticleVO getById(Long id);

  /**
   * Get article page. 获取文章分页列表。
   *
   * @param req Article query request
   * @return Article page
   */
  Page<ArticleVO> getPage(ArticleQueryReq req);

  /**
   * Search articles. 搜索文章。
   *
   * @param req Article query request
   * @return Article page
   */
  Page<ArticleVO> search(ArticleQueryReq req);

  /**
   * Publish article. 发布文章。
   *
   * @param id Article ID
   * @return Article VO
   */
  ArticleVO publish(Long id);

  /**
   * Set article as featured. 推荐文章。
   *
   * @param id Article ID
   * @param isFeatured Is featured
   * @return Article VO
   */
  ArticleVO setFeatured(Long id, Boolean isFeatured);

  /**
   * Set article as sticky. 置顶文章。
   *
   * @param id Article ID
   * @param isSticky Is sticky
   * @return Article VO
   */
  ArticleVO setSticky(Long id, Boolean isSticky);

  /**
   * Increase article view count. 增加文章浏览次数。
   *
   * @param id Article ID
   */
  void increaseViewCount(Long id);

  /**
   * Increase article like count. 增加文章点赞次数。
   *
   * @param id Article ID
   */
  void increaseLikeCount(Long id);

  /**
   * Increase article comment count. 增加文章评论次数。
   *
   * @param id Article ID
   */
  void increaseCommentCount(Long id);

  /**
   * Increase article favorite count. 增加文章收藏次数。
   *
   * @param id Article ID
   */
  void increaseFavoriteCount(Long id);

  /**
   * Increase article share count. 增加文章分享次数。
   *
   * @param id Article ID
   */
  void increaseShareCount(Long id);
}
