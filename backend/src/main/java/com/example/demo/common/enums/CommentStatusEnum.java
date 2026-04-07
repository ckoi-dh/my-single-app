package com.example.demo.common.enums;

import lombok.Getter;

/** Comment status enumeration. 评论状态枚举。 */
@Getter
public enum CommentStatusEnum {

  /** Pending. 待审核。 */
  PENDING(0, "Pending"),

  /** Approved. 已通过。 */
  APPROVED(1, "Approved"),

  /** Rejected. 已拒绝。 */
  REJECTED(2, "Rejected");

  /** Status value. */
  private final Integer status;

  /** Status description. */
  private final String description;

  CommentStatusEnum(Integer status, String description) {
    this.status = status;
    this.description = description;
  }

  /**
   * Get status enum by value. 根据值获取状态枚举。
   *
   * @param status Status value
   * @return CommentStatusEnum
   */
  public static CommentStatusEnum getByStatus(Integer status) {
    for (CommentStatusEnum commentStatus : values()) {
      if (commentStatus.getStatus().equals(status)) {
        return commentStatus;
      }
    }
    return null;
  }

  /**
   * Check if status is pending. 检查是否待审核。
   *
   * @return true if pending
   */
  public boolean isPending() {
    return this == PENDING;
  }

  /**
   * Check if status is approved. 检查是否已通过。
   *
   * @return true if approved
   */
  public boolean isApproved() {
    return this == APPROVED;
  }

  /**
   * Check if status is rejected. 检查是否已拒绝。
   *
   * @return true if rejected
   */
  public boolean isRejected() {
    return this == REJECTED;
  }
}
