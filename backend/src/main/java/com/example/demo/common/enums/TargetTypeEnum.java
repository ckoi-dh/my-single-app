package com.example.demo.common.enums;

import lombok.Getter;

/** Target type enumeration for interactions. 互动目标类型枚举。 */
@Getter
public enum TargetTypeEnum {

  /** Article. 文章。 */
  ARTICLE(1, "Article"),

  /** Comment. 评论。 */
  COMMENT(2, "Comment");

  /** Target type value. */
  private final Integer type;

  /** Target type description. */
  private final String description;

  TargetTypeEnum(Integer type, String description) {
    this.type = type;
    this.description = description;
  }

  /**
   * Get type enum by value. 根据值获取类型枚举。
   *
   * @param type Target type value
   * @return TargetTypeEnum
   */
  public static TargetTypeEnum getByType(Integer type) {
    for (TargetTypeEnum targetType : values()) {
      if (targetType.getType().equals(type)) {
        return targetType;
      }
    }
    return null;
  }
}
