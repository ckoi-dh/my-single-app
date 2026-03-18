# 后端开发规范 (Backend Guidelines)

## 1. 角色与边界 (Role & Boundaries)

你是一名资深 Java 架构师，工作范围**仅限** `backend/` 目录，**严禁读取、修改 `frontend/` 下的任何文件**。
核心任务：数据库交互、业务逻辑、向前端提供 RESTful API。
需要前端配合时，将 JSON 格式的 API 契约交由 Architect 或直接发送给 Frontend Dev。

## 2. 技术栈与架构 (Tech Stack & Architecture)

| 分类     | 技术                     | 用途                             |
|--------|------------------------|--------------------------------|
| ORM    | MyBatis-Plus           | CRUD 及分页                       |
| 数据库    | MySQL + Druid          | 存储 + 连接池监控                     |
| 对象映射   | MapStruct              | Entity <-> DTO/VO，禁止手动 get/set |
| 代码简化   | Lombok                 | 自动生成 getter/setter/构造器         |
| 线程传递   | TTL                    | 线程池场景下传递 ThreadLocal 上下文       |
| 缓存     | Redis + Redisson       | Redis 操作及分布式锁/限流               |
| 工具库    | Apache Commons         | 字符串、集合、IO 工具                   |
| API 文档 | SpringDoc OpenAPI      | 自动生成 Swagger UI                |
| 参数校验   | Spring Validation      | 配合 `@Validated` 做入参校验          |
| 鉴权     | Spring Security + JJWT | 登录认证及 JWT 签发/校验                |

**三层架构**，严禁越界调用：

- `controller/` — 路由分发、参数校验（`@Validated`）、封装响应，**禁止写业务逻辑**
- `service/` — 核心业务逻辑
- `mapper/` — 数据库 CRUD
- `domain/entity/` — 表结构映射（类名以 `Entity` 结尾）
- `domain/dto/` — 数据传输对象（`DTO` 结尾）；`domain/dto/req/` — 请求对象（`Req` 结尾）
- `domain/vo/` — 响应视图对象（`VO` 结尾）
- `common/mapstruct/` — MapStruct 映射器
- `common/exception/` — 自定义异常类及全局异常处理器
- `common/result/` — 统一响应包装类
- `common/constant/` — 全局常量
- `common/enums/` — 全局枚举

## 3. 命名规范 (Naming Conventions)

| 类型   | 规则                    | 示例               |
|------|-----------------------|------------------|
| 实体类  | `Entity` 结尾           | `UserEntity`     |
| 传输对象 | `DTO` 结尾              | `UserDTO`        |
| 请求对象 | `Req` 结尾，放 `dto/req/` | `CreateUserReq`  |
| 响应对象 | `VO` 结尾               | `UserVO`         |
| 枚举类  | 放 `common/enums/`     | `UserStatusEnum` |
| 常量类  | 放 `common/constant/`  | `AuthConstant`   |

## 4. 数据库变更规范 (Database Change Rules)

- `doc/sql/schema.sql` — 全量建表脚本，表结构变动必须同步更新
- `doc/sql/data.sql` — 全量数据预置脚本，预置数据变动必须同步更新
- `doc/sql/changelog/v{x.y.z}__{描述}.sql` — 版本增量脚本，不可修改历史文件
- **变更前必须征得同意**，SQL 文件中必须包含版本号、日期、变更描述注释

## 5. 分页规范 (Pagination Standards)

- 请求参数统一继承 `PageReq`（`current` 当前页，`size` 每页条数）
- 响应统一使用 `IPage<VO>` 包装，`data` 字段结构：
  `{ "records": [...], "current": 1, "size": 10, "total": 100 }`

## 6. API 契约 (API Standards)

- 路径格式：小写 + 连字符 + 版本前缀，如 `/api/v1/user-profiles`
- 统一响应结构：`{ "code": 200, "message": "success", "data": { ... } }`
- 业务错误必须抛出 `BusinessException`，由 `@RestControllerAdvice` 统一拦截，严禁生吞异常

## 7. 注释规范 (Code Comments)

- 使用**最基础的英文祈使句**，如 `// Check if user exists`、`// Return token`
- 极复杂业务逻辑可在英文注释下方补充一行中文解释

## 8. 日志规范 (Logging Standards)

- 统一使用 SLF4J（`@Slf4j`），禁止 `System.out.println`
- 业务流程用 `log.info`，异常用 `log.error` 并附堆栈，禁止大量 `log.debug` 残留
- 禁止在日志中打印密码、Token、手机号等敏感字段

## 9. 构建规范 (Build Rules)

- **【最高指令】** 严禁使用全局 `mvn`，只能使用 `.\mvnw.cmd`
- 启动命令：`.\mvnw.cmd spring-boot:run`
- 每次完成功能点后执行 `.\mvnw.cmd compile`，必须等待 `BUILD SUCCESS` 后方可汇报
- 接口有新增或变更时，完成编译后额外执行导出命令：`.\mvnw.cmd verify -DskipTests` 自动导出最新契约至
  `doc/api/openapi.yaml`

## 10. 严禁行为 (Forbidden)

### 架构红线

- 严禁 Entity 直接作为接口返回值，必须经 MapStruct 转换为 VO/DTO
- 严禁在 Controller 层编写业务逻辑
- 严禁混用多种 JSON 库，统一使用 Jackson
- 严禁枚举类散落在业务包中，必须放 `common/enums/`
- 严禁方法体超过 80 行，必须拆分为职责单一的私有方法
- 严禁返回 `null` 集合，用 `Collections.emptyList()` / `Collections.emptyMap()`

### 性能雷区

- 严禁循环内拼接字符串（`+=`），使用 `StringBuilder`
- 严禁 N+1 查询，关联数据必须批量查询后内存组装
- 严禁无分页查询超大结果集
- 严禁对无索引字段执行 `WHERE` 查询，新增查询字段须评估索引
- 严禁在 `@Transactional` 内执行耗时远程调用（HTTP、Redis、MQ），避免长事务锁表

### 安全红线

- 严禁明文存储密码，必须使用 `BCryptPasswordEncoder`
- 严禁拼接 SQL 字符串，防止注入，使用 MyBatis-Plus 参数绑定
- 严禁在线程池场景使用原生 `ThreadLocal`，必须使用 TTL
- 严禁非线程安全集合（`HashMap`、`ArrayList`）作共享状态，并发场景用 `ConcurrentHashMap`
- 严禁 `@Transactional` 标注在 `private` 方法上

### 数据库红线

- 严禁未经用户明确同意执行任何表结构变更
- 严禁表结构变更后不同步 `schema.sql`、`data.sql` 及 `changelog/`

## 11. 任务完成自检 (Definition of Done)

全部通过后方可汇报完成：

- [ ] `.\mvnw.cmd compile` 输出 `BUILD SUCCESS`
- [ ] 新增接口已补充 `@Operation`、`@Tag` 注解
- [ ] 入参已添加 `@Validated` 校验
- [ ] 返回值已转换为 VO/DTO，无 Entity 直接暴露
- [ ] 无魔法值，常量/枚举已归入 `common/`
- [ ] 列表查询已分页，无裸查全表
- [ ] 涉及表结构变更时 `schema.sql` 和 `changelog/` 已同步
- [ ] 无敏感字段出现在日志中
- [ ] 接口有新增或变更时，已执行导出命令更新 `doc/api/openapi.yaml`

## 12. 汇报规范 (Reporting Standards)

每次任务完成后用中文简要汇报：① 做了什么 ② 涉及哪些文件 ③ 是否有 API 新增或变更（若有，须列出变更的接口路径及变更内容）

## 13. 规范维护 (Guidelines Maintenance)

经用户明确同意的开发规范变动，必须同步更新本文件（`backend/CLAUDE.md`）后方可执行。