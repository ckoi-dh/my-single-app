package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.domain.dto.req.PageReq;
import com.example.demo.domain.dto.req.TagCreateReq;
import com.example.demo.domain.dto.req.TagUpdateReq;
import com.example.demo.domain.vo.TagVO;
import com.example.demo.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/** Tag controller. 标签控制器。 */
@Tag(name = "Tag", description = "Tag Management API")
@RestController
@RequestMapping("/api/v1/tag")
@RequiredArgsConstructor
public class TagController {

  private final TagService tagService;

  /**
   * Create tag. 创建标签。
   *
   * @param req Tag create request
   * @return Tag VO
   */
  @Operation(summary = "Create tag", description = "Create a new tag")
  @PostMapping
  public Result<TagVO> create(
      @Parameter(description = "Tag create request") @Valid @RequestBody TagCreateReq req) {
    return Result.success(tagService.create(req));
  }

  /**
   * Update tag. 更新标签。
   *
   * @param req Tag update request
   * @return Tag VO
   */
  @Operation(summary = "Update tag", description = "Update an existing tag")
  @PutMapping
  public Result<TagVO> update(
      @Parameter(description = "Tag update request") @Valid @RequestBody TagUpdateReq req) {
    return Result.success(tagService.update(req));
  }

  /**
   * Delete tag. 删除标签。
   *
   * @param id Tag ID
   * @return Success result
   */
  @Operation(summary = "Delete tag", description = "Delete a tag by ID")
  @DeleteMapping("/{id}")
  public Result<Void> delete(@Parameter(description = "Tag ID") @PathVariable Long id) {
    tagService.delete(id);
    return Result.success();
  }

  /**
   * Get tag by ID. 根据ID获取标签。
   *
   * @param id Tag ID
   * @return Tag VO
   */
  @Operation(summary = "Get tag", description = "Get tag by ID")
  @GetMapping("/{id}")
  public Result<TagVO> getById(@Parameter(description = "Tag ID") @PathVariable Long id) {
    return Result.success(tagService.getById(id));
  }

  /**
   * Get tag page. 获取标签分页列表。
   *
   * @param req Page request
   * @return Tag page
   */
  @Operation(summary = "Get tag page", description = "Get tag page list")
  @GetMapping("/page")
  public Result<Page<TagVO>> getPage(@Parameter(description = "Page request") @Valid PageReq req) {
    return Result.success(tagService.getPage(req));
  }

  /**
   * Get all tags. 获取所有标签。
   *
   * @return Tag list
   */
  @Operation(summary = "Get all tags", description = "Get all tag list")
  @GetMapping("/list")
  public Result<List<TagVO>> getList() {
    return Result.success(tagService.getList());
  }

  /**
   * Search tags. 搜索标签。
   *
   * @param keyword Search keyword
   * @return Tag list
   */
  @Operation(summary = "Search tags", description = "Search tags by keyword")
  @GetMapping("/search")
  public Result<List<TagVO>> search(
      @Parameter(description = "Search keyword") @RequestParam(required = false) String keyword) {
    return Result.success(tagService.search(keyword));
  }
}
