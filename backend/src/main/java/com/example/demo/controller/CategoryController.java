package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.domain.dto.req.CategoryCreateReq;
import com.example.demo.domain.dto.req.CategoryUpdateReq;
import com.example.demo.domain.dto.req.PageReq;
import com.example.demo.domain.vo.CategoryVO;
import com.example.demo.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/** Category controller. 分类控制器。 */
@Tag(name = "Category", description = "Category Management API")
@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  /**
   * Create category. 创建分类。
   *
   * @param req Category create request
   * @return Category VO
   */
  @Operation(summary = "Create category", description = "Create a new category")
  @PostMapping
  public Result<CategoryVO> create(
      @Parameter(description = "Category create request") @Valid @RequestBody
          CategoryCreateReq req) {
    return Result.success(categoryService.create(req));
  }

  /**
   * Update category. 更新分类。
   *
   * @param req Category update request
   * @return Category VO
   */
  @Operation(summary = "Update category", description = "Update an existing category")
  @PutMapping
  public Result<CategoryVO> update(
      @Parameter(description = "Category update request") @Valid @RequestBody
          CategoryUpdateReq req) {
    return Result.success(categoryService.update(req));
  }

  /**
   * Delete category. 删除分类。
   *
   * @param id Category ID
   * @return Success result
   */
  @Operation(summary = "Delete category", description = "Delete a category by ID")
  @DeleteMapping("/{id}")
  public Result<Void> delete(@Parameter(description = "Category ID") @PathVariable Long id) {
    categoryService.delete(id);
    return Result.success();
  }

  /**
   * Get category by ID. 根据ID获取分类。
   *
   * @param id Category ID
   * @return Category VO
   */
  @Operation(summary = "Get category", description = "Get category by ID")
  @GetMapping("/{id}")
  public Result<CategoryVO> getById(@Parameter(description = "Category ID") @PathVariable Long id) {
    return Result.success(categoryService.getById(id));
  }

  /**
   * Get category page. 获取分类分页列表。
   *
   * @param req Page request
   * @return Category page
   */
  @Operation(summary = "Get category page", description = "Get category page list")
  @GetMapping("/page")
  public Result<Page<CategoryVO>> getPage(
      @Parameter(description = "Page request") @Valid PageReq req) {
    return Result.success(categoryService.getPage(req));
  }

  /**
   * Get all categories. 获取所有分类。
   *
   * @return Category list
   */
  @Operation(summary = "Get all categories", description = "Get all category list")
  @GetMapping("/list")
  public Result<List<CategoryVO>> getList() {
    return Result.success(categoryService.getList());
  }

  /**
   * Get category tree. 获取分类树。
   *
   * @return Category tree
   */
  @Operation(summary = "Get category tree", description = "Get category tree structure")
  @GetMapping("/tree")
  public Result<List<CategoryVO>> getTree() {
    return Result.success(categoryService.getTree());
  }
}
