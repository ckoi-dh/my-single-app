package com.example.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/** Blog like entity. 博客点赞实体。 */
@Data
@TableName("blog_like")
public class LikeEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /** Like ID. 点赞ID。 */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /** User ID. 用户ID。 */
  @TableField("user_id")
  private Long userId;

  /** Target type (1-article, 2-comment). 目标类型。 */
  @TableField("target_type")
  private Integer targetType;

  /** Target ID. 目标ID。 */
  @TableField("target_id")
  private Long targetId;

  /** Create time. 创建时间。 */
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private LocalDateTime createTime;
}
