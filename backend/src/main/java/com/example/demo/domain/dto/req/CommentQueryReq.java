package com.example.demo.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** Comment query request. 评论查询请求。 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Comment query request")
public class CommentQueryReq extends PageReq {

  /** Article ID. 文章ID。 */
  @Schema(description = "Article ID")
  private Long articleId;

  /** Commentator ID. 评论者ID。 */
  @Schema(description = "Commentator ID")
  private Long commentatorId;

  /** Comment status. 评论状态。 */
  @Schema(description = "Comment status")
  private Integer status;

  /** Is root comment. 是否为根评论。 */
  @Schema(description = "Is root comment")
  private Boolean isRoot;
}
