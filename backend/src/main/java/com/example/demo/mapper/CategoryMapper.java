package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

/** Category mapper. 分类Mapper。 */
@Mapper
public interface CategoryMapper extends BaseMapper<CategoryEntity> {
  // Custom SQL can be defined here
}
