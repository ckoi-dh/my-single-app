package com.example.demo.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Min;
import lombok.Data;

/** Page request base class. 分页请求基类。 */
@Data
@Schema(description = "Page request")
public class PageReq {

  /** Current page number. 当前页码。 */
  @Schema(description = "Current page number", example = "1")
  @Min(value = 1, message = "Page number must be at least 1")
  private Integer current = 1;

  /** Page size. 每页条数。 */
  @Schema(description = "Page size", example = "10")
  @Min(value = 1, message = "Page size must be at least 1")
  private Integer size = 10;
}
