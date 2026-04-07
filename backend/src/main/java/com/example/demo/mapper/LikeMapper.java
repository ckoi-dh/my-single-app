package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.entity.LikeEntity;
import org.apache.ibatis.annotations.Mapper;

/** Like mapper. 点赞Mapper。 */
@Mapper
public interface LikeMapper extends BaseMapper<LikeEntity> {
  // Custom SQL can be defined here
}
