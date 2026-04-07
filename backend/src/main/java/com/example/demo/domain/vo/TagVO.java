package com.example.demo.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

/** Tag view object. 标签视图对象。 */
@Data
@Schema(description = "Tag view object")
public class TagVO {

  /** Tag ID. 标签ID。 */
  @Schema(description = "Tag ID")
  private Long id;

  /** Tag name. 标签名称。 */
  @Schema(description = "Tag name")
  private String name;

  /** Tag slug. 标签别名。 */
  @Schema(description = "Tag slug")
  private String slug;

  /** Tag description. 标签描述。 */
  @Schema(description = "Tag description")
  private String description;

  /** Article count. 文章数量。 */
  @Schema(description = "Article count")
  private Long articleCount;

  /** Create time. 创建时间。 */
  @Schema(description = "Create time")
  private LocalDateTime createTime;

  /** Update time. 更新时间。 */
  @Schema(description = "Update time")
  private LocalDateTime updateTime;
}
