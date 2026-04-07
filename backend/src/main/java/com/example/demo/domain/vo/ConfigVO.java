package com.example.demo.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

/** Config view object. 配置视图对象。 */
@Data
@Schema(description = "Config view object")
public class ConfigVO {

  /** Config ID. 配置ID。 */
  @Schema(description = "Config ID")
  private Long id;

  /** Config key. 配置键。 */
  @Schema(description = "Config key")
  private String configKey;

  /** Config value. 配置值。 */
  @Schema(description = "Config value")
  private String configValue;

  /** Config description. 配置描述。 */
  @Schema(description = "Config description")
  private String description;

  /** Is system config. 是否系统配置。 */
  @Schema(description = "Is system config")
  private Boolean isSystem;

  /** Is public config. 是否公共配置。 */
  @Schema(description = "Is public config")
  private Boolean isPublic;

  /** Create time. 创建时间。 */
  @Schema(description = "Create time")
  private LocalDateTime createTime;

  /** Update time. 更新时间。 */
  @Schema(description = "Update time")
  private LocalDateTime updateTime;
}
