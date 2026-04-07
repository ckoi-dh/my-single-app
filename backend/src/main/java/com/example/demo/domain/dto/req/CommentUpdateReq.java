package com.example.demo.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/** Comment update request. 评论更新请求。 */
@Data
@Schema(description = "Comment update request")
public class CommentUpdateReq {

  /** Comment ID. 评论ID。 */
  @NotNull(message = "Comment ID cannot be null")
  @Schema(description = "Comment ID", required = true)
  private Long id;

  /** Comment content. 评论内容。 */
  @Size(max = 500, message = "Comment content length cannot exceed 500 characters")
  @Schema(description = "Comment content")
  private String content;

  /** Comment status. 评论状态。 */
  @Schema(description = "Comment status")
  private Integer status;
}
