package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.domain.dto.req.PageReq;
import com.example.demo.domain.dto.req.UserCreateReq;
import com.example.demo.domain.dto.req.UserUpdateReq;
import com.example.demo.domain.vo.UserVO;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/** User controller. 用户控制器。 */
@Tag(name = "User", description = "User Management API")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  /** Create user. 创建用户。 */
  @Operation(summary = "Create user", description = "Create a new user")
  @PostMapping
  public Result<UserVO> create(
      @Parameter(description = "User create request") @Valid @RequestBody UserCreateReq req) {
    return Result.success(userService.create(req));
  }

  /** Update user. 更新用户。 */
  @Operation(summary = "Update user", description = "Update an existing user")
  @PutMapping
  public Result<UserVO> update(
      @Parameter(description = "User update request") @Valid @RequestBody UserUpdateReq req) {
    return Result.success(userService.update(req));
  }

  /** Delete user. 删除用户。 */
  @Operation(summary = "Delete user", description = "Delete a user by ID")
  @DeleteMapping("/{id}")
  public Result<Void> delete(@Parameter(description = "User ID") @PathVariable Long id) {
    userService.delete(id);
    return Result.success();
  }

  /** Get user by ID. 根据ID获取用户。 */
  @Operation(summary = "Get user", description = "Get user by ID")
  @GetMapping("/{id}")
  public Result<UserVO> getById(@Parameter(description = "User ID") @PathVariable Long id) {
    return Result.success(userService.getById(id));
  }

  /** Get user page. 获取用户分页列表。 */
  @Operation(summary = "Get user page", description = "Get user page list")
  @GetMapping("/page")
  public Result<Page<UserVO>> getPage(@Parameter(description = "Page request") @Valid PageReq req) {
    return Result.success(userService.getPage(req));
  }

  /** Get user by username. 根据用户名获取用户。 */
  @Operation(summary = "Get user by username", description = "Get user by username")
  @GetMapping("/username/{username}")
  public Result<UserVO> getByUsername(
      @Parameter(description = "Username") @PathVariable String username) {
    return Result.success(userService.getByUsername(username));
  }

  /** Check username exists. 检查用户名是否存在。 */
  @Operation(summary = "Check username exists", description = "Check if username exists")
  @GetMapping("/exists/username/{username}")
  public Result<Boolean> existsByUsername(
      @Parameter(description = "Username") @PathVariable String username) {
    return Result.success(userService.existsByUsername(username));
  }

  /** Check email exists. 检查邮箱是否存在。 */
  @Operation(summary = "Check email exists", description = "Check if email exists")
  @GetMapping("/exists/email/{email}")
  public Result<Boolean> existsByEmail(
      @Parameter(description = "Email") @PathVariable String email) {
    return Result.success(userService.existsByEmail(email));
  }
}
