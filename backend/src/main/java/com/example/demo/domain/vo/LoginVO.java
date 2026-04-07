package com.example.demo.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Login VO. 登录视图对象。 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Login VO")
public class LoginVO {

  /** Access token. 访问令牌。 */
  @Schema(description = "Access token")
  private String token;

  /** User information. 用户信息。 */
  @Schema(description = "User information")
  private UserVO user;
}
