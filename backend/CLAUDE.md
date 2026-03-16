# 后端开发规范 (Backend Guidelines)

## 1. 核心技术栈 (Tech Stack)

- **环境**: Java 17 + Spring Boot 2.7.18
- **构建**: Maven (强制使用 Wrapper)
- **持久层**: MySQL + MyBatis-Plus (或 Spring Data JPA)

## 2. 架构与分层红线 (Architecture Rules)

必须严格遵循经典三层架构，严禁越界调用：

- `controller/`: 仅负责路由分发、参数校验 (使用 `@Valid`)、封装统一响应，**绝对不能**在这里写业务逻辑。
- `service/`: 核心业务逻辑处理层。
- `mapper/` (或 `repository/`): 仅负责与数据库交互的 CRUD 操作。
- `entity/`: 数据库表结构的一一映射。
- `dto/` & `vo/`: 跨层和前后端交互的数据传输对象。**严禁直接将 Entity 作为接口返回值暴露给前端**。

## 3. 语言与注释学习规范 (Language & Comments)

- **对话语言**: 请使用中文与我进行日常沟通和原理解释。
- **命名规范**: 所有的类名、方法名、变量名必须使用规范的英文驼峰命名法。
- **注释规范 (核心要求)**:
    - 必须使用**最基础、简单的英文 (Basic, simple English)** 编写代码注释，尽量用祈使句（例如：`// Check if user exists`,
      `// Return token`），帮助我沉浸式学习编程英语。
    - 如果遇到非常复杂的业务算法，请在简单的英文注释下方，附带一行简明的中文解释作为辅助。

## 4. 终端与 Maven 规范 (CLI & Maven Rules)

- **【最高指令】**: 在本目录下执行任何构建命令时，**严禁使用全局的 `mvn` 命令**！
- 必须调用项目自带的 Maven Wrapper：
    - Windows 环境必须使用: `.\mvnw.cmd` (例如：`.\mvnw.cmd clean package`)
    - Mac/Linux 环境必须使用: `./mvnw`
- 本地启动服务命令: `.\mvnw.cmd spring-boot:run`

## 5. 自动格式化 (Code Formatting)

- 项目集成了 Spotless (或类似的格式化插件)。
- 在你完成任何 Java 代码的编写、修改或重构后，**必须**主动在终端执行一次格式化命令（如 `.\mvnw.cmd spotless:apply`），等待终端提示
  `BUILD SUCCESS` 后，再向我汇报代码已写完。

## 6. API 设计契约 (API Standards)

- 接口路径统一采用小写字母加连字符格式，且通常包含版本前缀（例如：`/api/v1/user-profiles`）。
- 给前端返回的所有接口，必须使用统一的 JSON 包装类进行响应，结构如下：
  `{ "code": 200, "message": "success", "data": { ... } }`
- 遇到业务错误或校验失败，必须抛出自定义异常（如 `BusinessException`），交由全局异常处理器 (`@RestControllerAdvice`)
  统一拦截并返回对应的错误码，严禁在业务代码中生吞异常。