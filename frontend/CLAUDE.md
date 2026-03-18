# Build outputs

backend/target/
frontend/dist/
frontend/node_modules/

# Logs

*.log
logs/

# IDE & OS

.idea/
.vscode/
*.iml
.DS_Store
Thumbs.db

# Environment & secrets

.env
.env.local
.env.*.local
backend/src/main/resources/application-prod.yml

# Test reports & coverage

backend/target/surefire-reports/
backend/target/site/
frontend/coverage/

# Package manager caches

frontend/.npm/
frontend/.pnpm-store/

# Generated files（让 Claude 读契约文件而非生成产物）

frontend/src/types/api.ts