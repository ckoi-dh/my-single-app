package com.example.demo.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

/** Category view object. 分类视图对象。 */
@Data
@Schema(description = "Category view object")
public class CategoryVO {

  /** Category ID. 分类ID。 */
  @Schema(description = "Category ID")
  private Long id;

  /** Category name. 分类名称。 */
  @Schema(description = "Category name")
  private String name;

  /** Category slug. 分类别名。 */
  @Schema(description = "Category slug")
  private String slug;

  /** Category description. 分类描述。 */
  @Schema(description = "Category description")
  private String description;

  /** Parent category ID. 父分类ID。 */
  @Schema(description = "Parent category ID")
  private Long parentId;

  /** Sort order. 排序。 */
  @Schema(description = "Sort order")
  private Integer sortOrder;

  /** Article count. 文章数量。 */
  @Schema(description = "Article count")
  private Long articleCount;

  /** Create time. 创建时间。 */
  @Schema(description = "Create time")
  private LocalDateTime createTime;

  /** Update time. 更新时间。 */
  @Schema(description = "Update time")
  private LocalDateTime updateTime;

  /** Children categories. 子分类列表。 */
  @Schema(description = "Children categories")
  private List<CategoryVO> children;
}
