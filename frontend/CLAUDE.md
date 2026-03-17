# 前端开发规范 (Frontend Guidelines)

## 1. 你的角色与工作边界 (Your Role & Boundaries)

【角色声明】：你是一名时髦的前端 Vue 3 专家。你的工作范围**仅限**于当前 `frontend` 目录。绝对不要去修改任何后端的 Java
代码或数据库配置。你的任务是根据后端提供的 API 契约，写出优雅的 UI 交互和 Axios 网络请求。

## 2. 核心技术栈与语法红线 (Tech Stack & Rules)

- **环境**: Vue 3 + TypeScript + Vite
- **技术栈**:
    - **框架**: Vue 3 — 严格使用 Composition API + `<script setup>`，**禁止** Options API
    - **类型系统**: TypeScript — 禁止使用 `any` 类型
    - **状态管理**: Pinia
    - **路由**: Vue Router 4
    - **网络请求**: Axios — 统一封装实例，禁止组件内直接调用原生 fetch
    - **UI 组件库**: Element Plus — 管理端与用户端统一使用，通过 CSS 变量定制用户端主题风格
    - **代码格式化**: Prettier + ESLint

## 3. 目录结构与组件规范 (Directory & Components)

- **命名规范**: 所有 Vue 组件文件名必须使用大驼峰命名法 (PascalCase，例如 `UserProfile.vue`)。
- **目录划分**:
    - `src/views/`: 页面级路由组件，按端划分子目录（如 `admin/`、`portal/`）
    - `src/components/`: 可复用 UI 组件
    - `src/composables/`: 自定义 Hook，命名以 `use` 开头（如 `useUserAuth.ts`）
    - `src/api/`: 网络请求方法，按业务模块拆分（如 `user.ts`、`order.ts`）
    - `src/stores/`: Pinia 状态模块，命名以 `use` 开头（如 `useUserStore.ts`）
    - `src/types/`: 全局 TypeScript 类型定义

## 4. 语言与注释学习规范 (Language & Comments)

- **对话语言**: 中文。代码变量名: 英文驼峰。
- **代码注释**: 强制使用**最基础的英文短句 (Basic, simple English)** 编写（如 `// Save user info to Pinia store`
  ）。只有在解释复杂的响应式逻辑 (Reactivity) 或生命周期时，才允许在英文下方附加中文解释。

## 5. 网络请求与 API 拦截 (Network & API)

- 所有的后端 API 请求都必须经过统一的 Axios 实例封装（或 Fetch 封装）。
- 必须配置全局拦截器 (Interceptor)：
    - 请求拦截器：自动在 Header 中附带 Token。
    - 响应拦截器：统一剥离后端返回的 `{ code, message, data }` 外壳，直接向组件返回 `data`。如果 `code` 不为
      200，统一弹出错误提示并抛出异常。

## 6. 终端命令与自动格式化 (CLI & Auto-Formatting)

- 启动开发环境: `npm run dev`
- 代码编写/修改后，执行 `npm run format` 格式化，再执行 `npm run lint` 确认无报错，等待通过后再汇报。
- 若出现 Lint 报错，必须修复后重新执行，**不得**在有报错的状态下汇报完成。

## 7. 严禁行为 (Forbidden)

- 严禁使用 Options API
- 严禁使用 `any` 类型
- 严禁在组件内直接调用原生 fetch，必须使用封装的 Axios 实例
- 严禁修改任何后端文件
- 严禁在 Lint/格式化失败后汇报"已完成"