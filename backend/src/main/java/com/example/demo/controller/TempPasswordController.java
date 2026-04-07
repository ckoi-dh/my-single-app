package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Temporary password reset controller. 临时密码重置控制器。 This is a temporary controller for development
 * purposes only. 仅用于开发阶段的临时控制器。
 */
@Tag(name = "Temp", description = "Temporary API for development")
@RestController
@RequestMapping("/api/v1/temp")
@RequiredArgsConstructor
@PreAuthorize("permitAll()") // Allow access without authentication
public class TempPasswordController {

  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  /** Reset default user passwords. 重置默认用户密码。 */
  @Operation(
      summary = "Reset default user passwords",
      description = "Reset passwords for admin and testuser")
  @PostMapping("/reset-passwords")
  @PreAuthorize("permitAll()")
  public Result<Void> resetPasswords() {
    // Reset admin password to admin123
    UserEntity admin = userMapper.selectById(1L);
    if (admin != null) {
      admin.setPassword(passwordEncoder.encode("admin123"));
      userMapper.updateById(admin);
    }

    // Reset testuser password to user123
    UserEntity testUser = userMapper.selectById(2L);
    if (testUser != null) {
      testUser.setPassword(passwordEncoder.encode("user123"));
      userMapper.updateById(testUser);
    }

    return Result.success();
  }
}
