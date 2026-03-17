package com.example.demo.domain.dto.req;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Demo update request DTO. Demo 更新请求 DTO。 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemoUpdateReq {

  /** Demo ID */
  @NotNull(message = "ID cannot be null")
  private Long id;

  /** Demo name */
  private String name;

  /** Demo description */
  private String description;

  /** Demo status (0-inactive, 1-active) */
  @Min(value = 0, message = "Status must be 0 or 1")
  @Max(value = 1, message = "Status must be 0 or 1")
  private Integer status;
}
