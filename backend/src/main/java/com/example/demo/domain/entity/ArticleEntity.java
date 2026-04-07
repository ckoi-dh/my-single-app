package com.example.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/** Blog article entity. 博客文章实体。 */
@Data
@TableName("blog_article")
public class ArticleEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /** Article ID. 文章ID。 */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /** Article title. 文章标题。 */
  @TableField("title")
  private String title;

  /** Article summary. 文章摘要。 */
  @TableField("summary")
  private String summary;

  /** Article content. 文章内容。 */
  @TableField("content")
  private String content;

  /** Cover image URL. 封面图片URL。 */
  @TableField("cover_image")
  private String coverImage;

  /** Author ID. 作者ID。 */
  @TableField("author_id")
  private Long authorId;

  /** Article status (0-draft, 1-published, 2-private, 3-scheduled, 4-banned). 文章状态。 */
  @TableField("status")
  private Integer status;

  /** Publish time. 发布时间。 */
  @TableField("publish_time")
  private LocalDateTime publishTime;

  /** View count. 浏览次数。 */
  @TableField("view_count")
  private Long viewCount;

  /** Like count. 点赞次数。 */
  @TableField("like_count")
  private Long likeCount;

  /** Comment count. 评论次数。 */
  @TableField("comment_count")
  private Long commentCount;

  /** Favorite count. 收藏次数。 */
  @TableField("favorite_count")
  private Long favoriteCount;

  /** Share count. 分享次数。 */
  @TableField("share_count")
  private Long shareCount;

  /** SEO title. SEO标题。 */
  @TableField("seo_title")
  private String seoTitle;

  /** SEO keywords. SEO关键词。 */
  @TableField("seo_keywords")
  private String seoKeywords;

  /** SEO description. SEO描述。 */
  @TableField("seo_description")
  private String seoDescription;

  /** Is sticky (0-no, 1-yes). 是否置顶。 */
  @TableField("is_sticky")
  private Integer isSticky;

  /** Is featured (0-no, 1-yes). 是否推荐。 */
  @TableField("is_featured")
  private Integer isFeatured;

  /** Create time. 创建时间。 */
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  /** Update time. 更新时间。 */
  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  /** Deleted flag (0-not deleted, 1-deleted). 逻辑删除标记。 */
  @TableField("deleted")
  @TableLogic
  private Integer deleted;
}
