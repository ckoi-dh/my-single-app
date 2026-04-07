package com.example.demo.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/** User create request. 用户创建请求。 */
@Data
@Schema(description = "User create request")
public class UserCreateReq {

  /** Username. 用户名。 */
  @Schema(description = "Username", required = true)
  @NotBlank(message = "Username is required")
  @Size(min = 3, max = 64, message = "Username must be 3-64 characters")
  private String username;

  /** Password. 密码。 */
  @Schema(description = "Password", required = true)
  @NotBlank(message = "Password is required")
  @Size(min = 6, max = 64, message = "Password must be 6-64 characters")
  private String password;

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

  /** Role type. 角色类型。 */
  @Schema(description = "Role type", required = true)
  @NotNull(message = "Role type is required")
  private Integer roleType;
}
