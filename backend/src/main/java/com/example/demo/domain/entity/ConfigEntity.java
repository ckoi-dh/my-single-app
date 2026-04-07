package com.example.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/** Blog config entity. 博客配置实体。 */
@Data
@TableName("blog_config")
public class ConfigEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /** Config ID. 配置ID。 */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /** Config key. 配置键。 */
  @TableField("config_key")
  private String configKey;

  /** Config value. 配置值。 */
  @TableField("config_value")
  private String configValue;

  /** Config type (string, number, boolean, json). 配置类型。 */
  @TableField("config_type")
  private String configType;

  /** Config description. 配置描述。 */
  @TableField("description")
  private String description;

  /** Sort order. 排序序号。 */
  @TableField("sort_order")
  private Integer sortOrder;

  /** Config status (0-inactive, 1-active). 配置状态。 */
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
