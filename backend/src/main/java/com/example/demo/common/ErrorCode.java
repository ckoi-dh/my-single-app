package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** Error code enumeration. 错误码枚举。 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

  // Common error codes (1xxxx)
  SUCCESS(200, "Success"),
  BAD_REQUEST(400, "Bad request"),
  UNAUTHORIZED(401, "Unauthorized"),
  FORBIDDEN(403, "Forbidden"),
  NOT_FOUND(404, "Not found"),
  INTERNAL_SERVER_ERROR(500, "Internal server error"),

  // Business error codes (2xxxx)
  USER_NOT_FOUND(20001, "User not found"),
  USER_ALREADY_EXISTS(20002, "User already exists"),
  PASSWORD_ERROR(20003, "Password error"),
  ACCOUNT_DISABLED(20004, "Account disabled"),
  TOKEN_INVALID(20005, "Token invalid"),
  TOKEN_EXPIRED(20006, "Token expired"),

  // Validation error codes (3xxxx)
  PARAM_ERROR(30001, "Parameter error"),
  PARAM_MISSING(30002, "Parameter missing");

  private final Integer code;
  private final String message;
}
