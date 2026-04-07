package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.domain.dto.req.CommentCreateReq;
import com.example.demo.domain.dto.req.CommentQueryReq;
import com.example.demo.domain.dto.req.CommentUpdateReq;
import com.example.demo.domain.vo.CommentVO;
import java.util.List;

/** Comment service interface. 评论服务接口。 */
public interface CommentService {

  /**
   * Create comment. 创建评论。
   *
   * @param req Comment create request
   * @return Comment VO
   */
  CommentVO create(CommentCreateReq req);

  /**
   * Update comment. 更新评论。
   *
   * @param req Comment update request
   * @return Comment VO
   */
  CommentVO update(CommentUpdateReq req);

  /**
   * Delete comment. 删除评论。
   *
   * @param id Comment ID
   */
  void delete(Long id);

  /**
   * Get comment by ID. 根据ID获取评论。
   *
   * @param id Comment ID
   * @return Comment VO
   */
  CommentVO getById(Long id);

  /**
   * Get comment page. 获取评论分页列表。
   *
   * @param req Comment query request
   * @return Comment page
   */
  Page<CommentVO> getPage(CommentQueryReq req);

  /**
   * Get comments by article. 获取文章评论列表。
   *
   * @param articleId Article ID
   * @return Comment list
   */
  List<CommentVO> getByArticleId(Long articleId);

  /**
   * Get comment tree. 获取评论树。
   *
   * @param articleId Article ID
   * @return Comment tree
   */
  List<CommentVO> getTreeByArticleId(Long articleId);

  /**
   * Approve comment. 审核通过评论。
   *
   * @param id Comment ID
   * @return Comment VO
   */
  CommentVO approve(Long id);

  /**
   * Reject comment. 审核拒绝评论。
   *
   * @param id Comment ID
   * @return Comment VO
   */
  CommentVO reject(Long id);

  /**
   * Increase comment like count. 增加评论点赞次数。
   *
   * @param id Comment ID
   */
  void increaseLikeCount(Long id);
}
