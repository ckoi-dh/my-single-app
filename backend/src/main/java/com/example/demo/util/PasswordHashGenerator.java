package com.example.demo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/** Password hash generator. 密码哈希生成器。 */
public class PasswordHashGenerator {
  public static void main(String[] args) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Generate hash for "admin123"
    String adminHash = encoder.encode("admin123");
    System.out.println("admin123 hash: " + adminHash);

    // Generate hash for "user123"
    String userHash = encoder.encode("user123");
    System.out.println("user123 hash: " + userHash);

    // Verify
    System.out.println("Verify admin123: " + encoder.matches("admin123", adminHash));
    System.out.println("Verify user123: " + encoder.matches("user123", userHash));
  }
}
