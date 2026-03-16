# 全栈项目全局架构与 Agent Team 协作规范

## 1. 团队协作与角色划分 (Agent Team Rules)

如果启用了 Agent Team 模式，队长（Leader）必须严格遵守以下任务分发红线：

- **后端队员 (Java Expert)**: 只能分配到 `backend/` 目录工作。负责查阅 `docs/` 下的需求，设计数据库表，并优先输出 RESTful
  API。**严禁修改任何前端文件。**
- **前端队员 (Vue Expert)**: 只能分配到 `frontend/` 目录工作。必须等待后端确定 API JSON 契约后，再进行页面和接口对接开发。*
  *严禁修改任何后端或数据库文件。**
- **队长职责**: 统筹规划，按顺序派发任务。在开发前引导队员读取 `docs/` 目录下的 PRD 或原型图。

## 2. 项目概览与全栈契约 (Project Overview)

- 本项目是前后端分离 Monorepo。`frontend/` 是 Vue3，`backend/` 是 Spring Boot 2.7.18。
- **全栈协作铁律**：后端是数据契约的制定者。前端必须严格按照后端定义的 `{code, message, data}` 结构进行对接，严禁前端自行捏造数据字段。

## 3. 沟通与语言学习策略 (Language Learning)

- **对话与解释**：请使用中文与我沟通，保留专业技术词汇的英文原词（如 Middleware, Component）。
- **代码注释要求**：所有生成的代码，**必须强制使用最基础、最简单的英文短句 (Basic, simple English)** 编写注释，多用动词开头的祈使句（如
  `// Save user data`）。如果遇到极度复杂的逻辑，可以在简单的英文注释下方补充一句中文解释。

## 4. Git 版本控制与安全红线 (Git & Security)

- 提交格式：遵循 Conventional Commits（如 `feat(auth): add login API`）。
- **绝对禁止**在代码中硬编码任何敏感密码或 Token 密钥。
- 执行任何高危破坏性命令（如 `rm -rf`, `drop table`, `git reset --hard`）前，**必须先用中文向我解释并请求明确授权**。