package com.example.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/** Blog tag entity. 博客标签实体。 */
@Data
@TableName("blog_tag")
public class TagEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /** Tag ID. 标签ID。 */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /** Tag name. 标签名称。 */
  @TableField("name")
  private String name;

  /** Tag description. 标签描述。 */
  @TableField("description")
  private String description;

  /** Tag color. 标签颜色。 */
  @TableField("color")
  private String color;

  /** Sort order. 排序序号。 */
  @TableField("sort_order")
  private Integer sortOrder;

  /** Tag status (0-inactive, 1-active). 标签状态。 */
  @TableField("status")
  private Integer status;

  /** Create time. 创建时间。 */
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  /** Update time. 更新时间。 */
  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  /** Deleted flag (0-not deleted, 1-deleted). 逻辑删除标记。 */
  @TableField("deleted")
  @TableLogic
  private Integer deleted;
}
