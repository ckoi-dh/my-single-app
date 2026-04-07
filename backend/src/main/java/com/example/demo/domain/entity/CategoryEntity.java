package com.example.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/** Blog category entity. 博客分类实体。 */
@Data
@TableName("blog_category")
public class CategoryEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /** Category ID. 分类ID。 */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /** Category name. 分类名称。 */
  @TableField("name")
  private String name;

  /** Category description. 分类描述。 */
  @TableField("description")
  private String description;

  /** Parent category ID. 父分类ID。 */
  @TableField("parent_id")
  private Long parentId;

  /** Sort order. 排序序号。 */
  @TableField("sort_order")
  private Integer sortOrder;

  /** Category status (0-inactive, 1-active). 分类状态。 */
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
