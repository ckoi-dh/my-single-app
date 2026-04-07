package com.example.demo.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** Article query request. 文章查询请求。 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Article query request")
public class ArticleQueryReq extends PageReq {

  /** Keyword. 关键词。 */
  @Schema(description = "Search keyword")
  private String keyword;

  /** Author ID. 作者ID。 */
  @Schema(description = "Author ID")
  private Long authorId;

  /** Article status. 文章状态。 */
  @Schema(description = "Article status")
  private Integer status;

  /** Category ID. 分类ID。 */
  @Schema(description = "Category ID")
  private Long categoryId;

  /** Tag ID. 标签ID。 */
  @Schema(description = "Tag ID")
  private Long tagId;

  /** Is sticky. 是否置顶。 */
  @Schema(description = "Is sticky")
  private Integer isSticky;

  /** Is featured. 是否推荐。 */
  @Schema(description = "Is featured")
  private Integer isFeatured;

  /** Start date. 开始日期。 */
  @Schema(description = "Start date (yyyy-MM-dd)")
  private String startDate;

  /** End date. 结束日期。 */
  @Schema(description = "End date (yyyy-MM-dd)")
  private String endDate;
}
