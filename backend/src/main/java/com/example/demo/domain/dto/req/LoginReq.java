package com.example.demo.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/** Login request. 登录请求。 */
@Data
@Schema(description = "Login request")
public class LoginReq {

  /** Username. 用户名。 */
  @Schema(description = "Username", required = true)
  @NotBlank(message = "Username is required")
  private String username;

  /** Password. 密码。 */
  @Schema(description = "Password", required = true)
  @NotBlank(message = "Password is required")
  private String password;
}
