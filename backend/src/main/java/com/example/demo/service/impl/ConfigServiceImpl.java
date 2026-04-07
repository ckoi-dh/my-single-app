package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.domain.dto.req.ConfigCreateReq;
import com.example.demo.domain.dto.req.ConfigUpdateReq;
import com.example.demo.domain.dto.req.PageReq;
import com.example.demo.domain.entity.ConfigEntity;
import com.example.demo.domain.vo.ConfigVO;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.ConfigMapper;
import com.example.demo.service.ConfigService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Config service implementation. 配置服务实现。 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService {

  private final ConfigMapper configMapper;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public ConfigVO create(ConfigCreateReq req) {
    if (existsByKey(req.getConfigKey())) {
      throw new BusinessException("Config key already exists");
    }

    ConfigEntity entity = new ConfigEntity();
    entity.setConfigKey(req.getConfigKey());
    entity.setConfigValue(req.getConfigValue());
    entity.setDescription(req.getDescription());
    entity.setStatus(1);

    configMapper.insert(entity);
    log.info("Created config: {}", entity.getConfigKey());

    return getById(entity.getId());
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public ConfigVO update(ConfigUpdateReq req) {
    ConfigEntity entity = configMapper.selectById(req.getId());
    if (entity == null) {
      throw new BusinessException("Config not found");
    }

    if (StringUtils.isNotBlank(req.getConfigKey())
        && !req.getConfigKey().equals(entity.getConfigKey())) {
      if (existsByKey(req.getConfigKey())) {
        throw new BusinessException("Config key already exists");
      }
      entity.setConfigKey(req.getConfigKey());
    }

    if (StringUtils.isNotBlank(req.getConfigValue())) {
      entity.setConfigValue(req.getConfigValue());
    }
    if (StringUtils.isNotBlank(req.getDescription())) {
      entity.setDescription(req.getDescription());
    }

    configMapper.updateById(entity);
    log.info("Updated config: {}", entity.getConfigKey());

    return getById(entity.getId());
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void delete(Long id) {
    ConfigEntity entity = configMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Config not found");
    }

    configMapper.deleteById(id);
    log.info("Deleted config: {}", entity.getConfigKey());
  }

  @Override
  public ConfigVO getById(Long id) {
    ConfigEntity entity = configMapper.selectById(id);
    if (entity == null) {
      throw new BusinessException("Config not found");
    }
    return convertToVO(entity);
  }

  @Override
  public ConfigVO getByKey(String key) {
    LambdaQueryWrapper<ConfigEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(ConfigEntity::getConfigKey, key);

    ConfigEntity entity = configMapper.selectOne(wrapper);
    if (entity == null) {
      throw new BusinessException("Config not found");
    }
    return convertToVO(entity);
  }

  @Override
  public Page<ConfigVO> getPage(PageReq req) {
    Page<ConfigEntity> page = new Page<>(req.getCurrent(), req.getSize());

    LambdaQueryWrapper<ConfigEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.orderByAsc(ConfigEntity::getCreateTime);

    Page<ConfigEntity> entityPage = configMapper.selectPage(page, wrapper);
    return convertToVOPage(entityPage);
  }

  @Override
  public List<ConfigVO> getList() {
    LambdaQueryWrapper<ConfigEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.orderByAsc(ConfigEntity::getCreateTime);

    List<ConfigEntity> entities = configMapper.selectList(wrapper);
    return entities.stream().map(this::convertToVO).collect(Collectors.toList());
  }

  @Override
  public Map<String, String> getConfigMap() {
    return getList().stream()
        .collect(
            Collectors.toMap(ConfigVO::getConfigKey, ConfigVO::getConfigValue, (v1, v2) -> v1));
  }

  @Override
  public List<ConfigVO> getPublicConfigs() {
    return getList();
  }

  @Override
  public Map<String, String> getPublicConfigMap() {
    return getConfigMap();
  }

  @Override
  public boolean existsByKey(String key) {
    LambdaQueryWrapper<ConfigEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(ConfigEntity::getConfigKey, key);

    return configMapper.selectCount(wrapper) > 0;
  }

  @Override
  public String getValue(String key) {
    return getValue(key, null);
  }

  @Override
  public String getValue(String key, String defaultValue) {
    LambdaQueryWrapper<ConfigEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(ConfigEntity::getConfigKey, key);

    ConfigEntity entity = configMapper.selectOne(wrapper);
    return entity != null ? entity.getConfigValue() : defaultValue;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void setValue(String key, String value) {
    LambdaQueryWrapper<ConfigEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(ConfigEntity::getConfigKey, key);

    ConfigEntity entity = configMapper.selectOne(wrapper);
    if (entity == null) {
      ConfigCreateReq req = new ConfigCreateReq();
      req.setConfigKey(key);
      req.setConfigValue(value);
      req.setDescription("");
      create(req);
    } else {
      entity.setConfigValue(value);
      configMapper.updateById(entity);
    }
  }

  private ConfigVO convertToVO(ConfigEntity entity) {
    ConfigVO vo = new ConfigVO();
    vo.setId(entity.getId());
    vo.setConfigKey(entity.getConfigKey());
    vo.setConfigValue(entity.getConfigValue());
    vo.setDescription(entity.getDescription());
    vo.setIsSystem(false);
    vo.setIsPublic(true);
    vo.setCreateTime(entity.getCreateTime());
    vo.setUpdateTime(entity.getUpdateTime());
    return vo;
  }

  private Page<ConfigVO> convertToVOPage(Page<ConfigEntity> entityPage) {
    Page<ConfigVO> voPage =
        new Page<>(entityPage.getCurrent(), entityPage.getSize(), entityPage.getTotal());
    voPage.setRecords(
        entityPage.getRecords().stream().map(this::convertToVO).collect(Collectors.toList()));
    return voPage;
  }
}
