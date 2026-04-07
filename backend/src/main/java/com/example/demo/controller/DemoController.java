package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.domain.dto.req.DemoCreateReq;
import com.example.demo.domain.dto.req.DemoUpdateReq;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.domain.vo.DemoVO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.DemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/** Demo controller. Demo 控制器。 */
@Tag(name = "Demo", description = "Demo API")
@RestController
@RequestMapping("/api/v1/demo")
@RequiredArgsConstructor
public class DemoController {

  private final DemoService demoService;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  /** Hello endpoint. Hello 接口。 */
  @Operation(summary = "Hello", description = "Say hello")
  @GetMapping("/hello")
  public Result<String> hello() {
    return Result.success("Hello, World!");
  }

  /**
   * Reset default user passwords. 重置默认用户密码。 This is a temporary endpoint for development purposes.
   * 仅用于开发阶段的临时接口。
   */
  @Operation(summary = "Reset passwords", description = "Reset admin and testuser passwords")
  @PostMapping("/reset-passwords")
  public Result<Void> resetPasswords() {
    // Reset admin password to admin123
    UserEntity admin = userMapper.selectById(1L);
    if (admin != null) {
      admin.setPassword(passwordEncoder.encode("admin123"));
      userMapper.updateById(admin);
    }

    // Reset testuser password to user123
    UserEntity testUser = userMapper.selectById(2L);
    if (testUser != null) {
      testUser.setPassword(passwordEncoder.encode("user123"));
      userMapper.updateById(testUser);
    }

    return Result.success();
  }

  /** Create demo. 创建 Demo。 */
  @Operation(summary = "Create demo", description = "Create a new demo")
  @PostMapping
  public Result<DemoVO> create(
      @Parameter(description = "Demo create request") @Valid @RequestBody DemoCreateReq req) {
    return Result.success(demoService.create(req));
  }

  /** Update demo. 更新 Demo。 */
  @Operation(summary = "Update demo", description = "Update an existing demo")
  @PutMapping
  public Result<DemoVO> update(
      @Parameter(description = "Demo update request") @Valid @RequestBody DemoUpdateReq req) {
    return Result.success(demoService.update(req));
  }

  /** Delete demo. 删除 Demo。 */
  @Operation(summary = "Delete demo", description = "Delete a demo by ID")
  @DeleteMapping("/{id}")
  public Result<Void> delete(@Parameter(description = "Demo ID") @PathVariable Long id) {
    demoService.delete(id);
    return Result.success();
  }

  /** Get demo by ID. 根据 ID 获取 Demo。 */
  @Operation(summary = "Get demo", description = "Get demo by ID")
  @GetMapping("/{id}")
  public Result<DemoVO> getById(@Parameter(description = "Demo ID") @PathVariable Long id) {
    return Result.success(demoService.getById(id));
  }

  /** Get demo page. 获取 Demo 分页列表。 */
  @Operation(summary = "Get demo page", description = "Get demo page list")
  @GetMapping("/page")
  public Result<Page<DemoVO>> getPage(
      @Parameter(description = "Page number") @RequestParam(defaultValue = "1") Integer pageNum,
      @Parameter(description = "Page size") @RequestParam(defaultValue = "10") Integer pageSize) {
    return Result.success(demoService.getPage(pageNum, pageSize));
  }
}
