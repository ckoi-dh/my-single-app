import request from '../utils/request'
import type { CategoryVO, PageResult } from '../types'

/**
 * Category Management API
 */
export const categoryApi = {
  /**
   * Create category
   */
  createCategory(data: Partial<CategoryVO>): Promise<CategoryVO> {
    return request.post('/api/v1/category', data)
  },

  /**
   * Update category
   */
  updateCategory(id: number, data: Partial<CategoryVO>): Promise<CategoryVO> {
    return request.put(`/api/v1/category/${id}`, data)
  },

  /**
   * Delete category
   */
  deleteCategory(id: number): Promise<void> {
    return request.delete(`/api/v1/category/${id}`)
  },

  /**
   * Get category by id
   */
  getCategory(id: number): Promise<CategoryVO> {
    return request.get(`/api/v1/category/${id}`)
  },

  /**
   * Get category list
   */
  getCategoryList(params: {
    page?: number
    size?: number
    keyword?: string
  }): Promise<PageResult<CategoryVO>> {
    return request.get('/api/v1/category/list', { params })
  },

  /**
   * Get all categories
   */
  getAllCategories(): Promise<CategoryVO[]> {
    return request.get('/api/v1/category/all')
  }
}
