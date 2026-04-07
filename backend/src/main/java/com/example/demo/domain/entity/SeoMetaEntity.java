package com.example.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/** Blog SEO meta entity. 博客SEO元数据实体。 */
@Data
@TableName("blog_seo_meta")
public class SeoMetaEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /** SEO meta ID. SEO元数据ID。 */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /** Page name. 页面名称。 */
  @TableField("page_name")
  private String pageName;

  /** Page path. 页面路径。 */
  @TableField("page_path")
  private String pagePath;

  /** SEO title. SEO标题。 */
  @TableField("title")
  private String title;

  /** SEO keywords. SEO关键词。 */
  @TableField("keywords")
  private String keywords;

  /** SEO description. SEO描述。 */
  @TableField("description")
  private String description;

  /** Open Graph title. Open Graph标题。 */
  @TableField("og_title")
  private String ogTitle;

  /** Open Graph description. Open Graph描述。 */
  @TableField("og_description")
  private String ogDescription;

  /** Open Graph image. Open Graph图片。 */
  @TableField("og_image")
  private String ogImage;

  /** Twitter title. Twitter标题。 */
  @TableField("twitter_title")
  private String twitterTitle;

  /** Twitter description. Twitter描述。 */
  @TableField("twitter_description")
  private String twitterDescription;

  /** Twitter image. Twitter图片。 */
  @TableField("twitter_image")
  private String twitterImage;

  /** Status (0-inactive, 1-active). 状态。 */
  @TableField("status")
  private Integer status;

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
