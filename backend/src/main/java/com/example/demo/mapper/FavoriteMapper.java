package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.entity.FavoriteEntity;
import org.apache.ibatis.annotations.Mapper;

/** Favorite mapper. 收藏Mapper。 */
@Mapper
public interface FavoriteMapper extends BaseMapper<FavoriteEntity> {
  // Custom SQL can be defined here
}
