package com.example.demo.domain.dto.req;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Demo create request DTO. Demo 创建请求 DTO。 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemoCreateReq {

  /** Demo name */
  @NotBlank(message = "Name cannot be blank")
  private String name;

  /** Demo description */
  private String description;

  /** Demo status (0-inactive, 1-active) */
  @Min(value = 0, message = "Status must be 0 or 1")
  @Max(value = 1, message = "Status must be 0 or 1")
  private Integer status;
}
