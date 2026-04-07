package com.example.demo.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

/** Article create request. 文章创建请求。 */
@Data
@Schema(description = "Article create request")
public class ArticleCreateReq {

  /** Article title. 文章标题。 */
  @NotBlank(message = "Title cannot be blank")
  @Size(max = 200, message = "Title length cannot exceed 200 characters")
  @Schema(description = "Article title", required = true)
  private String title;

  /** Article summary. 文章摘要。 */
  @Size(max = 500, message = "Summary length cannot exceed 500 characters")
  @Schema(description = "Article summary")
  private String summary;

  /** Article content. 文章内容。 */
  @NotBlank(message = "Content cannot be blank")
  @Schema(description = "Article content", required = true)
  private String content;

  /** Cover image URL. 封面图片URL。 */
  @Size(max = 500, message = "Cover image URL length cannot exceed 500 characters")
  @Schema(description = "Cover image URL")
  private String coverImage;

  /** Article status. 文章状态。 */
  @Schema(description = "Article status (0-draft, 1-published, 2-private)")
  private Integer status;

  /** SEO title. SEO标题。 */
  @Size(max = 200, message = "SEO title length cannot exceed 200 characters")
  @Schema(description = "SEO title")
  private String seoTitle;

  /** SEO keywords. SEO关键词。 */
  @Size(max = 500, message = "SEO keywords length cannot exceed 500 characters")
  @Schema(description = "SEO keywords")
  private String seoKeywords;

  /** SEO description. SEO描述。 */
  @Size(max = 500, message = "SEO description length cannot exceed 500 characters")
  @Schema(description = "SEO description")
  private String seoDescription;

  /** Category IDs. 分类ID列表。 */
  @Schema(description = "Category IDs")
  private java.util.List<Long> categoryIds;

  /** Tag IDs. 标签ID列表。 */
  @Schema(description = "Tag IDs")
  private java.util.List<Long> tagIds;

  /** Tag names (for creating new tags). 标签名称（用于创建新标签）。 */
  @Schema(description = "Tag names (for creating new tags)")
  private java.util.List<String> tagNames;
}
