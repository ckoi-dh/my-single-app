package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.entity.SeoMetaEntity;
import org.apache.ibatis.annotations.Mapper;

/** SeoMeta mapper. SEO元数据Mapper。 */
@Mapper
public interface SeoMetaMapper extends BaseMapper<SeoMetaEntity> {
  // Custom SQL can be defined here
}
