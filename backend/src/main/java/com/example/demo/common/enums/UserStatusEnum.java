package com.example.demo.common.enums;

import lombok.Getter;

/** User status enumeration. 用户状态枚举。 */
@Getter
public enum UserStatusEnum {

  /** Inactive. 未激活。 */
  INACTIVE(0, "Inactive"),

  /** Active. 活跃。 */
  ACTIVE(1, "Active"),

  /** Blocked. 已封禁。 */
  BLOCKED(2, "Blocked");

  /** Status value. */
  private final Integer status;

  /** Status description. */
  private final String description;

  UserStatusEnum(Integer status, String description) {
    this.status = status;
    this.description = description;
  }

  /**
   * Get status enum by value. 根据值获取状态枚举。
   *
   * @param status Status value
   * @return UserStatusEnum
   */
  public static UserStatusEnum getByStatus(Integer status) {
    for (UserStatusEnum userStatus : values()) {
      if (userStatus.getStatus().equals(status)) {
        return userStatus;
      }
    }
    return null;
  }
}
