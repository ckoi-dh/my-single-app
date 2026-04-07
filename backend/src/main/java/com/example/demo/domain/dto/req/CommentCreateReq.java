package com.example.demo.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/** Comment create request. 评论创建请求。 */
@Data
@Schema(description = "Comment create request")
public class CommentCreateReq {

  /** Article ID. 文章ID。 */
  @NotNull(message = "Article ID cannot be null")
  @Schema(description = "Article ID", required = true)
  private Long articleId;

  /** Parent comment ID. 父评论ID。 */
  @Schema(description = "Parent comment ID")
  private Long parentId;

  /** Comment content. 评论内容。 */
  @NotBlank(message = "Comment content cannot be blank")
  @Size(max = 500, message = "Comment content length cannot exceed 500 characters")
  @Schema(description = "Comment content", required = true)
  private String content;

  /** Commentator nickname. 评论者昵称。 */
  @Size(max = 100, message = "Nickname length cannot exceed 100 characters")
  @Schema(description = "Commentator nickname")
  private String nickname;

  /** Commentator email. 评论者邮箱。 */
  @Size(max = 100, message = "Email length cannot exceed 100 characters")
  @Schema(description = "Commentator email")
  private String email;
}
