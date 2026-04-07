package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.domain.dto.req.LoginReq;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.domain.vo.LoginVO;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.AuthService;
import com.example.demo.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Authentication service implementation. 认证服务实现。 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenUtil jwtTokenUtil;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public LoginVO login(LoginReq req) {
    // Find user by username
    // 根据用户名查找用户
    LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(UserEntity::getUsername, req.getUsername());
    UserEntity user = userMapper.selectOne(queryWrapper);

    if (user == null) {
      throw new BusinessException("User not found");
    }

    // Verify password
    // 验证密码
    if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
      throw new BusinessException("Password incorrect");
    }

    // Generate token
    // 生成令牌
    String token = jwtTokenUtil.generateToken(user);

    // Create login VO
    // 创建登录VO
    LoginVO loginVO = new LoginVO();
    loginVO.setToken(token);

    return loginVO;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void logout() {
    // TODO: Implement logout
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public String refreshToken(String refreshToken) {
    // TODO: Implement refresh token
    return null;
  }
}
