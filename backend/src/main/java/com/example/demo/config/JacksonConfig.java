package com.example.demo.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/** Jackson configuration. Jackson 配置类。 */
@Configuration
public class JacksonConfig {

  private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

  /** Configure ObjectMapper. 配置 ObjectMapper。 */
  @Bean
  @Primary
  public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();

    // Configure date format
    // 配置日期格式
    mapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT, Locale.CHINA));
    mapper.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));

    // Configure serialization behavior
    // 配置序列化行为
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    // Configure deserialization behavior
    // 配置反序列化行为
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    // Register JavaTimeModule for Java 8 time types
    // 注册 JavaTimeModule 支持 Java 8 时间类型
    mapper.registerModule(new JavaTimeModule());

    return mapper;
  }
}
