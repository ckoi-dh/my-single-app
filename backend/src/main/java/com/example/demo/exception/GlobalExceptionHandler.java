package com.example.demo.exception;

import com.example.demo.common.ErrorCode;
import com.example.demo.common.Result;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

/** Global exception handler. 全局异常处理器。 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  /** Handle business exception. 处理业务异常。 */
  @ExceptionHandler(BusinessException.class)
  public Result<Void> handleBusinessException(BusinessException e) {
    log.warn("Business exception: {}", e.getMessage());
    return Result.error(e.getCode(), e.getMessage());
  }

  /** Handle validation exception (MethodArgumentNotValidException). 处理参数校验异常（@RequestBody）。 */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Result<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    String message =
        e.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.joining("; "));
    log.warn("Validation exception: {}", message);
    return Result.error(ErrorCode.PARAM_ERROR.getCode(), message);
  }

  /** Handle validation exception (BindException). 处理参数绑定异常（表单数据）。 */
  @ExceptionHandler(BindException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Result<Void> handleBindException(BindException e) {
    String message =
        e.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.joining("; "));
    log.warn("Bind exception: {}", message);
    return Result.error(ErrorCode.PARAM_ERROR.getCode(), message);
  }

  /** Handle validation exception (ConstraintViolationException). 处理单个参数校验异常（@RequestParam）。 */
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Result<Void> handleConstraintViolationException(ConstraintViolationException e) {
    String message =
        e.getConstraintViolations().stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.joining("; "));
    log.warn("Constraint violation: {}", message);
    return Result.error(ErrorCode.PARAM_ERROR.getCode(), message);
  }

  /** Handle argument type mismatch exception. 处理参数类型不匹配异常。 */
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Result<Void> handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException e) {
    String message = "Parameter " + e.getName() + " type mismatch";
    log.warn("Type mismatch: {}", message);
    return Result.error(ErrorCode.PARAM_ERROR.getCode(), message);
  }

  /** Handle bad credentials exception. 处理认证失败异常。 */
  @ExceptionHandler(BadCredentialsException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Result<Void> handleBadCredentialsException(BadCredentialsException e) {
    log.warn("Bad credentials: {}", e.getMessage());
    return Result.error(ErrorCode.UNAUTHORIZED.getCode(), ErrorCode.UNAUTHORIZED.getMessage());
  }

  /** Handle access denied exception. 处理访问拒绝异常。 */
  @ExceptionHandler(AccessDeniedException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public Result<Void> handleAccessDeniedException(AccessDeniedException e) {
    log.warn("Access denied: {}", e.getMessage());
    return Result.error(ErrorCode.FORBIDDEN.getCode(), ErrorCode.FORBIDDEN.getMessage());
  }

  /** Handle no handler found exception. 处理404异常。 */
  @ExceptionHandler(NoHandlerFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Result<Void> handleNoHandlerFoundException(NoHandlerFoundException e) {
    log.warn("No handler found: {}", e.getRequestURL());
    return Result.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
  }

  /** Handle all other exceptions. 处理其他所有异常。 */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Result<Void> handleException(Exception e) {
    log.error("Internal server error", e);
    return Result.error(
        ErrorCode.INTERNAL_SERVER_ERROR.getCode(), ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
  }
}
