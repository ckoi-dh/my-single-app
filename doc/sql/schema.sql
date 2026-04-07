-- =============================================
-- Database Schema
-- 数据库初始化脚本
-- Version: 1.1.0
-- Created: 2026-04-06
-- Description: Complete blog system database schema
-- =============================================

-- Create database if not exists
-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS app_ckoi DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE app_ckoi;

-- =============================================
-- 1. User System (sys_user)
-- 用户系统表
-- =============================================

-- Drop tables if exists (for development only)
-- 删除表（仅用于开发环境）
DROP TABLE IF EXISTS sys_user_role;
DROP TABLE IF EXISTS sys_role_permission;
DROP TABLE IF EXISTS sys_permission;
DROP TABLE IF EXISTS sys_role;
DROP TABLE IF EXISTS sys_user;

-- Create sys_user table
-- 创建用户表
CREATE TABLE sys_user (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'User ID',
  username VARCHAR(64) NOT NULL COMMENT 'Username',
  password VARCHAR(255) NOT NULL COMMENT 'Password (BCrypt encrypted)',
  nickname VARCHAR(64) NULL COMMENT 'Nickname',
  email VARCHAR(128) NULL COMMENT 'Email',
  avatar VARCHAR(512) NULL COMMENT 'Avatar URL',
  bio VARCHAR(512) NULL COMMENT 'Biography',
  status TINYINT NOT NULL DEFAULT 1 COMMENT 'User status (0-inactive, 1-active, 2-blocked)',
  role_type TINYINT NOT NULL DEFAULT 4 COMMENT 'Role type (1-SUPER_ADMIN, 2-ADMIN, 3-AUTHOR, 4-VISITOR)',
  last_login_time DATETIME NULL COMMENT 'Last login time',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
  deleted TINYINT NOT NULL DEFAULT 0 COMMENT 'Deleted flag (0-not deleted, 1-deleted)',
  PRIMARY KEY (id),
  UNIQUE KEY uk_username (username),
  UNIQUE KEY uk_email (email),
  KEY idx_status (status),
  KEY idx_role_type (role_type),
  KEY idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='System user table';

-- Create sys_role table
-- 创建角色表
CREATE TABLE sys_role (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Role ID',
  name VARCHAR(64) NOT NULL COMMENT 'Role name',
  code VARCHAR(64) NOT NULL COMMENT 'Role code',
  description VARCHAR(512) NULL COMMENT 'Role description',
  sort_order INT NOT NULL DEFAULT 0 COMMENT 'Sort order',
  status TINYINT NOT NULL DEFAULT 1 COMMENT 'Role status (0-inactive, 1-active)',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
  deleted TINYINT NOT NULL DEFAULT 0 COMMENT 'Deleted flag (0-not deleted, 1-deleted)',
  PRIMARY KEY (id),
  UNIQUE KEY uk_code (code),
  KEY idx_status (status),
  KEY idx_sort_order (sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='System role table';

-- Create sys_permission table
-- 创建权限表
CREATE TABLE sys_permission (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Permission ID',
  name VARCHAR(64) NOT NULL COMMENT 'Permission name',
  code VARCHAR(64) NOT NULL COMMENT 'Permission code',
  type TINYINT NOT NULL DEFAULT 1 COMMENT 'Permission type (1-menu, 2-button)',
  parent_id BIGINT NOT NULL DEFAULT 0 COMMENT 'Parent permission ID',
  path VARCHAR(255) NULL COMMENT 'Menu path',
  component VARCHAR(255) NULL COMMENT 'Menu component',
  icon VARCHAR(64) NULL COMMENT 'Menu icon',
  sort_order INT NOT NULL DEFAULT 0 COMMENT 'Sort order',
  status TINYINT NOT NULL DEFAULT 1 COMMENT 'Permission status (0-inactive, 1-active)',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
  deleted TINYINT NOT NULL DEFAULT 0 COMMENT 'Deleted flag (0-not deleted, 1-deleted)',
  PRIMARY KEY (id),
  UNIQUE KEY uk_code (code),
  KEY idx_parent_id (parent_id),
  KEY idx_status (status),
  KEY idx_sort_order (sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='System permission table';

-- Create sys_user_role table
-- 创建用户角色关联表
CREATE TABLE sys_user_role (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'User role ID',
  user_id BIGINT NOT NULL COMMENT 'User ID',
  role_id BIGINT NOT NULL COMMENT 'Role ID',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
  PRIMARY KEY (id),
  UNIQUE KEY uk_user_role (user_id, role_id),
  KEY idx_user_id (user_id),
  KEY idx_role_id (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='System user role relation table';

-- Create sys_role_permission table
-- 创建角色权限关联表
CREATE TABLE sys_role_permission (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Role permission ID',
  role_id BIGINT NOT NULL COMMENT 'Role ID',
  permission_id BIGINT NOT NULL COMMENT 'Permission ID',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
  PRIMARY KEY (id),
  UNIQUE KEY uk_role_permission (role_id, permission_id),
  KEY idx_role_id (role_id),
  KEY idx_permission_id (permission_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='System role permission relation table';

-- =============================================
-- 2. Blog Article System (blog_article)
-- 文章系统表
-- =============================================

-- Drop tables if exists (for development only)
-- 删除表（仅用于开发环境）
DROP TABLE IF EXISTS blog_article_tag;
DROP TABLE IF EXISTS blog_article_category;
DROP TABLE IF EXISTS blog_tag;
DROP TABLE IF EXISTS blog_category;
DROP TABLE IF EXISTS blog_article;

-- Create blog_category table
-- 创建文章分类表
CREATE TABLE blog_category (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Category ID',
  name VARCHAR(64) NOT NULL COMMENT 'Category name',
  description VARCHAR(512) NULL COMMENT 'Category description',
  parent_id BIGINT NOT NULL DEFAULT 0 COMMENT 'Parent category ID',
  sort_order INT NOT NULL DEFAULT 0 COMMENT 'Sort order',
  status TINYINT NOT NULL DEFAULT 1 COMMENT 'Category status (0-inactive, 1-active)',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
  deleted TINYINT NOT NULL DEFAULT 0 COMMENT 'Deleted flag (0-not deleted, 1-deleted)',
  PRIMARY KEY (id),
  UNIQUE KEY uk_name (name),
  KEY idx_parent_id (parent_id),
  KEY idx_status (status),
  KEY idx_sort_order (sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Blog category table';

-- Create blog_tag table
-- 创建文章标签表
CREATE TABLE blog_tag (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Tag ID',
  name VARCHAR(64) NOT NULL COMMENT 'Tag name',
  description VARCHAR(512) NULL COMMENT 'Tag description',
  color VARCHAR(16) NULL COMMENT 'Tag color',
  sort_order INT NOT NULL DEFAULT 0 COMMENT 'Sort order',
  status TINYINT NOT NULL DEFAULT 1 COMMENT 'Tag status (0-inactive, 1-active)',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
  deleted TINYINT NOT NULL DEFAULT 0 COMMENT 'Deleted flag (0-not deleted, 1-deleted)',
  PRIMARY KEY (id),
  UNIQUE KEY uk_name (name),
  KEY idx_status (status),
  KEY idx_sort_order (sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Blog tag table';

-- Create blog_article table
-- 创建文章表
CREATE TABLE blog_article (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Article ID',
  title VARCHAR(255) NOT NULL COMMENT 'Article title',
  summary VARCHAR(512) NULL COMMENT 'Article summary',
  content LONGTEXT NOT NULL COMMENT 'Article content',
  cover_image VARCHAR(512) NULL COMMENT 'Cover image URL',
  author_id BIGINT NOT NULL COMMENT 'Author ID',
  status TINYINT NOT NULL DEFAULT 1 COMMENT 'Article status (0-draft, 1-published, 2-private, 3-scheduled, 4-banned)',
  publish_time DATETIME NULL COMMENT 'Publish time',
  view_count BIGINT NOT NULL DEFAULT 0 COMMENT 'View count',
  like_count BIGINT NOT NULL DEFAULT 0 COMMENT 'Like count',
  comment_count BIGINT NOT NULL DEFAULT 0 COMMENT 'Comment count',
  favorite_count BIGINT NOT NULL DEFAULT 0 COMMENT 'Favorite count',
  share_count BIGINT NOT NULL DEFAULT 0 COMMENT 'Share count',
  seo_title VARCHAR(255) NULL COMMENT 'SEO title',
  seo_keywords VARCHAR(255) NULL COMMENT 'SEO keywords',
  seo_description VARCHAR(512) NULL COMMENT 'SEO description',
  is_sticky TINYINT NOT NULL DEFAULT 0 COMMENT 'Is sticky (0-no, 1-yes)',
  is_featured TINYINT NOT NULL DEFAULT 0 COMMENT 'Is featured (0-no, 1-yes)',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
  deleted TINYINT NOT NULL DEFAULT 0 COMMENT 'Deleted flag (0-not deleted, 1-deleted)',
  PRIMARY KEY (id),
  KEY idx_author_id (author_id),
  KEY idx_status (status),
  KEY idx_publish_time (publish_time),
  KEY idx_view_count (view_count),
  KEY idx_create_time (create_time),
  FULLTEXT KEY ft_title_summary (title, summary)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Blog article table';

-- Create blog_article_category table
-- 创建文章分类关联表
CREATE TABLE blog_article_category (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Article category ID',
  article_id BIGINT NOT NULL COMMENT 'Article ID',
  category_id BIGINT NOT NULL COMMENT 'Category ID',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
  PRIMARY KEY (id),
  UNIQUE KEY uk_article_category (article_id, category_id),
  KEY idx_article_id (article_id),
  KEY idx_category_id (category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Blog article category relation table';

-- Create blog_article_tag table
-- 创建文章标签关联表
CREATE TABLE blog_article_tag (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Article tag ID',
  article_id BIGINT NOT NULL COMMENT 'Article ID',
  tag_id BIGINT NOT NULL COMMENT 'Tag ID',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
  PRIMARY KEY (id),
  UNIQUE KEY uk_article_tag (article_id, tag_id),
  KEY idx_article_id (article_id),
  KEY idx_tag_id (tag_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Blog article tag relation table';

-- =============================================
-- 3. Blog Comment System (blog_comment)
-- 评论系统表
-- =============================================

-- Drop tables if exists (for development only)
-- 删除表（仅用于开发环境）
DROP TABLE IF EXISTS blog_comment;

-- Create blog_comment table
-- 创建评论表
CREATE TABLE blog_comment (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Comment ID',
  article_id BIGINT NOT NULL COMMENT 'Article ID',
  user_id BIGINT NOT NULL COMMENT 'User ID',
  parent_id BIGINT NOT NULL DEFAULT 0 COMMENT 'Parent comment ID',
  content VARCHAR(1024) NOT NULL COMMENT 'Comment content',
  status TINYINT NOT NULL DEFAULT 1 COMMENT 'Comment status (0-pending, 1-approved, 2-rejected)',
  like_count BIGINT NOT NULL DEFAULT 0 COMMENT 'Like count',
  reply_count BIGINT NOT NULL DEFAULT 0 COMMENT 'Reply count',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
  deleted TINYINT NOT NULL DEFAULT 0 COMMENT 'Deleted flag (0-not deleted, 1-deleted)',
  PRIMARY KEY (id),
  KEY idx_article_id (article_id),
  KEY idx_user_id (user_id),
  KEY idx_parent_id (parent_id),
  KEY idx_status (status),
  KEY idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Blog comment table';

-- =============================================
-- 4. Blog Interaction System (blog_interaction)
-- 互动系统表
-- =============================================

-- Drop tables if exists (for development only)
-- 删除表（仅用于开发环境）
DROP TABLE IF EXISTS blog_favorite;
DROP TABLE IF EXISTS blog_like;

-- Create blog_like table
-- 创建点赞表
CREATE TABLE blog_like (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Like ID',
  user_id BIGINT NOT NULL COMMENT 'User ID',
  target_type TINYINT NOT NULL COMMENT 'Target type (1-article, 2-comment)',
  target_id BIGINT NOT NULL COMMENT 'Target ID',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
  PRIMARY KEY (id),
  UNIQUE KEY uk_user_target (user_id, target_type, target_id),
  KEY idx_user_id (user_id),
  KEY idx_target (target_type, target_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Blog like table';

-- Create blog_favorite table
-- 创建收藏表
CREATE TABLE blog_favorite (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Favorite ID',
  user_id BIGINT NOT NULL COMMENT 'User ID',
  article_id BIGINT NOT NULL COMMENT 'Article ID',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
  PRIMARY KEY (id),
  UNIQUE KEY uk_user_article (user_id, article_id),
  KEY idx_user_id (user_id),
  KEY idx_article_id (article_id),
  KEY idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Blog favorite table';

-- =============================================
-- 5. Blog System Management (blog_config)
-- 系统管理表
-- =============================================

-- Drop tables if exists (for development only)
-- 删除表（仅用于开发环境）
DROP TABLE IF EXISTS blog_seo_meta;
DROP TABLE IF EXISTS blog_config;

-- Create blog_config table
-- 创建配置表
CREATE TABLE blog_config (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Config ID',
  config_key VARCHAR(64) NOT NULL COMMENT 'Config key',
  config_value TEXT NULL COMMENT 'Config value',
  config_type VARCHAR(32) NOT NULL DEFAULT 'string' COMMENT 'Config type (string, number, boolean, json)',
  description VARCHAR(512) NULL COMMENT 'Config description',
  sort_order INT NOT NULL DEFAULT 0 COMMENT 'Sort order',
  status TINYINT NOT NULL DEFAULT 1 COMMENT 'Config status (0-inactive, 1-active)',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
  deleted TINYINT NOT NULL DEFAULT 0 COMMENT 'Deleted flag (0-not deleted, 1-deleted)',
  PRIMARY KEY (id),
  UNIQUE KEY uk_config_key (config_key),
  KEY idx_status (status),
  KEY idx_sort_order (sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Blog config table';

-- Create blog_seo_meta table
-- 创建SEO元数据表
CREATE TABLE blog_seo_meta (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'SEO meta ID',
  page_name VARCHAR(64) NOT NULL COMMENT 'Page name',
  page_path VARCHAR(255) NOT NULL COMMENT 'Page path',
  title VARCHAR(255) NULL COMMENT 'SEO title',
  keywords VARCHAR(255) NULL COMMENT 'SEO keywords',
  description VARCHAR(512) NULL COMMENT 'SEO description',
  og_title VARCHAR(255) NULL COMMENT 'Open Graph title',
  og_description VARCHAR(512) NULL COMMENT 'Open Graph description',
  og_image VARCHAR(512) NULL COMMENT 'Open Graph image',
  twitter_title VARCHAR(255) NULL COMMENT 'Twitter title',
  twitter_description VARCHAR(512) NULL COMMENT 'Twitter description',
  twitter_image VARCHAR(512) NULL COMMENT 'Twitter image',
  status TINYINT NOT NULL DEFAULT 1 COMMENT 'Status (0-inactive, 1-active)',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
  deleted TINYINT NOT NULL DEFAULT 0 COMMENT 'Deleted flag (0-not deleted, 1-deleted)',
  PRIMARY KEY (id),
  UNIQUE KEY uk_page_path (page_path),
  KEY idx_status (status),
  KEY idx_page_name (page_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Blog SEO meta table';

-- =============================================
-- 6. Blog Analytics (blog_statistics)
-- 统计系统表
-- =============================================

-- Drop tables if exists (for development only)
-- 删除表（仅用于开发环境）
DROP TABLE IF EXISTS blog_visit_stat;
DROP TABLE IF EXISTS blog_article_stat;
DROP TABLE IF EXISTS blog_user_stat;

-- Create blog_visit_stat table
-- 创建访问统计表
CREATE TABLE blog_visit_stat (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Visit stat ID',
  visit_date DATE NOT NULL COMMENT 'Visit date',
  page_path VARCHAR(255) NOT NULL COMMENT 'Page path',
  visit_count BIGINT NOT NULL DEFAULT 0 COMMENT 'Visit count',
  unique_visitor_count BIGINT NOT NULL DEFAULT 0 COMMENT 'Unique visitor count',
  ip_count BIGINT NOT NULL DEFAULT 0 COMMENT 'IP count',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
  PRIMARY KEY (id),
  UNIQUE KEY uk_date_path (visit_date, page_path),
  KEY idx_visit_date (visit_date),
  KEY idx_page_path (page_path)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Blog visit statistics table';

-- Create blog_article_stat table
-- 创建文章统计表
CREATE TABLE blog_article_stat (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Article stat ID',
  article_id BIGINT NOT NULL COMMENT 'Article ID',
  stat_date DATE NOT NULL COMMENT 'Stat date',
  view_count BIGINT NOT NULL DEFAULT 0 COMMENT 'View count',
  like_count BIGINT NOT NULL DEFAULT 0 COMMENT 'Like count',
  comment_count BIGINT NOT NULL DEFAULT 0 COMMENT 'Comment count',
  favorite_count BIGINT NOT NULL DEFAULT 0 COMMENT 'Favorite count',
  share_count BIGINT NOT NULL DEFAULT 0 COMMENT 'Share count',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
  PRIMARY KEY (id),
  UNIQUE KEY uk_article_date (article_id, stat_date),
  KEY idx_article_id (article_id),
  KEY idx_stat_date (stat_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Blog article statistics table';

-- Create blog_user_stat table
-- 创建用户统计表
CREATE TABLE blog_user_stat (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'User stat ID',
  user_id BIGINT NOT NULL COMMENT 'User ID',
  stat_date DATE NOT NULL COMMENT 'Stat date',
  login_count BIGINT NOT NULL DEFAULT 0 COMMENT 'Login count',
  publish_article_count BIGINT NOT NULL DEFAULT 0 COMMENT 'Published article count',
  like_count BIGINT NOT NULL DEFAULT 0 COMMENT 'Like count',
  comment_count BIGINT NOT NULL DEFAULT 0 COMMENT 'Comment count',
  favorite_count BIGINT NOT NULL DEFAULT 0 COMMENT 'Favorite count',
  share_count BIGINT NOT NULL DEFAULT 0 COMMENT 'Share count',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
  PRIMARY KEY (id),
  UNIQUE KEY uk_user_date (user_id, stat_date),
  KEY idx_user_id (user_id),
  KEY idx_stat_date (stat_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Blog user statistics table';

-- =============================================
-- 7. Demo table (keep for compatibility)
-- 保留 demo 表用于兼容性
-- =============================================

-- Drop table if exists (for development only)
-- 删除表（仅用于开发环境）
DROP TABLE IF EXISTS demo;

-- Create demo table
-- 创建 demo 表
CREATE TABLE demo (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Demo ID',
  name VARCHAR(64) NOT NULL COMMENT 'Demo name',
  description VARCHAR(512) NULL COMMENT 'Demo description',
  status TINYINT NOT NULL DEFAULT 1 COMMENT 'Demo status (0-inactive, 1-active)',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
  deleted TINYINT NOT NULL DEFAULT 0 COMMENT 'Deleted flag (0-not deleted, 1-deleted)',
  PRIMARY KEY (id),
  UNIQUE KEY uk_name (name),
  KEY idx_status (status),
  KEY idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Demo table';

-- =============================================
-- Initialize Complete System Data
-- 初始化完整系统数据
-- =============================================

-- 1. Insert initial roles
-- 插入初始角色
INSERT INTO sys_role (name, code, description, sort_order, status) VALUES
  ('Super Admin', 'SUPER_ADMIN', 'System super administrator with all permissions', 1, 1),
  ('Admin', 'ADMIN', 'System administrator with management permissions', 2, 1),
  ('Author', 'AUTHOR', 'Content creator with article management permissions', 3, 1),
  ('Visitor', 'VISITOR', 'Regular user with read and comment permissions', 4, 1);

-- 2. Insert initial permissions (simplified)
-- 插入初始权限（简化版）
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
  ('Delete Comment', 'comment:delete', 2, 8, NULL, NULL, NULL, 3, 1);

-- 3. Insert role-permission relations
-- 插入角色权限关联
-- Super Admin has all permissions
INSERT INTO sys_role_permission (role_id, permission_id)
SELECT 1, id FROM sys_permission;

-- Admin has most permissions except super admin specific ones
INSERT INTO sys_role_permission (role_id, permission_id)
SELECT 2, id FROM sys_permission WHERE code NOT IN ('permission:manage');

-- Author has article and comment permissions
INSERT INTO sys_role_permission (role_id, permission_id)
SELECT 3, id FROM sys_permission WHERE code IN ('article:manage', 'article:create', 'article:update', 'article:delete', 'comment:manage');

-- Visitor has read permissions
INSERT INTO sys_role_permission (role_id, permission_id)
SELECT 4, id FROM sys_permission WHERE code IN ('dashboard', 'analytics:view');

-- 4. Insert admin user (password: admin123)
-- 插入管理员用户（密码：admin123）
INSERT INTO sys_user (username, password, nickname, email, avatar, bio, status, role_type) VALUES
  ('admin', '$2a$10$eWdKjVfJxVvZ7xY9yX8wQOeR5tY6uI7oP8aS9dF0gH1jK2lZ3xC4vB5nM6',
   'Admin', 'admin@example.com', NULL, 'System administrator', 1, 1);

-- 5. Insert test user (password: user123)
-- 插入测试用户（密码：user123）
INSERT INTO sys_user (username, password, nickname, email, avatar, bio, status, role_type) VALUES
  ('testuser', '$2a$10$eWdKjVfJxVvZ7xY9yX8wQOeR5tY6uI7oP8aS9dF0gH1jK2lZ3xC4vB5nM6',
   'Test User', 'test@example.com', NULL, 'Regular user', 1, 4);

-- 6. Insert test categories
-- 插入测试分类
INSERT INTO blog_category (name, description, parent_id, sort_order, status) VALUES
  ('Technology', 'Technology and programming', 0, 1, 1),
  ('Life', 'Life and experiences', 0, 2, 1),
  ('Travel', 'Travel and adventures', 0, 3, 1),
  ('Food', 'Food and cooking', 0, 4, 1),
  ('Science', 'Science and research', 0, 5, 1);

-- 7. Insert test tags
-- 插入测试标签
INSERT INTO blog_tag (name, description, color, sort_order, status) VALUES
  ('Java', 'Java programming language', '#5382a1', 1, 1),
  ('Python', 'Python programming language', '#3776ab', 2, 1),
  ('Web Development', 'Web development technologies', '#42b883', 3, 1),
  ('AI', 'Artificial intelligence', '#ff6b6b', 4, 1),
  ('Database', 'Database technologies', '#ffa726', 5, 1);

-- 8. Insert initial configs
-- 插入初始配置
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
  ('site.maintenance', 'false', 'boolean', 'Maintenance mode', 10, 1);

-- 9. Insert SEO metas
-- 插入SEO元数据
INSERT INTO blog_seo_meta (page_name, page_path, title, keywords, description) VALUES
  ('Home', '/', 'My Blog - Welcome', 'blog, programming, technology', 'A modern blog system featuring technology and programming content'),
  ('Article List', '/articles', 'Articles - My Blog', 'articles, blog posts, content', 'Browse all articles on my blog'),
  ('Article Detail', '/article/{id}', '{title} - My Blog', '', '{summary}'),
  ('About', '/about', 'About - My Blog', 'about, contact', 'Learn more about me and this blog'),
  ('Admin Dashboard', '/admin', 'Admin Dashboard - My Blog', '', 'Admin management dashboard');

-- 10. Insert demo data
-- 插入 demo 数据
INSERT INTO demo (name, description, status) VALUES
  ('Demo 1', 'This is the first demo', 1),
  ('Demo 2', 'This is the second demo', 1),
  ('Demo 3', 'This is the third demo', 0);
