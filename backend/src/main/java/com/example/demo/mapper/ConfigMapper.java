package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.entity.ConfigEntity;
import org.apache.ibatis.annotations.Mapper;

/** Config mapper. 配置Mapper。 */
@Mapper
public interface ConfigMapper extends BaseMapper<ConfigEntity> {
  // Custom SQL can be defined here
}
