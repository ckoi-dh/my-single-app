package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.domain.dto.req.ConfigCreateReq;
import com.example.demo.domain.dto.req.ConfigUpdateReq;
import com.example.demo.domain.dto.req.PageReq;
import com.example.demo.domain.vo.ConfigVO;
import com.example.demo.service.ConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/** Config controller. 配置控制器。 */
@Tag(name = "Config", description = "Config Management API")
@RestController
@RequestMapping("/api/v1/config")
@RequiredArgsConstructor
public class ConfigController {

  private final ConfigService configService;

  /**
   * Create config. 创建配置。
   *
   * @param req Config create request
   * @return Config VO
   */
  @Operation(summary = "Create config", description = "Create a new config")
  @PostMapping
  public Result<ConfigVO> create(
      @Parameter(description = "Config create request") @Valid @RequestBody ConfigCreateReq req) {
    return Result.success(configService.create(req));
  }

  /**
   * Update config. 更新配置。
   *
   * @param req Config update request
   * @return Config VO
   */
  @Operation(summary = "Update config", description = "Update an existing config")
  @PutMapping
  public Result<ConfigVO> update(
      @Parameter(description = "Config update request") @Valid @RequestBody ConfigUpdateReq req) {
    return Result.success(configService.update(req));
  }

  /**
   * Delete config. 删除配置。
   *
   * @param id Config ID
   * @return Success result
   */
  @Operation(summary = "Delete config", description = "Delete a config by ID")
  @DeleteMapping("/{id}")
  public Result<Void> delete(@Parameter(description = "Config ID") @PathVariable Long id) {
    configService.delete(id);
    return Result.success();
  }

  /**
   * Get config by ID. 根据ID获取配置。
   *
   * @param id Config ID
   * @return Config VO
   */
  @Operation(summary = "Get config", description = "Get config by ID")
  @GetMapping("/{id}")
  public Result<ConfigVO> getById(@Parameter(description = "Config ID") @PathVariable Long id) {
    return Result.success(configService.getById(id));
  }

  /**
   * Get config by key. 根据键获取配置。
   *
   * @param key Config key
   * @return Config VO
   */
  @Operation(summary = "Get config by key", description = "Get config by key")
  @GetMapping("/key/{key}")
  public Result<ConfigVO> getByKey(
      @Parameter(description = "Config key") @PathVariable String key) {
    return Result.success(configService.getByKey(key));
  }

  /**
   * Get config page. 获取配置分页列表。
   *
   * @param req Page request
   * @return Config page
   */
  @Operation(summary = "Get config page", description = "Get config page list")
  @GetMapping("/page")
  public Result<Page<ConfigVO>> getPage(
      @Parameter(description = "Page request") @Valid PageReq req) {
    return Result.success(configService.getPage(req));
  }

  /**
   * Get all configs. 获取所有配置。
   *
   * @return Config list
   */
  @Operation(summary = "Get all configs", description = "Get all config list")
  @GetMapping("/list")
  public Result<List<ConfigVO>> getList() {
    return Result.success(configService.getList());
  }

  /**
   * Get configs as map. 获取配置映射。
   *
   * @return Config map
   */
  @Operation(summary = "Get config map", description = "Get config map")
  @GetMapping("/map")
  public Result<Map<String, String>> getConfigMap() {
    return Result.success(configService.getConfigMap());
  }

  /**
   * Get public configs. 获取公共配置。
   *
   * @return Public config list
   */
  @Operation(summary = "Get public configs", description = "Get public config list")
  @GetMapping("/public")
  public Result<List<ConfigVO>> getPublicConfigs() {
    return Result.success(configService.getPublicConfigs());
  }

  /**
   * Get public configs as map. 获取公共配置映射。
   *
   * @return Public config map
   */
  @Operation(summary = "Get public config map", description = "Get public config map")
  @GetMapping("/public/map")
  public Result<Map<String, String>> getPublicConfigMap() {
    return Result.success(configService.getPublicConfigMap());
  }

  /**
   * Get config value. 获取配置值。
   *
   * @param key Config key
   * @return Config value
   */
  @Operation(summary = "Get config value", description = "Get config value by key")
  @GetMapping("/value/{key}")
  public Result<String> getValue(@Parameter(description = "Config key") @PathVariable String key) {
    return Result.success(configService.getValue(key));
  }

  /**
   * Set config value. 设置配置值。
   *
   * @param key Config key
   * @param value Config value
   * @return Success result
   */
  @Operation(summary = "Set config value", description = "Set config value")
  @PutMapping("/value")
  public Result<Void> setValue(
      @Parameter(description = "Config key") @RequestParam String key,
      @Parameter(description = "Config value") @RequestParam String value) {
    configService.setValue(key, value);
    return Result.success();
  }
}
