package com.example.demo.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

/** Comment view object. 评论视图对象。 */
@Data
@Schema(description = "Comment view object")
public class CommentVO {

  /** Comment ID. 评论ID。 */
  @Schema(description = "Comment ID")
  private Long id;

  /** Article ID. 文章ID。 */
  @Schema(description = "Article ID")
  private Long articleId;

  /** Article title. 文章标题。 */
  @Schema(description = "Article title")
  private String articleTitle;

  /** Parent comment ID. 父评论ID。 */
  @Schema(description = "Parent comment ID")
  private Long parentId;

  /** Comment content. 评论内容。 */
  @Schema(description = "Comment content")
  private String content;

  /** Commentator ID. 评论者ID。 */
  @Schema(description = "Commentator ID")
  private Long commentatorId;

  /** Commentator nickname. 评论者昵称。 */
  @Schema(description = "Commentator nickname")
  private String nickname;

  /** Commentator email. 评论者邮箱。 */
  @Schema(description = "Commentator email")
  private String email;

  /** Commentator avatar. 评论者头像。 */
  @Schema(description = "Commentator avatar")
  private String avatar;

  /** Comment status. 评论状态。 */
  @Schema(description = "Comment status")
  private Integer status;

  /** Comment status label. 评论状态标签。 */
  @Schema(description = "Comment status label")
  private String statusLabel;

  /** Like count. 点赞次数。 */
  @Schema(description = "Like count")
  private Long likeCount;

  /** Reply count. 回复次数。 */
  @Schema(description = "Reply count")
  private Long replyCount;

  /** Create time. 创建时间。 */
  @Schema(description = "Create time")
  private LocalDateTime createTime;

  /** Update time. 更新时间。 */
  @Schema(description = "Update time")
  private LocalDateTime updateTime;

  /** Children comments. 子评论。 */
  @Schema(description = "Children comments")
  private List<CommentVO> children;
}
