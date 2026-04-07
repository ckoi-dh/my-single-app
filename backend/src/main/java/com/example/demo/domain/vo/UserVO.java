package com.example.demo.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

/** User VO. 用户视图对象。 */
@Data
@Schema(description = "User VO")
public class UserVO {

  /** User ID. 用户ID。 */
  @Schema(description = "User ID")
  private Long id;

  /** Username. 用户名。 */
  @Schema(description = "Username")
  private String username;

  /** Nickname. 昵称。 */
  @Schema(description = "Nickname")
  private String nickname;

  /** Email. 邮箱。 */
  @Schema(description = "Email")
  private String email;

  /** Avatar URL. 头像URL。 */
  @Schema(description = "Avatar URL")
  private String avatar;

  /** Biography. 个人简介。 */
  @Schema(description = "Biography")
  private String bio;

  /** User status (0-inactive, 1-active, 2-blocked). 用户状态。 */
  @Schema(description = "User status (0-inactive, 1-active, 2-blocked)")
  private Integer status;

  /** Role type (1-SUPER_ADMIN, 2-ADMIN, 3-AUTHOR, 4-VISITOR). 角色类型。 */
  @Schema(description = "Role type (1-SUPER_ADMIN, 2-ADMIN, 3-AUTHOR, 4-VISITOR)")
  private Integer roleType;

  /** Role name. 角色名称。 */
  @Schema(description = "Role name")
  private String roleName;

  /** Last login time. 最后登录时间。 */
  @Schema(description = "Last login time")
  private LocalDateTime lastLoginTime;

  /** Create time. 创建时间。 */
  @Schema(description = "Create time")
  private LocalDateTime createTime;
}
