# pnpm 使用指南

## 安装 pnpm

如果尚未安装 pnpm，请执行：

```bash
npm install -g pnpm
```

或者使用官方推荐的安装方式：

```bash
# Windows
iwr https://get.pnpm.io/install.ps1 -useb | iex

# macOS/Linux
curl -fsSL https://get.pnpm.io/install.sh | sh
```

## 常用命令

### 安装依赖
```bash
pnpm install
# 或简写
pnpm i
```

### 添加依赖
```bash
# 生产依赖
pnpm add <package>

# 开发依赖
pnpm add -D <package>

# 指定版本
pnpm add <package>@^1.2.3

# 安装到工作区根目录（monorepo）
pnpm add -w <package>
```

### 移除依赖
```bash
pnpm remove <package>
```

### 更新依赖
```bash
pnpm update <package>

# 更新所有依赖
pnpm up
```

### 运行脚本
```bash
pnpm dev
pnpm build
pnpm format
```

### 查看依赖
```bash
# 查看已安装的包
pnpm list

# 查看依赖树
pnpm list --depth=2

# 检查过时的包
pnpm outdated
```

## 为什么使用 pnpm

1. **速度快**：比 npm/yarn 快 2-3 倍
2. **节省空间**：使用硬链接共享依赖，节省磁盘空间
3. **严格性**：防止幽灵依赖，确保依赖访问的正确性
4. **Monorepo 支持**：对 monorepo 架构有良好的支持

## 配置说明

项目中的 `.npmrc` 文件配置了：
- `shamefully-hoist=false` - 保持严格的依赖结构
- `strict-peer-dependencies=false` - 宽松处理 peer dependencies
- `registry=https://registry.npmmirror.com` - 使用国内镜像源加速

## 注意事项

- **严禁混用包管理器**：项目强制使用 pnpm，不要使用 npm/yarn
- **提交锁文件**：`pnpm-lock.yaml` 需要提交到 git
- **不要手动修改锁文件**：通过 pnpm 命令来管理依赖
