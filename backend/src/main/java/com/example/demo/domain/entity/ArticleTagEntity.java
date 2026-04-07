package com.example.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/** Article tag relationship entity. 文章标签关联实体。 */
@Data
@TableName("blog_article_tag")
public class ArticleTagEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /** ID. ID。 */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /** Article ID. 文章ID。 */
  @TableField("article_id")
  private Long articleId;

  /** Tag ID. 标签ID。 */
  @TableField("tag_id")
  private Long tagId;

  /** Create time. 创建时间。 */
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private LocalDateTime createTime;
}
