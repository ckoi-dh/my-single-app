# 全栈项目全局架构与 Agent Team 协作规范

## 1. 团队协作与角色划分 (Agent Team Rules)

如果启用了 Agent Team 模式，队长（Leader）必须严格遵守以下任务分发红线：

- **后端队员 (Java Expert)**: 只能分配到 `backend/` 目录工作。负责查阅 `doc/` 下的需求，设计数据库表，并优先输出 RESTful
  API。**严禁修改任何前端文件。**
- **前端队员 (Vue Expert)**: 只能分配到 `frontend/` 目录工作。必须等待后端确定 API JSON 契约后，再进行页面和接口对接开发。
  **严禁修改任何后端或数据库文件。**
- **队长职责**: 统筹规划，按顺序派发任务。在开发前引导队员读取 `doc/` 目录下的 PRD 或原型图。**协作铁律**：后端优先定义 API
  数据结构（契约），前端必须严格按照后端定义的数据结构进行对接，严禁前端自行捏造数据字段。

## 2. 项目概览与全栈契约 (Project Overview)

- 本项目是前后端分离 Monorepo。
- **前端技术栈**: Vue 3 + TypeScript + Vite + Pinia + Vue Router + Element Plus + Axios + Prettier + ESLint
- **后端技术栈**: Java 17 + Spring Boot 2.7.18 + MyBatis-Plus + MySQL + Druid + MapStruct + Lombok + Spring Security +
  JJWT
- **子目录规范**: `backend/` 和 `frontend/` 目录下各自有更详细的 CLAUDE.md 开发规范
- 所有接口响应统一使用 `{ code: 200, message: "success", data: { ... } }` 结构。
- 规范性的项目变更，需要同步更新对应的 CLAUDE.md 文件

## 3. 项目快速启动 (Quick Start)

- **后端启动**: 进入 `backend/` 目录，执行 `.\mvnw.cmd spring-boot:run` (Windows) 或 `./mvnw spring-boot:run` (Mac/Linux)
- **前端启动**: 进入 `frontend/` 目录，执行 `npm run dev`

## 4. 沟通与语言学习策略 (Communication & Language Learning)

- **对话与解释**：请使用中文与我进行日常探讨、原理解释和错误排查；若需求描述不够清晰、或现有方案存在明显缺陷，不得强行执行，必须先提出疑问并确认后再动手。。
- **专业词汇**：在中文解释中，请尽量保留行业标准的英文原词（如 Component, Dependency Injection, Middleware）。
- **代码注释 (Code Comments)**：
    - 代码中的所有注释，必须强制使用**最基础、最简单的英文短句 (Basic, simple English)**，多用动词开头的祈使句（如
      `// Fetch user data`, `// Check if input is valid`）。
    - 如果遇到极度复杂的业务逻辑，请在简单的英文注释下方，补充一句简明的中文解释。

## 5. Git 版本控制 (Git Workflow & Commits)

- Git 仓库建立在项目根目录，所有的提交必须在根目录统筹。
- 必须严格遵守 Conventional Commits 规范，并在 Commit Message 中使用基础英文描述。
- **格式示例**:
    - `feat(frontend): add login page UI` (新增登录页面 UI)
    - `fix(backend): resolve null pointer in user service` (修复用户服务的空指针)
    - `docs(root): update global settings` (更新全局配置)

## 6. 安全红线与破坏性操作 (Security & Destructive Actions)

- **绝对禁止**在代码中硬编码任何数据库密码、JWT Secret 或第三方 API Key。敏感信息必须通过 `.env.local` (前端) 或
  `application-dev.yml` (后端) 注入。
- 在终端执行任何涉及删除 (`rm -rf`, `drop table`)、回滚 (`git reset --hard`) 等高危破坏性命令前，**必须先用中文向我解释原因，并请求我的明确授权
  **。