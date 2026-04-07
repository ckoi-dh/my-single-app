package com.example.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/** Interact entity. 互动实体。 */
@Data
@TableName("blog_interact")
public class InteractEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /** ID. ID。 */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /** User ID. 用户ID。 */
  @TableField("user_id")
  private Long userId;

  /** Target ID. 目标ID。 */
  @TableField("target_id")
  private Long targetId;

  /** Target type (1-Article, 2-Comment). 目标类型。 */
  @TableField("target_type")
  private Integer targetType;

  /** Create time. 创建时间。 */
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  /** Update time. 更新时间。 */
  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;
}
