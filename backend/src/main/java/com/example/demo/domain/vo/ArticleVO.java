package com.example.demo.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

/** Article view object. 文章视图对象。 */
@Data
@Schema(description = "Article view object")
public class ArticleVO {

  /** Article ID. 文章ID。 */
  @Schema(description = "Article ID")
  private Long id;

  /** Article title. 文章标题。 */
  @Schema(description = "Article title")
  private String title;

  /** Article summary. 文章摘要。 */
  @Schema(description = "Article summary")
  private String summary;

  /** Article content. 文章内容。 */
  @Schema(description = "Article content")
  private String content;

  /** Cover image URL. 封面图片URL。 */
  @Schema(description = "Cover image URL")
  private String coverImage;

  /** Author ID. 作者ID。 */
  @Schema(description = "Author ID")
  private Long authorId;

  /** Author name. 作者名称。 */
  @Schema(description = "Author name")
  private String authorName;

  /** Article status. 文章状态。 */
  @Schema(description = "Article status")
  private Integer status;

  /** Article status label. 文章状态标签。 */
  @Schema(description = "Article status label")
  private String statusLabel;

  /** Publish time. 发布时间。 */
  @Schema(description = "Publish time")
  private LocalDateTime publishTime;

  /** View count. 浏览次数。 */
  @Schema(description = "View count")
  private Long viewCount;

  /** Like count. 点赞次数。 */
  @Schema(description = "Like count")
  private Long likeCount;

  /** Comment count. 评论次数。 */
  @Schema(description = "Comment count")
  private Long commentCount;

  /** Favorite count. 收藏次数。 */
  @Schema(description = "Favorite count")
  private Long favoriteCount;

  /** Share count. 分享次数。 */
  @Schema(description = "Share count")
  private Long shareCount;

  /** SEO title. SEO标题。 */
  @Schema(description = "SEO title")
  private String seoTitle;

  /** SEO keywords. SEO关键词。 */
  @Schema(description = "SEO keywords")
  private String seoKeywords;

  /** SEO description. SEO描述。 */
  @Schema(description = "SEO description")
  private String seoDescription;

  /** Is sticky. 是否置顶。 */
  @Schema(description = "Is sticky")
  private Integer isSticky;

  /** Is featured. 是否推荐。 */
  @Schema(description = "Is featured")
  private Integer isFeatured;

  /** Create time. 创建时间。 */
  @Schema(description = "Create time")
  private LocalDateTime createTime;

  /** Update time. 更新时间。 */
  @Schema(description = "Update time")
  private LocalDateTime updateTime;

  /** Categories. 分类列表。 */
  @Schema(description = "Categories")
  private List<CategoryVO> categories;

  /** Tags. 标签列表。 */
  @Schema(description = "Tags")
  private List<TagVO> tags;
}
