package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.entity.CommentEntity;
import org.apache.ibatis.annotations.Mapper;

/** Comment mapper. 评论Mapper。 */
@Mapper
public interface CommentMapper extends BaseMapper<CommentEntity> {
  // Custom SQL can be defined here
}
