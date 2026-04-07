package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.domain.dto.req.InteractCreateReq;
import com.example.demo.domain.dto.req.PageReq;
import com.example.demo.domain.vo.InteractVO;
import com.example.demo.service.InteractService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/** Interact controller. 互动控制器。 */
@Tag(name = "Interact", description = "Interaction Management API")
@RestController
@RequestMapping("/api/v1/interact")
@RequiredArgsConstructor
public class InteractController {

  private final InteractService interactService;

  /**
   * Create interact. 创建互动。
   *
   * @param req Interact create request
   * @return Interact VO
   */
  @Operation(summary = "Create interact", description = "Create a new interact")
  @PostMapping
  public Result<InteractVO> create(
      @Parameter(description = "Interact create request") @Valid @RequestBody
          InteractCreateReq req) {
    return Result.success(interactService.create(req));
  }

  /**
   * Delete interact. 删除互动。
   *
   * @param id Interact ID
   * @return Success result
   */
  @Operation(summary = "Delete interact", description = "Delete an interact by ID")
  @DeleteMapping("/{id}")
  public Result<Void> delete(@Parameter(description = "Interact ID") @PathVariable Long id) {
    interactService.delete(id);
    return Result.success();
  }

  /**
   * Get interact by ID. 根据ID获取互动。
   *
   * @param id Interact ID
   * @return Interact VO
   */
  @Operation(summary = "Get interact", description = "Get interact by ID")
  @GetMapping("/{id}")
  public Result<InteractVO> getById(@Parameter(description = "Interact ID") @PathVariable Long id) {
    return Result.success(interactService.getById(id));
  }

  /**
   * Get interact page. 获取互动分页列表。
   *
   * @param req Page request
   * @return Interact page
   */
  @Operation(summary = "Get interact page", description = "Get interact page list")
  @GetMapping("/page")
  public Result<Page<InteractVO>> getPage(
      @Parameter(description = "Page request") @Valid PageReq req) {
    return Result.success(interactService.getPage(req));
  }

  /**
   * Get interacts by user. 获取用户互动列表。
   *
   * @param userId User ID
   * @return Interact list
   */
  @Operation(summary = "Get user interacts", description = "Get interacts by user ID")
  @GetMapping("/user/{userId}")
  public Result<List<InteractVO>> getByUserId(
      @Parameter(description = "User ID") @PathVariable Long userId) {
    return Result.success(interactService.getByUserId(userId));
  }

  /**
   * Get interacts by target. 获取目标互动列表。
   *
   * @param targetId Target ID
   * @param targetType Target type
   * @return Interact list
   */
  @Operation(summary = "Get target interacts", description = "Get interacts by target")
  @GetMapping("/target")
  public Result<List<InteractVO>> getByTarget(
      @Parameter(description = "Target ID") @RequestParam Long targetId,
      @Parameter(description = "Target type (1-Article, 2-Comment)") @RequestParam
          Integer targetType) {
    return Result.success(interactService.getByTarget(targetId, targetType));
  }

  /**
   * Check interact exists. 检查互动是否存在。
   *
   * @param userId User ID
   * @param targetId Target ID
   * @param targetType Target type
   * @return Exists status
   */
  @Operation(summary = "Check interact exists", description = "Check if interact exists")
  @GetMapping("/exists")
  public Result<Boolean> exists(
      @Parameter(description = "User ID") @RequestParam Long userId,
      @Parameter(description = "Target ID") @RequestParam Long targetId,
      @Parameter(description = "Target type (1-Article, 2-Comment)") @RequestParam
          Integer targetType) {
    return Result.success(interactService.exists(userId, targetId, targetType));
  }

  /**
   * Toggle interact. 切换互动状态。
   *
   * @param req Interact create request
   * @return Interact VO or null
   */
  @Operation(summary = "Toggle interact", description = "Toggle interact state")
  @PostMapping("/toggle")
  public Result<InteractVO> toggle(
      @Parameter(description = "Interact toggle request") @Valid @RequestBody
          InteractCreateReq req) {
    return Result.success(interactService.toggle(req));
  }
}
