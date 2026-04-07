package com.example.demo.common.constant;

/** Authentication constants. 认证常量。 */
public class AuthConstant {

  /** JWT token prefix. JWT令牌前缀。 */
  public static final String JWT_PREFIX = "Bearer ";

  /** Authorization header. 授权头部。 */
  public static final String AUTHORIZATION_HEADER = "Authorization";

  /** Token header. 令牌头部。 */
  public static final String TOKEN_HEADER = "token";

  /** User ID claim key. 用户ID声明键。 */
  public static final String CLAIM_USER_ID = "userId";

  /** Username claim key. 用户名声明键。 */
  public static final String CLAIM_USERNAME = "username";

  /** Role type claim key. 角色类型声明键。 */
  public static final String CLAIM_ROLE_TYPE = "roleType";

  /** Expiration time claim key. 过期时间声明键。 */
  public static final String CLAIM_EXPIRED_AT = "exp";

  /** Issued at claim key. 签发时间声明键。 */
  public static final String CLAIM_ISSUED_AT = "iat";

  /** Token issuer. 令牌签发者。 */
  public static final String TOKEN_ISSUER = "demo-app";

  /** Access token expiration time (24 hours in seconds). 访问令牌过期时间（24小时）。 */
  public static final long ACCESS_TOKEN_EXPIRE = 24 * 60 * 60L;

  /** Refresh token expiration time (7 days in seconds). 刷新令牌过期时间（7天）。 */
  public static final long REFRESH_TOKEN_EXPIRE = 7 * 24 * 60 * 60L;

  /** JWT secret key property. JWT密钥属性。 */
  public static final String JWT_SECRET_PROPERTY = "jwt.secret";

  /** JWT expiration property. JWT过期时间属性。 */
  public static final String JWT_EXPIRATION_PROPERTY = "jwt.expiration";

  /** Default JWT secret key. 默认JWT密钥。 */
  public static final String DEFAULT_JWT_SECRET =
      "demo-app-secret-key-for-development-purpose-only-which-is-at-least-256-bits";

  /** Default token expiration time (24 hours in milliseconds). 默认令牌过期时间（24小时）。 */
  public static final long DEFAULT_JWT_EXPIRATION = 24 * 60 * 60 * 1000L;

  /** Anonymous user ID. 匿名用户ID。 */
  public static final Long ANONYMOUS_USER_ID = 0L;

  /** Anonymous username. 匿名用户名。 */
  public static final String ANONYMOUS_USERNAME = "anonymous";
}
