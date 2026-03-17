package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;

/**
 * Spring Boot application entry point. Exclude Redis auto-configuration when Redis is not
 * available.
 */
@SpringBootApplication(
    exclude = {RedisAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
