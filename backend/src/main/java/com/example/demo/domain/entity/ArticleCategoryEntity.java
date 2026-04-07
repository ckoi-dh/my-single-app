package com.example.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/** Article category relationship entity. 文章分类关联实体。 */
@Data
@TableName("blog_article_category")
public class ArticleCategoryEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /** ID. ID。 */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /** Article ID. 文章ID。 */
  @TableField("article_id")
  private Long articleId;

  /** Category ID. 分类ID。 */
  @TableField("category_id")
  private Long categoryId;

  /** Create time. 创建时间。 */
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private LocalDateTime createTime;
}
