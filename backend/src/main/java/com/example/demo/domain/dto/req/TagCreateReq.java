package com.example.demo.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

/** Tag create request. 标签创建请求。 */
@Data
@Schema(description = "Tag create request")
public class TagCreateReq {

  /** Tag name. 标签名称。 */
  @NotBlank(message = "Tag name cannot be blank")
  @Size(max = 100, message = "Tag name length cannot exceed 100 characters")
  @Schema(description = "Tag name", required = true)
  private String name;

  /** Tag description. 标签描述。 */
  @Size(max = 500, message = "Tag description length cannot exceed 500 characters")
  @Schema(description = "Tag description")
  private String description;

  /** Tag color. 标签颜色。 */
  @Size(max = 7, message = "Tag color should be in #RRGGBB format")
  @Schema(description = "Tag color (hex format)")
  private String color;

  /** Sort order. 排序。 */
  @Schema(description = "Sort order")
  private Integer sortOrder;
}
