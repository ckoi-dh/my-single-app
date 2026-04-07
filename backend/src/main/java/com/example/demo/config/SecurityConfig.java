package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/** Spring Security configuration. Spring Security 配置类。 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  /** Configure password encoder. 配置密码编码器。 */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /** Configure security filter chain. 配置安全过滤器链。 */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        // Configure CORS
        // 配置跨域资源共享
        .cors()
        .and()
        .authorizeRequests()
        // Allow public access to these endpoints
        // 允许公开访问这些端点
        .antMatchers(
            "/api/v1/demo/**",
            "/api/v1/auth/login",
            "/api/v1/auth/refresh",
            "/api/v1/temp/**",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/actuator/**",
            "/error")
        .permitAll()
        // All other requests require authentication
        // 其他所有请求需要认证
        .anyRequest()
        .authenticated();

    // Add JWT filter before UsernamePasswordAuthenticationFilter
    // 在用户名密码认证过滤器之前添加JWT过滤器
    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  /** Configure CORS policy. 配置跨域资源共享策略 */
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    // Allow requests from frontend dev servers
    // 允许来自前端开发服务器的请求
    configuration.setAllowedOrigins(
        Arrays.asList("http://localhost:3000", "http://localhost:5173", "http://127.0.0.1:5173"));
    // Allow all HTTP methods
    // 允许所有HTTP方法
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    // Allow necessary headers
    // 允许必要的头部
    configuration.setAllowedHeaders(
        Arrays.asList("Authorization", "Content-Type", "Accept", "Origin", "X-Requested-With"));
    // Allow credentials (cookies, authorization headers, etc.)
    // 允许凭证（cookies、授权头部等）
    configuration.setAllowCredentials(true);
    // Set max age for pre-flight requests (30 minutes)
    // 设置预检请求的最大缓存时间（30分钟）
    configuration.setMaxAge(1800L);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    // Apply CORS configuration to all endpoints
    // 将CORS配置应用到所有端点
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }
}
