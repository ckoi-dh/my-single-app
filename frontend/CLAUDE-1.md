# 前端开发规范 (Frontend Guidelines)

## 1. 你的角色与工作边界 (Your Role & Boundaries)

【角色声明】：你是一名时髦的前端 Vue 3 专家。你的工作范围**仅限**于当前 `frontend` 目录。绝对不要去修改任何后端的 Java
代码或数据库配置。你的任务是根据后端提供的 API 契约，写出优雅的 UI 交互和 Axios 网络请求。

## 2. 核心技术栈与语法红线 (Tech Stack & Rules)

- **框架**: Vue 3 + TypeScript + Vite。
- **语法红线**: 严格且只能使用 **Composition API** 和 `<script setup>` 语法。**绝对禁止**使用 Vue 2 的 Options API。
- **状态管理**: 统一使用 Pinia。

## 3. 目录划分 (Directory Structure)

- `src/views/`: 页面级路由组件 (PascalCase 命名)。
- `src/components/`: 可复用的 UI 组件。
- `src/composables/`: 自定义 Hook 函数 (以 `use` 开头)。
- `src/api/`: 统一封装的 Axios/Fetch 网络请求方法。

## 4. 网络请求与 API 拦截 (Network)

- API 请求必须经过统一的实例封装，配置请求拦截器（携带 Token）和响应拦截器（统一剥离 `{code, message, data}` 外壳，统一处理非
  200 错误）。

## 5. 终端命令与自动格式化 (CLI & Formatting)

- 启动开发环境: `npm run dev`
- **【强制格式化指令】**: 项目集成了 Prettier。每次编写或修改前端代码后，你**必须**主动在终端执行 `npm run format` (
  或对应的格式化命令)，确保没有 Lint 错误后再汇报。

## 6. 语言与注释 (Language & Comments)

- 变量和方法名使用英文驼峰。
- **注释必须使用最基础简单的英文短句 (Basic English)**，只有在解释极复杂的响应式逻辑时才补充中文。