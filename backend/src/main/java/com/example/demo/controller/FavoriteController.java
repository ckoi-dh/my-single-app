package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.domain.dto.req.FavoriteCreateReq;
import com.example.demo.domain.dto.req.PageReq;
import com.example.demo.domain.vo.FavoriteVO;
import com.example.demo.service.FavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/** Favorite controller. 收藏控制器。 */
@Tag(name = "Favorite", description = "Favorite Management API")
@RestController
@RequestMapping("/api/v1/favorite")
@RequiredArgsConstructor
public class FavoriteController {

  private final FavoriteService favoriteService;

  /**
   * Create favorite. 创建收藏。
   *
   * @param req Favorite create request
   * @return Favorite VO
   */
  @Operation(summary = "Create favorite", description = "Create a new favorite")
  @PostMapping
  public Result<FavoriteVO> create(
      @Parameter(description = "Favorite create request") @Valid @RequestBody
          FavoriteCreateReq req) {
    return Result.success(favoriteService.create(req));
  }

  /**
   * Delete favorite. 删除收藏。
   *
   * @param id Favorite ID
   * @return Success result
   */
  @Operation(summary = "Delete favorite", description = "Delete a favorite by ID")
  @DeleteMapping("/{id}")
  public Result<Void> delete(@Parameter(description = "Favorite ID") @PathVariable Long id) {
    favoriteService.delete(id);
    return Result.success();
  }

  /**
   * Get favorite by ID. 根据ID获取收藏。
   *
   * @param id Favorite ID
   * @return Favorite VO
   */
  @Operation(summary = "Get favorite", description = "Get favorite by ID")
  @GetMapping("/{id}")
  public Result<FavoriteVO> getById(@Parameter(description = "Favorite ID") @PathVariable Long id) {
    return Result.success(favoriteService.getById(id));
  }

  /**
   * Get favorite page. 获取收藏分页列表。
   *
   * @param req Page request
   * @return Favorite page
   */
  @Operation(summary = "Get favorite page", description = "Get favorite page list")
  @GetMapping("/page")
  public Result<Page<FavoriteVO>> getPage(
      @Parameter(description = "Page request") @Valid PageReq req) {
    return Result.success(favoriteService.getPage(req));
  }

  /**
   * Get favorites by user. 获取用户收藏列表。
   *
   * @param userId User ID
   * @return Favorite list
   */
  @Operation(summary = "Get user favorites", description = "Get favorites by user ID")
  @GetMapping("/user/{userId}")
  public Result<List<FavoriteVO>> getByUserId(
      @Parameter(description = "User ID") @PathVariable Long userId) {
    return Result.success(favoriteService.getByUserId(userId));
  }

  /**
   * Get favorites by target. 获取目标收藏列表。
   *
   * @param targetId Target ID
   * @param targetType Target type
   * @return Favorite list
   */
  @Operation(summary = "Get target favorites", description = "Get favorites by target")
  @GetMapping("/target")
  public Result<List<FavoriteVO>> getByTarget(
      @Parameter(description = "Target ID") @RequestParam Long targetId,
      @Parameter(description = "Target type") @RequestParam Integer targetType) {
    return Result.success(favoriteService.getByTarget(targetId, targetType));
  }

  /**
   * Check favorite exists. 检查收藏是否存在。
   *
   * @param userId User ID
   * @param targetId Target ID
   * @param targetType Target type
   * @return Exists status
   */
  @Operation(summary = "Check favorite exists", description = "Check if favorite exists")
  @GetMapping("/exists")
  public Result<Boolean> exists(
      @Parameter(description = "User ID") @RequestParam Long userId,
      @Parameter(description = "Target ID") @RequestParam Long targetId,
      @Parameter(description = "Target type") @RequestParam Integer targetType) {
    return Result.success(favoriteService.exists(userId, targetId, targetType));
  }

  /**
   * Toggle favorite. 切换收藏状态。
   *
   * @param req Favorite create request
   * @return Favorite VO or null
   */
  @Operation(summary = "Toggle favorite", description = "Toggle favorite state")
  @PostMapping("/toggle")
  public Result<FavoriteVO> toggle(
      @Parameter(description = "Favorite toggle request") @Valid @RequestBody
          FavoriteCreateReq req) {
    return Result.success(favoriteService.toggle(req));
  }
}
