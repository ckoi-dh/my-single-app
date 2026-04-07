package com.example.demo.common.mapstruct;

import com.example.demo.domain.dto.req.UserCreateReq;
import com.example.demo.domain.dto.req.UserUpdateReq;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.domain.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/** User MapStruct mapper. 用户MapStruct映射器。 */
@Mapper(componentModel = "spring")
public interface UserMapstruct {

  /**
   * Convert entity to VO. 将实体转换为VO。
   *
   * @param entity User entity
   * @return UserVO
   */
  @Mapping(target = "roleName", expression = "java(getRoleName(entity.getRoleType()))")
  UserVO toVO(UserEntity entity);

  /**
   * Convert create request to entity. 将创建请求转换为实体。
   *
   * @param req User create request
   * @return UserEntity
   */
  UserEntity toEntity(UserCreateReq req);

  /**
   * Update entity from update request. 从更新请求更新实体。
   *
   * @param req User update request
   * @param entity User entity to update
   */
  void updateEntity(@MappingTarget UserEntity entity, UserUpdateReq req);

  /**
   * Get role name from role type. 从角色类型获取角色名称。
   *
   * @param roleType Role type
   * @return Role name
   */
  default String getRoleName(Integer roleType) {
    if (roleType == null) {
      return "";
    }
    switch (roleType) {
      case 1:
        return "Super Admin";
      case 2:
        return "Admin";
      case 3:
        return "Author";
      case 4:
        return "Visitor";
      default:
        return "Unknown";
    }
  }
}
