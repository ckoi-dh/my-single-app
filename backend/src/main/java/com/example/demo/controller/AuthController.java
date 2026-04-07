package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.common.constant.AuthConstant;
import com.example.demo.domain.dto.req.LoginReq;
import com.example.demo.domain.vo.LoginVO;
import com.example.demo.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/** Authentication controller. 认证控制器。 */
@Tag(name = "Auth", description = "Authentication and Authorization API")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  /**
   * User login. 用户登录。
   *
   * @param req Login request
   * @return Login response with token
   */
  @Operation(summary = "User login", description = "User login to get access token")
  @PostMapping("/login")
  public Result<LoginVO> login(
      @Parameter(description = "Login request") @Valid @RequestBody LoginReq req) {
    return Result.success(authService.login(req));
  }

  /**
   * User logout. 用户登出。
   *
   * @return Success result
   */
  @Operation(summary = "User logout", description = "User logout to invalidate token")
  @PostMapping("/logout")
  public Result<Void> logout() {
    authService.logout();
    return Result.success();
  }

  /**
   * Refresh token. 刷新令牌。
   *
   * @param token Bearer token
   * @return New login response
   */
  @Operation(summary = "Refresh token", description = "Refresh access token with current token")
  @PostMapping("/refresh")
  public Result<String> refreshToken(
      @Parameter(description = "Authorization token")
          @RequestHeader(name = AuthConstant.AUTHORIZATION_HEADER, required = false)
          String token) {
    return Result.success(authService.refreshToken(token));
  }
}
