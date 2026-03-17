package com.example.demo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/** MyBatis-Plus auto fill handler. MyBatis-Plus 自动填充处理器。 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

  /** Auto fill when insert. 插入时自动填充。 */
  @Override
  public void insertFill(MetaObject metaObject) {
    log.debug("Start auto fill for insert...");
    this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
    this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
  }

  /** Auto fill when update. 更新时自动填充。 */
  @Override
  public void updateFill(MetaObject metaObject) {
    log.debug("Start auto fill for update...");
    this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
  }
}
