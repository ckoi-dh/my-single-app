-- =============================================
-- Database Schema
-- 数据库初始化脚本
-- =============================================
-- Version: 1.0.0
-- Created: 2026-03-18
-- Description: Initialize database and tables
-- =============================================

-- Create database if not exists
-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS app_ckoi DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE app_ckoi;

-- =============================================
-- demo table
-- demo 表
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

-- Insert test data
-- 插入测试数据
INSERT INTO demo (name, description, status) VALUES
  ('Demo 1', 'This is the first demo', 1),
  ('Demo 2', 'This is the second demo', 1),
  ('Demo 3', 'This is the third demo', 0);
