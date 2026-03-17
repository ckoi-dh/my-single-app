package com.example.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Demo entity. Demo 实体类。 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("demo")
public class DemoEntity {

  /** Demo ID */
  @TableId(type = IdType.AUTO)
  private Long id;

  /** Demo name */
  private String name;

  /** Demo description */
  private String description;

  /** Demo status (0-inactive, 1-active) */
  private Integer status;

  /** Create time */
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  /** Update time */
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  /** Deleted flag (0-not deleted, 1-deleted) */
  @TableLogic private Integer deleted;
}
