package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.domain.dto.req.DemoCreateReq;
import com.example.demo.domain.dto.req.DemoUpdateReq;
import com.example.demo.domain.vo.DemoVO;
import com.example.demo.service.DemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/** Demo controller. Demo 控制器。 */
@Tag(name = "Demo", description = "Demo API")
@RestController
@RequestMapping("/api/v1/demo")
@RequiredArgsConstructor
public class DemoController {

  private final DemoService demoService;

  /** Hello endpoint. Hello 接口。 */
  @Operation(summary = "Hello", description = "Say hello")
  @GetMapping("/hello")
  public Result<String> hello() {
    return Result.success("Hello, World!");
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
