package com.example.demo.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Data;

/** Favorite create request. 收藏创建请求。 */
@Data
@Schema(description = "Favorite create request")
public class FavoriteCreateReq {

  /** Target ID. 目标ID。 */
  @NotNull(message = "Target ID cannot be null")
  @Schema(description = "Target ID", required = true)
  private Long targetId;

  /** Target type. 目标类型 (1-Article). */
  @Schema(description = "Target type (1-Article)")
  private Integer targetType = 1;
}
