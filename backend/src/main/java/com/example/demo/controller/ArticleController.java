package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.domain.dto.req.ArticleCreateReq;
import com.example.demo.domain.dto.req.ArticleQueryReq;
import com.example.demo.domain.dto.req.ArticleUpdateReq;
import com.example.demo.domain.vo.ArticleVO;
import com.example.demo.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/** Article controller. 文章控制器。 */
@Tag(name = "Article", description = "Article Management API")
@RestController
@RequestMapping("/api/v1/article")
@RequiredArgsConstructor
public class ArticleController {

  private final ArticleService articleService;

  /**
   * Create article. 创建文章。
   *
   * @param req Article create request
   * @return Article VO
   */
  @Operation(summary = "Create article", description = "Create a new article")
  @PostMapping
  public Result<ArticleVO> create(
      @Parameter(description = "Article create request") @Valid @RequestBody ArticleCreateReq req) {
    return Result.success(articleService.create(req));
  }

  /**
   * Update article. 更新文章。
   *
   * @param req Article update request
   * @return Article VO
   */
  @Operation(summary = "Update article", description = "Update an existing article")
  @PutMapping
  public Result<ArticleVO> update(
      @Parameter(description = "Article update request") @Valid @RequestBody ArticleUpdateReq req) {
    return Result.success(articleService.update(req));
  }

  /**
   * Delete article. 删除文章。
   *
   * @param id Article ID
   * @return Success result
   */
  @Operation(summary = "Delete article", description = "Delete an article by ID")
  @DeleteMapping("/{id}")
  public Result<Void> delete(@Parameter(description = "Article ID") @PathVariable Long id) {
    articleService.delete(id);
    return Result.success();
  }

  /**
   * Get article by ID. 根据ID获取文章。
   *
   * @param id Article ID
   * @return Article VO
   */
  @Operation(summary = "Get article", description = "Get article by ID")
  @GetMapping("/{id}")
  public Result<ArticleVO> getById(@Parameter(description = "Article ID") @PathVariable Long id) {
    return Result.success(articleService.getById(id));
  }

  /**
   * Get article page. 获取文章分页列表。
   *
   * @param req Article query request
   * @return Article page
   */
  @Operation(summary = "Get article page", description = "Get article page list")
  @GetMapping("/page")
  public Result<Page<ArticleVO>> getPage(
      @Parameter(description = "Article query request") @Valid ArticleQueryReq req) {
    return Result.success(articleService.getPage(req));
  }

  /**
   * Search articles. 搜索文章。
   *
   * @param req Article query request
   * @return Article page
   */
  @Operation(summary = "Search articles", description = "Search articles by keyword")
  @GetMapping("/search")
  public Result<Page<ArticleVO>> search(
      @Parameter(description = "Article search request") @Valid ArticleQueryReq req) {
    return Result.success(articleService.search(req));
  }

  /**
   * Publish article. 发布文章。
   *
   * @param id Article ID
   * @return Article VO
   */
  @Operation(summary = "Publish article", description = "Publish an article")
  @PostMapping("/{id}/publish")
  public Result<ArticleVO> publish(@Parameter(description = "Article ID") @PathVariable Long id) {
    return Result.success(articleService.publish(id));
  }

  /**
   * Set article as featured. 推荐文章。
   *
   * @param id Article ID
   * @param isFeatured Is featured
   * @return Article VO
   */
  @Operation(summary = "Set article as featured", description = "Set article as featured or not")
  @PostMapping("/{id}/feature")
  public Result<ArticleVO> setFeatured(
      @Parameter(description = "Article ID") @PathVariable Long id,
      @Parameter(description = "Is featured") @RequestParam Boolean isFeatured) {
    return Result.success(articleService.setFeatured(id, isFeatured));
  }

  /**
   * Set article as sticky. 置顶文章。
   *
   * @param id Article ID
   * @param isSticky Is sticky
   * @return Article VO
   */
  @Operation(summary = "Set article as sticky", description = "Set article as sticky or not")
  @PostMapping("/{id}/sticky")
  public Result<ArticleVO> setSticky(
      @Parameter(description = "Article ID") @PathVariable Long id,
      @Parameter(description = "Is sticky") @RequestParam Boolean isSticky) {
    return Result.success(articleService.setSticky(id, isSticky));
  }

  /**
   * Increase article view count. 增加文章浏览次数。
   *
   * @param id Article ID
   * @return Success result
   */
  @Operation(summary = "Increase article view count", description = "Increase article view count")
  @PostMapping("/{id}/view")
  public Result<Void> increaseViewCount(
      @Parameter(description = "Article ID") @PathVariable Long id) {
    articleService.increaseViewCount(id);
    return Result.success();
  }

  /**
   * Increase article share count. 增加文章分享次数。
   *
   * @param id Article ID
   * @return Success result
   */
  @Operation(summary = "Increase article share count", description = "Increase article share count")
  @PostMapping("/{id}/share")
  public Result<Void> increaseShareCount(
      @Parameter(description = "Article ID") @PathVariable Long id) {
    articleService.increaseShareCount(id);
    return Result.success();
  }
}
