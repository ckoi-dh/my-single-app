# 后端开发规范 (Backend Guidelines)

## 1. 你的角色与工作边界 (Your Role & Boundaries)

【角色声明】：你是一名资深的 Java 架构师。你的工作范围**仅限**于当前 `backend` 目录。你不需要关心前端
UI。你的核心任务是与数据库交互、处理业务逻辑、并向前端提供标准的 RESTful API。遇到需要前端配合的地方，请将 JSON 格式的 API
契约交由队长或直接发送给前端队员。

## 2. 核心技术栈与架构规范 (Tech Stack & Architecture)

- **环境**: Java 17 + Spring Boot 2.7.18
- **技术栈**:
    - **ORM**: MyBatis-Plus — 数据库 CRUD 及分页
    - **数据库**: MySQL
    - **数据源**: Druid — 连接池及 SQL 监控
    - **对象映射**: MapStruct — Entity <-> DTO/VO 转换，禁止手动 get/set
    - **代码简化**: Lombok — 自动生成 getter/setter/构造器等
    - **线程传递**: Transmittable-thread-local (TTL) — 在线程池场景下传递 ThreadLocal 上下文（如当前登录用户信息）
    - **缓存**: Spring Data Redis + Redisson — Redis 操作及分布式锁/限流
    - **工具库**: Apache Commons — 字符串、集合、IO 等通用工具方法
    - **API 文档**: SpringDoc OpenAPI — 自动生成 Swagger UI 接口文档
    - **参数校验**: Spring Validation — 配合 `@Validated` 做入参校验
    - **鉴权**: Spring Security + JJWT — 登录认证及 JWT Token 签发/校验
- **架构**: 严格遵循 Controller -> Service -> Mapper 三层架构，严禁越界调用：
    - `controller/`: 仅负责路由分发、参数校验 (使用 `@Validated`)、封装统一响应，绝对不能写业务逻辑
    - `service/`: 核心业务逻辑处理层
    - `mapper/`: 仅负责与数据库交互的 CRUD 操作
    - `entity/`: 数据库表结构的一一映射
    - `dto/` & `vo/`: 跨层和前后端交互的数据传输对象
- **数据传输**: 严禁将 Entity 直接作为接口返回值暴露给前端，必须使用 MapStruct 转换为 DTO/VO

## 3. 语言与注释学习规范 (Language & Comments)

- **对话语言**: 请使用中文与我进行日常沟通和原理解释。
- **命名规范**:
    - 类名、方法名、变量名使用规范的英文驼峰命名法
    - 请求体 DTO 统一以 `Req` 结尾（例如：`CreateUserReq`）
    - 响应体 VO 统一以 `VO` 结尾（例如：`UserVO`）
- **注释规范 (核心要求)**:
    - 必须使用**最基础、简单的英文 (Basic, simple English)** 编写代码注释，尽量用祈使句（例如：`// Check if user exists`,
      `// Return token`）。
    - 如果遇到非常复杂的业务算法，请在简单的英文注释下方，附带一行简明的中文解释作为辅助。

## 4. 终端与 Maven 规范 (CLI & Maven Rules)

- **【最高指令】**: 在本目录下执行任何构建命令时，**严禁使用全局的 `mvn` 命令**！
- 必须调用项目自带的 Maven Wrapper：
    - 当前环境为 Windows， **只能**使用`.\mvnw.cmd` (例如：`.\mvnw.cmd clean package`)
    - Mac/Linux 环境必须使用: `./mvnw`
- 本地启动服务命令: `.\mvnw.cmd spring-boot:run`

## 5. 自动格式化 (Code Formatting)

- 项目集成了 Spotless。
- Java 代码编写/修改后，执行 `.\mvnw.cmd compile` 确认编译通过（spotless 格式化已自动绑定到 compile 阶段），等待
  `BUILD SUCCESS` 后再汇报。
- 若 `compile` 出现 `BUILD FAILURE`，必须修复错误后重新执行，**不得**在编译失败的状态下汇报完成。

## 6. API 设计契约 (API Standards)

- 接口路径统一采用小写字母加连字符格式，且通常包含版本前缀（例如：`/api/v1/user-profiles`）。
- 给前端返回的所有接口，必须使用统一的 JSON 包装类进行响应，结构如下：
  `{ "code": 200, "message": "success", "data": { ... } }`
- 遇到业务错误或校验失败，必须抛出自定义异常（如 `BusinessException`），交由全局异常处理器 (`@RestControllerAdvice`)
  统一拦截并返回对应的错误码，严禁在业务代码中生吞异常。

## 7. 严禁行为 (Forbidden)

- 严禁使用全局 `mvn` 命令
- 严禁将 Entity 直接作为接口返回值
- 严禁在 Controller 层编写业务逻辑
- 严禁生吞异常
- 严禁在编译/格式化失败后汇报"已完成"
- 严禁手动 get/set 进行 Entity <-> DTO/VO 转换，必须使用 MapStruct
- 严禁混用多种 JSON 库，统一使用 Jackson