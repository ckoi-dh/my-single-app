package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.domain.dto.req.CommentCreateReq;
import com.example.demo.domain.dto.req.CommentQueryReq;
import com.example.demo.domain.dto.req.CommentUpdateReq;
import com.example.demo.domain.vo.CommentVO;
import com.example.demo.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/** Comment controller. 评论控制器。 */
@Tag(name = "Comment", description = "Comment Management API")
@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;

  /**
   * Create comment. 创建评论。
   *
   * @param req Comment create request
   * @return Comment VO
   */
  @Operation(summary = "Create comment", description = "Create a new comment")
  @PostMapping
  public Result<CommentVO> create(
      @Parameter(description = "Comment create request") @Valid @RequestBody CommentCreateReq req) {
    return Result.success(commentService.create(req));
  }

  /**
   * Update comment. 更新评论。
   *
   * @param req Comment update request
   * @return Comment VO
   */
  @Operation(summary = "Update comment", description = "Update an existing comment")
  @PutMapping
  public Result<CommentVO> update(
      @Parameter(description = "Comment update request") @Valid @RequestBody CommentUpdateReq req) {
    return Result.success(commentService.update(req));
  }

  /**
   * Delete comment. 删除评论。
   *
   * @param id Comment ID
   * @return Success result
   */
  @Operation(summary = "Delete comment", description = "Delete a comment by ID")
  @DeleteMapping("/{id}")
  public Result<Void> delete(@Parameter(description = "Comment ID") @PathVariable Long id) {
    commentService.delete(id);
    return Result.success();
  }

  /**
   * Get comment by ID. 根据ID获取评论。
   *
   * @param id Comment ID
   * @return Comment VO
   */
  @Operation(summary = "Get comment", description = "Get comment by ID")
  @GetMapping("/{id}")
  public Result<CommentVO> getById(@Parameter(description = "Comment ID") @PathVariable Long id) {
    return Result.success(commentService.getById(id));
  }

  /**
   * Get comment page. 获取评论分页列表。
   *
   * @param req Comment query request
   * @return Comment page
   */
  @Operation(summary = "Get comment page", description = "Get comment page list")
  @GetMapping("/page")
  public Result<Page<CommentVO>> getPage(
      @Parameter(description = "Comment query request") @Valid CommentQueryReq req) {
    return Result.success(commentService.getPage(req));
  }

  /**
   * Get comments by article. 获取文章评论列表。
   *
   * @param articleId Article ID
   * @return Comment list
   */
  @Operation(summary = "Get article comments", description = "Get comments by article ID")
  @GetMapping("/article/{articleId}")
  public Result<List<CommentVO>> getByArticleId(
      @Parameter(description = "Article ID") @PathVariable Long articleId) {
    return Result.success(commentService.getByArticleId(articleId));
  }

  /**
   * Get comment tree. 获取评论树。
   *
   * @param articleId Article ID
   * @return Comment tree
   */
  @Operation(summary = "Get comment tree", description = "Get comment tree by article ID")
  @GetMapping("/tree/{articleId}")
  public Result<List<CommentVO>> getTreeByArticleId(
      @Parameter(description = "Article ID") @PathVariable Long articleId) {
    return Result.success(commentService.getTreeByArticleId(articleId));
  }

  /**
   * Approve comment. 审核通过评论。
   *
   * @param id Comment ID
   * @return Comment VO
   */
  @Operation(summary = "Approve comment", description = "Approve a comment")
  @PostMapping("/{id}/approve")
  public Result<CommentVO> approve(@Parameter(description = "Comment ID") @PathVariable Long id) {
    return Result.success(commentService.approve(id));
  }

  /**
   * Reject comment. 审核拒绝评论。
   *
   * @param id Comment ID
   * @return Comment VO
   */
  @Operation(summary = "Reject comment", description = "Reject a comment")
  @PostMapping("/{id}/reject")
  public Result<CommentVO> reject(@Parameter(description = "Comment ID") @PathVariable Long id) {
    return Result.success(commentService.reject(id));
  }

  /**
   * Increase comment like count. 增加评论点赞次数。
   *
   * @param id Comment ID
   * @return Success result
   */
  @Operation(summary = "Increase comment like count", description = "Increase comment like count")
  @PostMapping("/{id}/like")
  public Result<Void> increaseLikeCount(
      @Parameter(description = "Comment ID") @PathVariable Long id) {
    commentService.increaseLikeCount(id);
    return Result.success();
  }
}
