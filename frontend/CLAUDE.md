# 前端开发规范 (Frontend Guidelines)

## 1. 核心技术栈 (Tech Stack)

- **框架**: Vue 3 + TypeScript
- **语法红线**: 严格且只能使用 **Composition API** 和 `<script setup>` 语法，**绝对禁止**使用 Vue 2 的 Options API (
  `data()`, `methods: {}`)。
- **状态与路由**: 状态管理使用 Pinia，路由使用 Vue Router 4。
- **构建工具**: Vite

## 2. 目录结构与组件规范 (Directory & Components)

- **命名规范**: 所有 Vue 组件文件名必须使用大驼峰命名法 (PascalCase，例如 `UserProfile.vue`)。
- **目录划分**:
    - `src/views/`: 存放页面级路由组件。
    - `src/components/`: 存放可复用的 UI 组件。
    - `src/composables/`: 存放自定义的 Hook 函数，命名必须以 `use` 开头（如 `useUserAuth.ts`）。
    - `src/api/`: 统一存放 Axios/Fetch 网络请求方法。

## 3. 语言与注释学习规范 (Language & Comments)

- **对话语言**: 中文。代码变量名: 英文驼峰。
- **代码注释**: 强制使用**最基础的英文短句 (Basic, simple English)** 编写（如 `// Save user info to Pinia store`
  ）。只有在解释复杂的响应式逻辑 (Reactivity) 或生命周期时，才允许在英文下方附加中文解释。

## 4. 网络请求与 API 拦截 (Network & API)

- 所有的后端 API 请求都必须经过统一的 Axios 实例封装（或 Fetch 封装）。
- 必须配置全局拦截器 (Interceptor)：
    - 请求拦截器：自动在 Header 中附带 Token。
    - 响应拦截器：统一剥离后端返回的 `{ code, message, data }` 外壳，直接向组件返回 `data`。如果 `code` 不为
      200，统一弹出错误提示并抛出异常。

## 5. 终端命令与自动格式化 (CLI & Auto-Formatting)

- 启动开发环境: `npm run dev`
- **【强制格式化指令】**: 项目集成了 Prettier。在你完成任何前端代码的编写、修改或重构后，你**必须**主动在终端执行
  `npm run format`（或对应的格式化脚本），确保代码风格符合规范，并且没有任何 Lint 报错后，再向我汇报任务完成。