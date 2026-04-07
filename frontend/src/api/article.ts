import request from '../utils/request'
import type { ArticleVO, ArticleQueryReq, PageResult } from '../types'

/**
 * Article Management API
 */
export const articleApi = {
  /**
   * Create article
   */
  createArticle(data: Partial<ArticleVO>): Promise<ArticleVO> {
    return request.post('/api/v1/article', data)
  },

  /**
   * Update article
   */
  updateArticle(id: number, data: Partial<ArticleVO>): Promise<ArticleVO> {
    return request.put(`/api/v1/article/${id}`, data)
  },

  /**
   * Delete article
   */
  deleteArticle(id: number): Promise<void> {
    return request.delete(`/api/v1/article/${id}`)
  },

  /**
   * Get article by id
   */
  getArticle(id: number): Promise<ArticleVO> {
    return request.get(`/api/v1/article/${id}`)
  },

  /**
   * Get article list
   */
  getArticleList(params: ArticleQueryReq): Promise<PageResult<ArticleVO>> {
    return request.get('/api/v1/article/list', { params })
  },

  /**
   * Get published article list
   */
  getPublishedArticleList(params: ArticleQueryReq): Promise<PageResult<ArticleVO>> {
    return request.get('/api/v1/article/published/list', { params })
  },

  /**
   * Update article status
   */
  updateArticleStatus(id: number, status: number): Promise<ArticleVO> {
    return request.put(`/api/v1/article/${id}/status`, { status })
  },

  /**
   * Increase view count
   */
  increaseViewCount(id: number): Promise<ArticleVO> {
    return request.post(`/api/v1/article/${id}/view`)
  }
}
