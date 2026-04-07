package com.example.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/** Blog favorite entity. 博客收藏实体。 */
@Data
@TableName("blog_favorite")
public class FavoriteEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /** Favorite ID. 收藏ID。 */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /** User ID. 用户ID。 */
  @TableField("user_id")
  private Long userId;

  /** Article ID. 文章ID。 */
  @TableField("article_id")
  private Long articleId;

  /** Create time. 创建时间。 */
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private LocalDateTime createTime;
}
