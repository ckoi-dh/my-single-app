package com.example.demo.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/** Category update request. 分类更新请求。 */
@Data
@Schema(description = "Category update request")
public class CategoryUpdateReq {

  /** Category ID. 分类ID。 */
  @NotNull(message = "Category ID cannot be null")
  @Schema(description = "Category ID", required = true)
  private Long id;

  /** Category name. 分类名称。 */
  @Size(max = 100, message = "Category name length cannot exceed 100 characters")
  @Schema(description = "Category name")
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
