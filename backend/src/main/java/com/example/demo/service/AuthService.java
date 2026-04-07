package com.example.demo.service;

import com.example.demo.domain.dto.req.LoginReq;
import com.example.demo.domain.vo.LoginVO;

/** Authentication service interface. 认证服务接口。 */
public interface AuthService {

  /**
   * Login. 登录。
   *
   * @param req Login request
   * @return LoginVO with token and user info
   */
  LoginVO login(LoginReq req);

  /** Logout. 登出。 */
  void logout();

  /**
   * Refresh token. 刷新令牌。
   *
   * @param refreshToken Refresh token
   * @return New access token
   */
  String refreshToken(String refreshToken);
}
