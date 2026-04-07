package com.example.demo.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

/** Interact view object. 互动视图对象。 */
@Data
@Schema(description = "Interact view object")
public class InteractVO {

  /** Interact ID. 互动ID。 */
  @Schema(description = "Interact ID")
  private Long id;

  /** User ID. 用户ID。 */
  @Schema(description = "User ID")
  private Long userId;

  /** Target ID. 目标ID。 */
  @Schema(description = "Target ID")
  private Long targetId;

  /** Target type. 目标类型。 */
  @Schema(description = "Target type (1-Article, 2-Comment)")
  private Integer targetType;

  /** Target title. 目标标题。 */
  @Schema(description = "Target title")
  private String targetTitle;

  /** Target content. 目标内容。 */
  @Schema(description = "Target content")
  private String targetContent;

  /** Create time. 创建时间。 */
  @Schema(description = "Create time")
  private LocalDateTime createTime;

  /** Update time. 更新时间。 */
  @Schema(description = "Update time")
  private LocalDateTime updateTime;
}
