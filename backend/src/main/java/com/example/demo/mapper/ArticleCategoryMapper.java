package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.entity.ArticleCategoryEntity;
import org.apache.ibatis.annotations.Mapper;

/** Article category mapper. 文章分类关联Mapper。 */
@Mapper
public interface ArticleCategoryMapper extends BaseMapper<ArticleCategoryEntity> {}
