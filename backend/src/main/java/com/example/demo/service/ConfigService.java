package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.domain.dto.req.ConfigCreateReq;
import com.example.demo.domain.dto.req.ConfigUpdateReq;
import com.example.demo.domain.dto.req.PageReq;
import com.example.demo.domain.vo.ConfigVO;
import java.util.List;
import java.util.Map;

/** Config service interface. 配置服务接口。 */
public interface ConfigService {

  /**
   * Create config. 创建配置。
   *
   * @param req Config create request
   * @return Config VO
   */
  ConfigVO create(ConfigCreateReq req);

  /**
   * Update config. 更新配置。
   *
   * @param req Config update request
   * @return Config VO
   */
  ConfigVO update(ConfigUpdateReq req);

  /**
   * Delete config. 删除配置。
   *
   * @param id Config ID
   */
  void delete(Long id);

  /**
   * Get config by ID. 根据ID获取配置。
   *
   * @param id Config ID
   * @return Config VO
   */
  ConfigVO getById(Long id);

  /**
   * Get config by key. 根据键获取配置。
   *
   * @param key Config key
   * @return Config VO
   */
  ConfigVO getByKey(String key);

  /**
   * Get config page. 获取配置分页列表。
   *
   * @param req Page request
   * @return Config page
   */
  Page<ConfigVO> getPage(PageReq req);

  /**
   * Get all configs. 获取所有配置。
   *
   * @return Config list
   */
  List<ConfigVO> getList();

  /**
   * Get configs as map. 获取配置映射。
   *
   * @return Config map
   */
  Map<String, String> getConfigMap();

  /**
   * Get public configs. 获取公共配置。
   *
   * @return Public config list
   */
  List<ConfigVO> getPublicConfigs();

  /**
   * Get public configs as map. 获取公共配置映射。
   *
   * @return Public config map
   */
  Map<String, String> getPublicConfigMap();

  /**
   * Check if config exists. 检查配置是否存在。
   *
   * @param key Config key
   * @return True if exists
   */
  boolean existsByKey(String key);

  /**
   * Get config value. 获取配置值。
   *
   * @param key Config key
   * @return Config value
   */
  String getValue(String key);

  /**
   * Get config value with default. 获取配置值，带默认值。
   *
   * @param key Config key
   * @param defaultValue Default value
   * @return Config value
   */
  String getValue(String key, String defaultValue);

  /**
   * Set config value. 设置配置值。
   *
   * @param key Config key
   * @param value Config value
   */
  void setValue(String key, String value);
}
