import request from '../utils/request'
import type { TagVO, PageResult } from '../types'

/**
 * Tag Management API
 */
export const tagApi = {
  /**
   * Create tag
   */
  createTag(data: Partial<TagVO>): Promise<TagVO> {
    return request.post('/api/v1/tag', data)
  },

  /**
   * Update tag
   */
  updateTag(id: number, data: Partial<TagVO>): Promise<TagVO> {
    return request.put(`/api/v1/tag/${id}`, data)
  },

  /**
   * Delete tag
   */
  deleteTag(id: number): Promise<void> {
    return request.delete(`/api/v1/tag/${id}`)
  },

  /**
   * Get tag by id
   */
  getTag(id: number): Promise<TagVO> {
    return request.get(`/api/v1/tag/${id}`)
  },

  /**
   * Get tag list
   */
  getTagList(params: {
    page?: number
    size?: number
    keyword?: string
  }): Promise<PageResult<TagVO>> {
    return request.get('/api/v1/tag/list', { params })
  },

  /**
   * Get all tags
   */
  getAllTags(): Promise<TagVO[]> {
    return request.get('/api/v1/tag/all')
  }
}
