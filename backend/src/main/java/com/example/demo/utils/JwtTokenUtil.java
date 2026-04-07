package com.example.demo.utils;

import com.example.demo.common.constant.AuthConstant;
import com.example.demo.domain.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/** JWT token utility class. JWT令牌工具类。 */
@Slf4j
@Component
public class JwtTokenUtil {

  @Value("${" + AuthConstant.JWT_SECRET_PROPERTY + ":" + AuthConstant.DEFAULT_JWT_SECRET + "}")
  private String secret;

  @Value(
      "${" + AuthConstant.JWT_EXPIRATION_PROPERTY + ":" + AuthConstant.DEFAULT_JWT_EXPIRATION + "}")
  private long expiration;

  /**
   * Generate JWT token from user entity. 从用户实体生成JWT令牌。
   *
   * @param user User entity
   * @return JWT token
   */
  public String generateToken(UserEntity user) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + expiration);

    return Jwts.builder()
        .setIssuer(AuthConstant.TOKEN_ISSUER)
        .setSubject(String.valueOf(user.getId()))
        .claim(AuthConstant.CLAIM_USER_ID, user.getId())
        .claim(AuthConstant.CLAIM_USERNAME, user.getUsername())
        .claim(AuthConstant.CLAIM_ROLE_TYPE, user.getRoleType())
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  /**
   * Get signing key for JWT. 获取JWT签名密钥。
   *
   * @return Signing key
   */
  private Key getSigningKey() {
    byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  /**
   * Parse claims from token. 解析令牌中的声明。
   *
   * @param token JWT token
   * @return Claims
   */
  public Claims parseClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  /**
   * Get user ID from token. 从令牌中获取用户ID。
   *
   * @param token JWT token
   * @return User ID
   */
  public Long getUserId(String token) {
    return parseClaims(token).get(AuthConstant.CLAIM_USER_ID, Long.class);
  }

  /**
   * Get username from token. 从令牌中获取用户名。
   *
   * @param token JWT token
   * @return Username
   */
  public String getUsername(String token) {
    return parseClaims(token).get(AuthConstant.CLAIM_USERNAME, String.class);
  }

  /**
   * Get role type from token. 从令牌中获取角色类型。
   *
   * @param token JWT token
   * @return Role type
   */
  public Integer getRoleType(String token) {
    return parseClaims(token).get(AuthConstant.CLAIM_ROLE_TYPE, Integer.class);
  }

  /**
   * Get issued at time from token. 从令牌中获取签发时间。
   *
   * @param token JWT token
   * @return Issued at time
   */
  public LocalDateTime getIssuedAt(String token) {
    Date date = parseClaims(token).getIssuedAt();
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

  /**
   * Get expiration time from token. 从令牌中获取过期时间。
   *
   * @param token JWT token
   * @return Expiration time
   */
  public LocalDateTime getExpiration(String token) {
    Date date = parseClaims(token).getExpiration();
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

  /**
   * Check if token is expired. 检查令牌是否过期。
   *
   * @param token JWT token
   * @return True if expired
   */
  public boolean isExpired(String token) {
    return getExpiration(token).isBefore(LocalDateTime.now());
  }

  /**
   * Check if token is valid. 检查令牌是否有效。
   *
   * @param token JWT token
   * @param user User entity
   * @return True if valid
   */
  public boolean isValid(String token, UserEntity user) {
    try {
      Long userId = getUserId(token);
      String username = getUsername(token);
      return userId.equals(user.getId())
          && username.equals(user.getUsername())
          && !isExpired(token);
    } catch (Exception e) {
      log.error("Invalid JWT token: {}", e.getMessage());
      return false;
    }
  }

  /**
   * Extract token from authorization header. 从授权头部提取令牌。
   *
   * @param authHeader Authorization header
   * @return Token without prefix
   */
  public String extractToken(String authHeader) {
    if (authHeader != null && authHeader.startsWith(AuthConstant.JWT_PREFIX)) {
      return authHeader.substring(AuthConstant.JWT_PREFIX.length());
    }
    return null;
  }
}
