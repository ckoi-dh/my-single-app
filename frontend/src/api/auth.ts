import request from '../utils/request'
import type { LoginReq, LoginVO } from '../types'

/**
 * Authentication API
 */
export const authApi = {
  /**
   * User login
   */
  login(data: LoginReq): Promise<LoginVO> {
    return request.post('/api/v1/auth/login', data)
  },

  /**
   * User logout
   */
  logout(): Promise<void> {
    return request.post('/api/v1/auth/logout')
  },

  /**
   * Refresh token
   */
  refreshToken(): Promise<string> {
    return request.post('/api/v1/auth/refresh')
  }
}
