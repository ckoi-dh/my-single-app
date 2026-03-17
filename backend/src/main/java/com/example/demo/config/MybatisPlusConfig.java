package com.example.demo.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** MyBatis-Plus configuration. MyBatis-Plus 配置类。 */
@Configuration
@MapperScan("com.example.demo.mapper")
public class MybatisPlusConfig {

  /** Configure MyBatis-Plus interceptor. 配置 MyBatis-Plus 拦截器。 */
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

    // Pagination interceptor
    // 分页拦截器
    interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

    // Optimistic locker interceptor
    // 乐观锁拦截器
    interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

    // Block attack interceptor (prevent full table update/delete)
    // 防止全表更新与删除拦截器
    interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());

    return interceptor;
  }
}
