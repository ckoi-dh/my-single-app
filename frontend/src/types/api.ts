/**
 * Type definitions generated from OpenAPI schema
 * These types will be replaced by openapi-typescript output
 */

// Base response structure
export interface ApiResponse<T = any> {
  code: number
  message: string
  data: T
}

// User related types
export interface UserVO {
  id: number
  username: string
  nickname: string
  email: string
  avatar: string
}

export interface LoginReq {
  username: string
  password: string
}

export interface LoginVO {
  token: string
  user: UserVO
}

// Article related types
export interface ArticleVO {
  id: number
  title: string
  summary: string
  content: string
  coverImage: string
  authorId: number
  authorName: string
  categoryId: number
  categoryName: string
  status: number
  viewCount: number
  likeCount: number
  commentCount: number
  createTime: string
  updateTime: string
  tags: TagVO[]
}

export interface ArticleQueryReq {
  page: number
  size: number
  keyword?: string
  categoryId?: number
  tagId?: number
  status?: number
}

// Category related types
export interface CategoryVO {
  id: number
  name: string
  slug: string
  description: string
  sort: number
  articleCount: number
  createTime: string
}

// Tag related types
export interface TagVO {
  id: number
  name: string
  slug: string
  articleCount: number
  createTime: string
}

// Comment related types
export interface CommentVO {
  id: number
  articleId: number
  articleTitle: string
  userId: number
  username: string
  avatar: string
  content: string
  parentId: number
  replyTo: string
  createTime: string
}

export interface CommentQueryReq {
  page: number
  size: number
  articleId?: number
  userId?: number
}

// Pagination result
export interface PageResult<T> {
  list: T[]
  total: number
  page: number
  size: number
}
