# 全栈项目全局架构与协作规范 (Global Architecture & Rules)

## 1. 项目概览 (Project Overview)

- 本项目是一个前后端分离的 Monorepo（单体仓库）。
- `frontend/`: 基于 Vue 3 + TypeScript 构建的前端应用。
- `backend/`: 基于 Java 17 + Spring Boot 2.7.18 构建的后端服务。
- **全栈协作铁律**：后端优先定义 API 数据结构（契约），前端必须严格按照后端定义的数据结构进行对接，严禁前端自行捏造数据字段。

## 2. 沟通与语言学习策略 (Communication & Language Learning)

- **对话与解释**：请使用中文与我进行日常探讨、原理解释和错误排查。
- **专业词汇**：在中文解释中，请尽量保留行业标准的英文原词（如 Component, Dependency Injection, Middleware），帮助我建立纯正的编程语感。
- **代码注释 (Code Comments)**：
    - 代码中的所有注释，必须强制使用**最基础、最简单的英文短句 (Basic, simple English)**，多用动词开头的祈使句（如
      `// Fetch user data`, `// Check if input is valid`）。
    - 如果遇到极度复杂的业务逻辑，请在简单的英文注释下方，补充一句简明的中文解释。

## 3. Git 版本控制 (Git Workflow & Commits)

- Git 仓库建立在项目根目录，所有的提交必须在根目录统筹。
- 必须严格遵守 Conventional Commits 规范，并在 Commit Message 中使用基础英文描述。
- **格式示例**:
    - `feat(frontend): add login page UI` (新增登录页面 UI)
    - `fix(backend): resolve null pointer in user service` (修复用户服务的空指针)
    - `docs(root): update global settings` (更新全局配置)

## 4. 安全红线与破坏性操作 (Security & Destructive Actions)

- **绝对禁止**在代码中硬编码任何数据库密码、JWT Secret 或第三方 API Key。敏感信息必须通过 `.env.local` (前端) 或
  `application-dev.yml` (后端) 注入。
- 在终端执行任何涉及删除 (`rm -rf`, `drop table`)、回滚 (`git reset --hard`) 等高危破坏性命令前，**必须先用中文向我解释原因，并请求我的明确授权
  **。