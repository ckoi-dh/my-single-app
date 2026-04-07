package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.entity.ArticleTagEntity;
import org.apache.ibatis.annotations.Mapper;

/** Article tag mapper. 文章标签关联Mapper。 */
@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTagEntity> {}
