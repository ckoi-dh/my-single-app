package com.example.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/** Blog comment entity. 博客评论实体。 */
@Data
@TableName("blog_comment")
public class CommentEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /** Comment ID. 评论ID。 */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /** Article ID. 文章ID。 */
  @TableField("article_id")
  private Long articleId;

  /** User ID. 用户ID。 */
  @TableField("user_id")
  private Long userId;

  /** Parent comment ID. 父评论ID。 */
  @TableField("parent_id")
  private Long parentId;

  /** Comment content. 评论内容。 */
  @TableField("content")
  private String content;

  /** Comment status (0-pending, 1-approved, 2-rejected). 评论状态。 */
  @TableField("status")
  private Integer status;

  /** Like count. 点赞次数。 */
  @TableField("like_count")
  private Long likeCount;

  /** Reply count. 回复次数。 */
  @TableField("reply_count")
  private Long replyCount;

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
