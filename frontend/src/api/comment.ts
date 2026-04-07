import request from '../utils/request'
import type { CommentVO, CommentQueryReq, PageResult } from '../types'

/**
 * Comment Management API
 */
export const commentApi = {
  /**
   * Create comment
   */
  createComment(data: Partial<CommentVO>): Promise<CommentVO> {
    return request.post('/api/v1/comment', data)
  },

  /**
   * Update comment
   */
  updateComment(id: number, data: Partial<CommentVO>): Promise<CommentVO> {
    return request.put(`/api/v1/comment/${id}`, data)
  },

  /**
   * Delete comment
   */
  deleteComment(id: number): Promise<void> {
    return request.delete(`/api/v1/comment/${id}`)
  },

  /**
   * Get comment by id
   */
  getComment(id: number): Promise<CommentVO> {
    return request.get(`/api/v1/comment/${id}`)
  },

  /**
   * Get comment list
   */
  getCommentList(params: CommentQueryReq): Promise<PageResult<CommentVO>> {
    return request.get('/api/v1/comment/list', { params })
  },

  /**
   * Like comment
   */
  likeComment(id: number): Promise<number> {
    return request.post(`/api/v1/comment/${id}/like`)
  },

  /**
   * Get comment count
   */
  getCommentCount(articleId?: number): Promise<number> {
    return request.get('/api/v1/comment/count', { params: { articleId } })
  }
}
