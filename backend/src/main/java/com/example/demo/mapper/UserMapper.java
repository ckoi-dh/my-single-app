package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/** User mapper. 用户Mapper。 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
  // Custom SQL can be defined here
}
