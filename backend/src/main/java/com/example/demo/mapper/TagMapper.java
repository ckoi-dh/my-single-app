package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.entity.TagEntity;
import org.apache.ibatis.annotations.Mapper;

/** Tag mapper. 标签Mapper。 */
@Mapper
public interface TagMapper extends BaseMapper<TagEntity> {
  // Custom SQL can be defined here
}
