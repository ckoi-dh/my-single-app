package com.example.demo.common.enums;

import lombok.Getter;

/** User role enumeration. 用户角色枚举。 */
@Getter
public enum UserRoleEnum {

  /** Super administrator. 超级管理员。 */
  SUPER_ADMIN(1, "SUPER_ADMIN", "Super Administrator"),

  /** Administrator. 管理员。 */
  ADMIN(2, "ADMIN", "Administrator"),

  /** Author. 作者。 */
  AUTHOR(3, "AUTHOR", "Author"),

  /** Visitor. 访客。 */
  VISITOR(4, "VISITOR", "Visitor");

  /** Role type value. */
  private final Integer type;

  /** Role code. */
  private final String code;

  /** Role description. */
  private final String description;

  UserRoleEnum(Integer type, String code, String description) {
    this.type = type;
    this.code = code;
    this.description = description;
  }

  /**
   * Get role enum by type. 根据类型获取角色枚举。
   *
   * @param type Role type
   * @return UserRoleEnum
   */
  public static UserRoleEnum getByType(Integer type) {
    for (UserRoleEnum role : values()) {
      if (role.getType().equals(type)) {
        return role;
      }
    }
    return null;
  }

  /**
   * Get role enum by code. 根据代码获取角色枚举。
   *
   * @param code Role code
   * @return UserRoleEnum
   */
  public static UserRoleEnum getByCode(String code) {
    for (UserRoleEnum role : values()) {
      if (role.getCode().equals(code)) {
        return role;
      }
    }
    return null;
  }
}
