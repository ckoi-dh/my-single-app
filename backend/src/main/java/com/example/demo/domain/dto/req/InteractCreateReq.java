package com.example.demo.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Data;

/** Interact create request. 互动创建请求。 */
@Data
@Schema(description = "Interact create request")
public class InteractCreateReq {

  /** Target ID. 目标ID。 */
  @NotNull(message = "Target ID cannot be null")
  @Schema(description = "Target ID", required = true)
  private Long targetId;

  /** Target type. 目标类型 (1-Article, 2-Comment). */
  @NotNull(message = "Target type cannot be null")
  @Schema(description = "Target type (1-Article, 2-Comment)", required = true)
  private Integer targetType;
}
