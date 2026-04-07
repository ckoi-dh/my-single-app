-- =============================================
-- Database Data
-- 数据库数据预置脚本
-- Version: 1.1.0
-- Created: 2026-04-06
-- Description: Initialize complete blog system data
-- =============================================

USE app_ckoi;

-- =============================================
-- 1. Initialize System Roles
-- 初始化系统角色
-- =============================================

INSERT INTO sys_role (name, code, description, sort_order, status) VALUES
  ('Super Admin', 'SUPER_ADMIN', 'System super administrator with all permissions', 1, 1),
  ('Admin', 'ADMIN', 'System administrator with management permissions', 2, 1),
  ('Author', 'AUTHOR', 'Content creator with article management permissions', 3, 1),
  ('Visitor', 'VISITOR', 'Regular user with read and comment permissions', 4, 1)
ON DUPLICATE KEY UPDATE update_time = CURRENT_TIMESTAMP;

-- =============================================
-- 2. Initialize System Permissions
-- 初始化系统权限
-- =============================================

INSERT INTO sys_permission (name, code, type, parent_id, path, component, icon, sort_order, status) VALUES
  ('Dashboard', 'dashboard', 1, 0, '/dashboard', 'Dashboard', 'dashboard', 1, 1),
  ('User Management', 'user:manage', 1, 0, '/admin/user', 'UserManagement', 'user', 2, 1),
  ('Role Management', 'role:manage', 1, 0, '/admin/role', 'RoleManagement', 'role', 3, 1),
  ('Permission Management', 'permission:manage', 1, 0, '/admin/permission', 'PermissionManagement', 'permission', 4, 1),
  ('Article Management', 'article:manage', 1, 0, '/admin/article', 'ArticleManagement', 'article', 5, 1),
  ('Category Management', 'category:manage', 1, 0, '/admin/category', 'CategoryManagement', 'category', 6, 1),
  ('Tag Management', 'tag:manage', 1, 0, '/admin/tag', 'TagManagement', 'tag', 7, 1),
  ('Comment Management', 'comment:manage', 1, 0, '/admin/comment', 'CommentManagement', 'comment', 8, 1),
  ('Config Management', 'config:manage', 1, 0, '/admin/config', 'ConfigManagement', 'config', 9, 1),
  ('SEO Management', 'seo:manage', 1, 0, '/admin/seo', 'SeoManagement', 'seo', 10, 1),
  ('Analytics', 'analytics:view', 1, 0, '/admin/analytics', 'Analytics', 'analytics', 11, 1),
  ('Create Article', 'article:create', 2, 5, NULL, NULL, NULL, 1, 1),
  ('Update Article', 'article:update', 2, 5, NULL, NULL, NULL, 2, 1),
  ('Delete Article', 'article:delete', 2, 5, NULL, NULL, NULL, 3, 1),
  ('Approve Comment', 'comment:approve', 2, 8, NULL, NULL, NULL, 1, 1),
  ('Reject Comment', 'comment:reject', 2, 8, NULL, NULL, NULL, 2, 1),
  ('Delete Comment', 'comment:delete', 2, 8, NULL, NULL, NULL, 3, 1)
ON DUPLICATE KEY UPDATE update_time = CURRENT_TIMESTAMP;

-- =============================================
-- 3. Initialize Role-Permission Relations
-- 初始化角色权限关联
-- =============================================

-- Super Admin has all permissions
INSERT IGNORE INTO sys_role_permission (role_id, permission_id)
SELECT 1, id FROM sys_permission;

-- Admin has most permissions except super admin specific ones
INSERT IGNORE INTO sys_role_permission (role_id, permission_id)
SELECT 2, id FROM sys_permission WHERE code NOT IN ('permission:manage');

-- Author has article and comment permissions
INSERT IGNORE INTO sys_role_permission (role_id, permission_id)
SELECT 3, id FROM sys_permission WHERE code IN ('article:manage', 'article:create', 'article:update', 'article:delete', 'comment:manage');

-- Visitor has read permissions
INSERT IGNORE INTO sys_role_permission (role_id, permission_id)
SELECT 4, id FROM sys_permission WHERE code IN ('dashboard', 'analytics:view');

-- =============================================
-- 4. Initialize Admin User
-- 初始化管理员用户
-- =============================================

-- Password: admin123 (BCrypt encoded)
INSERT INTO sys_user (username, password, nickname, email, avatar, bio, status, role_type) VALUES
  ('admin', '$2a$10$eWdKjVfJxVvZ7xY9yX8wQOeR5tY6uI7oP8aS9dF0gH1jK2lZ3xC4vB5nM6',
   'Admin', 'admin@example.com', NULL, 'System administrator', 1, 1)
ON DUPLICATE KEY UPDATE update_time = CURRENT_TIMESTAMP;

-- =============================================
-- 5. Initialize Test User
-- 初始化测试用户
-- =============================================

-- Password: user123 (BCrypt encoded)
INSERT INTO sys_user (username, password, nickname, email, avatar, bio, status, role_type) VALUES
  ('testuser', '$2a$10$eWdKjVfJxVvZ7xY9yX8wQOeR5tY6uI7oP8aS9dF0gH1jK2lZ3xC4vB5nM6',
   'Test User', 'test@example.com', NULL, 'Regular user', 1, 4)
ON DUPLICATE KEY UPDATE update_time = CURRENT_TIMESTAMP;

-- =============================================
-- 6. Initialize Blog Categories
-- 初始化博客分类
-- =============================================

INSERT INTO blog_category (name, description, parent_id, sort_order, status) VALUES
  ('Technology', 'Technology and programming', 0, 1, 1),
  ('Life', 'Life and experiences', 0, 2, 1),
  ('Travel', 'Travel and adventures', 0, 3, 1),
  ('Food', 'Food and cooking', 0, 4, 1),
  ('Science', 'Science and research', 0, 5, 1)
ON DUPLICATE KEY UPDATE update_time = CURRENT_TIMESTAMP;

-- =============================================
-- 7. Initialize Blog Tags
-- 初始化博客标签
-- =============================================

INSERT INTO blog_tag (name, description, color, sort_order, status) VALUES
  ('Java', 'Java programming language', '#5382a1', 1, 1),
  ('Python', 'Python programming language', '#3776ab', 2, 1),
  ('Web Development', 'Web development technologies', '#42b883', 3, 1),
  ('AI', 'Artificial intelligence', '#ff6b6b', 4, 1),
  ('Database', 'Database technologies', '#ffa726', 5, 1)
ON DUPLICATE KEY UPDATE update_time = CURRENT_TIMESTAMP;

-- =============================================
-- 8. Initialize Blog Configs
-- 初始化博客配置
-- =============================================

INSERT INTO blog_config (config_key, config_value, config_type, description, sort_order, status) VALUES
  ('site.name', 'My Blog', 'string', 'Website name', 1, 1),
  ('site.description', 'A modern blog system', 'string', 'Website description', 2, 1),
  ('site.keywords', 'blog, programming, technology', 'string', 'Website keywords', 3, 1),
  ('site.logo', '', 'string', 'Website logo URL', 4, 1),
  ('site.favicon', '', 'string', 'Website favicon URL', 5, 1),
  ('article.publish.autoApprove', 'true', 'boolean', 'Auto approve published articles', 6, 1),
  ('comment.publish.autoApprove', 'true', 'boolean', 'Auto approve comments', 7, 1),
  ('article.page.size', '10', 'number', 'Articles per page', 8, 1),
  ('comment.page.size', '20', 'number', 'Comments per page', 9, 1),
  ('site.maintenance', 'false', 'boolean', 'Maintenance mode', 10, 1)
ON DUPLICATE KEY UPDATE update_time = CURRENT_TIMESTAMP;

-- =============================================
-- 9. Initialize SEO Meta Data
-- 初始化SEO元数据
-- =============================================

INSERT INTO blog_seo_meta (page_name, page_path, title, keywords, description) VALUES
  ('Home', '/', 'My Blog - Welcome', 'blog, programming, technology', 'A modern blog system featuring technology and programming content'),
  ('Article List', '/articles', 'Articles - My Blog', 'articles, blog posts, content', 'Browse all articles on my blog'),
  ('Article Detail', '/article/{id}', '{title} - My Blog', '', '{summary}'),
  ('About', '/about', 'About - My Blog', 'about, contact', 'Learn more about me and this blog'),
  ('Admin Dashboard', '/admin', 'Admin Dashboard - My Blog', '', 'Admin management dashboard')
ON DUPLICATE KEY UPDATE update_time = CURRENT_TIMESTAMP;

-- =============================================
-- 10. Initialize Demo Data
-- 初始化演示数据
-- =============================================

INSERT INTO demo (name, description, status) VALUES
  ('Demo 1', 'This is the first demo', 1),
  ('Demo 2', 'This is the second demo', 1),
  ('Demo 3', 'This is the third demo', 0)
ON DUPLICATE KEY UPDATE update_time = CURRENT_TIMESTAMP;

-- =============================================
-- Data initialization completed
-- 数据初始化完成
-- =============================================
