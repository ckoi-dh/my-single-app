import request from '../utils/request'
import type { UserVO, PageResult } from '../types'

/**
 * User Management API
 */
export const userApi = {
  /**
   * Get current user info
   */
  getCurrentUser(): Promise<UserVO> {
    return request.get('/api/v1/user/info')
  },

  /**
   * Get user by id
   */
  getUser(id: number): Promise<UserVO> {
    return request.get(`/api/v1/user/${id}`)
  },

  /**
   * Update user info
   */
  updateUser(data: Partial<UserVO>): Promise<UserVO> {
    return request.put('/api/v1/user/info', data)
  },

  /**
   * Upload avatar
   */
  uploadAvatar(formData: FormData): Promise<string> {
    return request.post('/api/v1/user/avatar', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
  },

  /**
   * Get user list
   */
  getUserList(params: {
    page?: number
    size?: number
    keyword?: string
  }): Promise<PageResult<UserVO>> {
    return request.get('/api/v1/user/list', { params })
  }
}
