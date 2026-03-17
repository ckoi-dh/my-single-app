package com.example.demo.common.mapstruct;

import com.example.demo.domain.dto.req.DemoCreateReq;
import com.example.demo.domain.entity.DemoEntity;
import com.example.demo.domain.vo.DemoVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/** Demo MapStruct mapper. Demo MapStruct 映射器。 */
@Mapper(componentModel = "spring")
public interface DemoMapstruct {

  DemoMapstruct INSTANCE = Mappers.getMapper(DemoMapstruct.class);

  /** Convert create request DTO to entity. 将创建请求 DTO 转换为实体。 */
  DemoEntity createReqToEntity(DemoCreateReq req);

  /** Convert entity to VO. 将实体转换为 VO。 */
  DemoVO entityToVO(DemoEntity entity);
}
