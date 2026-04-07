package com.example.demo.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

/** Category create request. 分类创建请求。 */
@Data
@Schema(description = "Category create request")
public class CategoryCreateReq {

  /** Category name. 分类名称。 */
  @NotBlank(message = "Category name cannot be blank")
  @Size(max = 100, message = "Category name length cannot exceed 100 characters")
  @Schema(description = "Category name", required = true)
  private String name;

  /** Category description. 分类描述。 */
  @Size(max = 500, message = "Category description length cannot exceed 500 characters")
  @Schema(description = "Category description")
  private String description;

  /** Parent category ID. 父分类ID。 */
  @Schema(description = "Parent category ID")
  private Long parentId;

  /** Sort order. 排序。 */
  @Schema(description = "Sort order")
  private Integer sortOrder;
}
