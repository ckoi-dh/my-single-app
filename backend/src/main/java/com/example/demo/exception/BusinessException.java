package com.example.demo.exception;

import com.example.demo.common.ErrorCode;
import lombok.Getter;

/** Business exception. 业务异常。 */
@Getter
public class BusinessException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final Integer code;

  /** Create business exception with message. 使用消息创建业务异常。 */
  public BusinessException(String message) {
    super(message);
    this.code = 500;
  }

  /** Create business exception with code and message. 使用状态码和消息创建业务异常。 */
  public BusinessException(Integer code, String message) {
    super(message);
    this.code = code;
  }

  /** Create business exception with error code. 使用错误码创建业务异常。 */
  public BusinessException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.code = errorCode.getCode();
  }

  /** Create business exception with error code and custom message. 使用错误码和自定义消息创建业务异常。 */
  public BusinessException(ErrorCode errorCode, String message) {
    super(message);
    this.code = errorCode.getCode();
  }
}
