package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.entity.DemoEntity;
import org.apache.ibatis.annotations.Mapper;

/** Demo mapper. Demo Mapper。 */
@Mapper
public interface DemoMapper extends BaseMapper<DemoEntity> {}
