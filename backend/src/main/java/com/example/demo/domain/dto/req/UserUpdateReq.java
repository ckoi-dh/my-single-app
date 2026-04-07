package com.example.demo.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/** User update request. 用户更新请求。 */
@Data
@Schema(description = "User update request")
public class UserUpdateReq {

  /** User ID. 用户ID。 */
  @Schema(description = "User ID", required = true)
  @NotNull(message = "User ID is required")
  private Long id;

  /** Nickname. 昵称。 */
  @Schema(description = "Nickname")
  @Size(max = 64, message = "Nickname must be less than 64 characters")
  private String nickname;

  /** Email. 邮箱。 */
  @Schema(description = "Email")
  @Email(message = "Invalid email format")
  private String email;

  /** Avatar URL. 头像URL。 */
  @Schema(description = "Avatar URL")
  @Size(max = 512, message = "Avatar URL must be less than 512 characters")
  private String avatar;

  /** Biography. 个人简介。 */
  @Schema(description = "Biography")
  @Size(max = 512, message = "Biography must be less than 512 characters")
  private String bio;

  /** User status. 用户状态。 */
  @Schema(description = "User status (0-inactive, 1-active, 2-blocked)")
  private Integer status;

  /** Role type. 角色类型。 */
  @Schema(description = "Role type (1-SUPER_ADMIN, 2-ADMIN, 3-AUTHOR, 4-VISITOR)")
  private Integer roleType;
}
