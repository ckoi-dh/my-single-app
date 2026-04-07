package com.example.demo.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/** Config update request. 配置更新请求。 */
@Data
@Schema(description = "Config update request")
public class ConfigUpdateReq {

  /** Config ID. 配置ID。 */
  @NotNull(message = "Config ID cannot be null")
  @Schema(description = "Config ID", required = true)
  private Long id;

  /** Config key. 配置键。 */
  @Size(max = 100, message = "Config key length cannot exceed 100 characters")
  @Schema(description = "Config key")
  private String configKey;

  /** Config value. 配置值。 */
  @Size(max = 500, message = "Config value length cannot exceed 500 characters")
  @Schema(description = "Config value")
  private String configValue;

  /** Config description. 配置描述。 */
  @Size(max = 500, message = "Config description length cannot exceed 500 characters")
  @Schema(description = "Config description")
  private String description;

  /** Is system config. 是否系统配置。 */
  @Schema(description = "Is system config")
  private Boolean isSystem;

  /** Is public config. 是否公共配置。 */
  @Schema(description = "Is public config")
  private Boolean isPublic;
}
