# 全栈项目全局架构与 Agent Team 协作规范

## 1. 团队协作与角色划分 (Agent Team Rules)

如果启用了 Agent Team 模式，队长（Architect）必须严格遵守以下任务分发红线：

- **后端队员 (Backend Dev)**: 只能分配到 `backend/` 目录工作。负责查阅 `doc/` 下的需求，设计数据库表，并优先输出
  RESTful API，完成后需将 API 契约同步导出至 `doc/api/openapi.yaml`。**严禁修改任何前端文件。**

- **前端队员 (Frontend Dev)**: 只能分配到 `frontend/` 目录工作。必须等待后端导出最新契约（`doc/api/openapi.yaml`）后，
  执行 `npx openapi-typescript` 同步类型，再进行页面和接口对接开发。
  **严禁修改任何后端或数据库文件。**

- **审查队员 (Reviewer)**: 只读角色，可跨 `backend/` 和 `frontend/` 目录阅读代码，
  **严禁修改任何文件**。由 Architect 在功能模块完成后手动触发，依据各目录 CLAUDE.md 输出审查报告。

- **队长职责 (Architect)**: 统筹规划，按以下顺序严格串行派发任务：
    1. **Backend Dev** 完成接口开发、编译通过、导出契约至 `doc/api/openapi.yaml`
    2. **Frontend Dev** 同步最新契约后完成页面对接
    3. **Reviewer** 执行增量代码 Code Review，执行完成将Review报告输出给用户，让用户决定哪些需要修改

**如果启用了 Agent Team 模式，Architect 只负责任务拆解与调度，严禁直接修改任何代码文件。**
**Backend Dev 汇报涉及 API 变更时，必须在派发前端任务前先指派 Frontend Dev 同步最新契约。**
**Frontend Dev 依据最新契约做对应的页面开发，如果出现与需求不一致部分，通知 Architect 进行协调**

## 2. 项目概览与全栈契约 (Project Overview)

- 本项目是前后端分离 Monorepo。
- **前端技术栈**: Vue 3 + TypeScript + Vite + Pinia + Vue Router + Element Plus + Axios + Prettier + ESLint
- **后端技术栈**: Java 17 + Spring Boot 2.7.18 + MyBatis-Plus + MySQL + Druid + MapStruct + Lombok + Spring Security
    + JJWT
- **子目录规范**: `backend/` 和 `frontend/` 目录下各自有更详细的 CLAUDE.md 开发规范
- **API 契约文档** 统一维护在 `doc/api/` 目录，以 OpenAPI YAML 格式存储，后端定义完成后需更新此目录，前端以此为唯一对接依据。
- 所有接口响应统一使用 `{ code: 200, message: "success", data: { ... } }` 结构。
- 规范性的项目变更，需要同步更新对应的 CLAUDE.md 文件。

## 3. 目录结构参考 (Directory Structure)

```
root/
├── CLAUDE.md              # 全局规范（本文件）
├── doc/
│   ├── api/
│   ├── prd/
│   ├── prompt/
│   └── sql/
│       ├── schema.sql            # 全量建表脚本（始终最新）
│       ├── data.sql              # 全量数据预置脚本（始终最新）
│       └── changelog/            # 版本增量变更脚本
│           └── v1.0.0__init.sql
├── backend/
│   ├── CLAUDE.md          # 后端专项规范
│   └── src/
├── frontend/
│   ├── CLAUDE.md          # 前端专项规范
│   └── src/
├── review/
│   └── CLAUDE.md          # 代码审查规范
└── .gitignore
```

## 4. 项目快速启动 (Quick Start)

- **后端启动**: 进入 `backend/` 目录，执行 `.\mvnw.cmd spring-boot:run`
- **前端启动**: 进入 `frontend/` 目录，执行 `npm run dev`

## 5. 编码默认约定 (Coding Defaults)

- **后端**: 新建类默认放在对应功能包下（如 `controller/`, `service/`, `mapper/`），禁止平铺在根包。
- **SQL 脚本**: 所有脚本存入 `doc/sql/`，按以下结构维护：
    - `schema.sql` —— 全量建表脚本，始终保持**最新完整表结构**，每次表结构变动必须同步更新。
    - `data.sql` —— 全量数据预置脚本（字典、初始配置等），每次预置数据变动必须同步更新。
    - `changelog/v{x.y.z}__{描述}.sql` —— 版本变更增量脚本（如 `changelog/v1.1.0__add_user_avatar.sql`
      ），只记录该版本的结构或数据变化，不可修改历史文件。
    - 禁止直接在数据库客户端执行未留档的结构变更。
- **前端**: 新建组件默认使用 `<script setup lang="ts">` 语法，文件名使用 PascalCase。
- **TypeScript**: 禁止使用 `any` 类型，必须定义明确的 Interface 或 Type。
- **异常处理**: 所有异常必须被捕获处理，禁止空 catch 块。

## 6. 沟通与语言学习策略 (Communication & Language Learning)

- **对话与解释**：请使用中文与我进行日常探讨、原理解释和错误排查；若需求描述不够清晰、或现有方案存在明显缺陷，不得强行执行，必须先提出疑问并确认后再动手。
- **专业词汇**：在中文解释中，请尽量保留行业标准的英文原词（如 Component, Dependency Injection, Middleware）。
- **代码注释 (Code Comments)**：
    - 代码中的所有注释，必须强制使用**最基础、最简单的英文短句 (Basic, simple English)**，多用动词开头的祈使句（如
      `// Fetch user data`, `// Check if input is valid`）。
    - 如果遇到极度复杂的业务逻辑，请在简单的英文注释下方，补充一句简明的中文解释。

## 7. Git 版本控制 (Git Workflow & Commits)

- Git 仓库建立在项目根目录，所有的提交必须在根目录统筹。
- 必须严格遵守 Conventional Commits 规范，并在 Commit Message 中使用简明的中文描述变更信息。
- **格式示例**:
    - `feat(frontend): add login page UI`
    - `fix(backend): resolve null pointer in user service`
    - `docs(root): update global CLAUDE.md`

## 8. 安全红线与破坏性操作 (Security & Destructive Actions)

- **绝对禁止**在代码中硬编码任何数据库密码、JWT Secret 或第三方 API Key。敏感信息必须通过 `.env.local` (前端) 或
  `application-dev.yml` (后端) 注入。
- 以下操作必须先用中文说明理由，等待我明确回复"确认执行"后才能执行：
    - 任何 `DROP`、`TRUNCATE`、`DELETE`（无 WHERE 条件）的 SQL
    - `rm -rf`、`git reset --hard`、`git clean -fd` 、`git push`
    - 修改 `application.yml`（生产配置）或 `.env` 文件
    - 安装新的 Maven Dependency 或 npm Package（需说明引入原因）