package com.example.demo.common;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Unified API response wrapper. 统一 API 响应包装类。 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  /** Response status code */
  private Integer code;

  /** Response message */
  private String message;

  /** Response data */
  private T data;

  /** Return success result without data. 返回成功结果（无数据）。 */
  public static <T> Result<T> success() {
    return new Result<>(200, "success", null);
  }

  /** Return success result with data. 返回成功结果（带数据）。 */
  public static <T> Result<T> success(T data) {
    return new Result<>(200, "success", data);
  }

  /** Return success result with message and data. 返回成功结果（带消息和数据）。 */
  public static <T> Result<T> success(String message, T data) {
    return new Result<>(200, message, data);
  }

  /** Return error result with default message. 返回错误结果（默认消息）。 */
  public static <T> Result<T> error() {
    return new Result<>(500, "error", null);
  }

  /** Return error result with message. 返回错误结果（带消息）。 */
  public static <T> Result<T> error(String message) {
    return new Result<>(500, message, null);
  }

  /** Return error result with code and message. 返回错误结果（带状态码和消息）。 */
  public static <T> Result<T> error(Integer code, String message) {
    return new Result<>(code, message, null);
  }
}
