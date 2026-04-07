package com.example.demo.common.enums;

import lombok.Getter;

/** Article status enumeration. 文章状态枚举。 */
@Getter
public enum ArticleStatusEnum {

  /** Draft. 草稿。 */
  DRAFT(0, "Draft"),

  /** Published. 已发布。 */
  PUBLISHED(1, "Published"),

  /** Private. 私密。 */
  PRIVATE(2, "Private"),

  /** Scheduled. 定时发布。 */
  SCHEDULED(3, "Scheduled"),

  /** Banned. 已封禁。 */
  BANNED(4, "Banned");

  /** Status value. */
  private final Integer status;

  /** Status description. */
  private final String description;

  ArticleStatusEnum(Integer status, String description) {
    this.status = status;
    this.description = description;
  }

  /**
   * Get status enum by value. 根据值获取状态枚举。
   *
   * @param status Status value
   * @return ArticleStatusEnum
   */
  public static ArticleStatusEnum getByStatus(Integer status) {
    for (ArticleStatusEnum articleStatus : values()) {
      if (articleStatus.getStatus().equals(status)) {
        return articleStatus;
      }
    }
    return null;
  }

  /**
   * Check if status is published. 检查是否已发布。
   *
   * @return true if published
   */
  public boolean isPublished() {
    return this == PUBLISHED;
  }

  /**
   * Check if status is draft. 检查是否是草稿。
   *
   * @return true if draft
   */
  public boolean isDraft() {
    return this == DRAFT;
  }

  /**
   * Check if status is private. 检查是否是私密。
   *
   * @return true if private
   */
  public boolean isPrivate() {
    return this == PRIVATE;
  }

  /**
   * Check if status is scheduled. 检查是否是定时发布。
   *
   * @return true if scheduled
   */
  public boolean isScheduled() {
    return this == SCHEDULED;
  }

  /**
   * Check if status is banned. 检查是否是封禁状态。
   *
   * @return true if banned
   */
  public boolean isBanned() {
    return this == BANNED;
  }
}
