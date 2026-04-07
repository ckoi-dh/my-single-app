import { defineStore } from 'pinia'
import type { UserVO, LoginVO } from '../types'
import { authApi } from '../api/auth'
import {
  setToken,
  removeToken,
  setUserInfo,
  removeUserInfo,
  getToken,
  getUserInfo
} from '../utils/storage'

interface AuthState {
  token: string | null
  userInfo: UserVO | null
  isLogin: boolean
  loading: boolean
}

export const useAuthStore = defineStore('auth', {
  state: (): AuthState => ({
    token: getToken(),
    userInfo: getUserInfo(),
    isLogin: !!getToken(),
    loading: false
  }),

  getters: {
    isAuthenticated: (state) => state.isLogin && !!state.token,
    currentUser: (state) => state.userInfo
  },

  actions: {
    /**
     * User login
     */
    async login(data: { username: string; password: string }) {
      try {
        this.loading = true
        const result: LoginVO = await authApi.login(data)

        // Save token and user info
        this.token = result.token
        this.userInfo = result.user
        this.isLogin = true
        setToken(result.token)
        setUserInfo(result.user)

        return result
      } catch (error) {
        console.error('Login failed:', error)
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * User logout
     */
    async logout() {
      try {
        await authApi.logout()
      } catch (error) {
        console.error('Logout failed:', error)
      } finally {
        this.token = null
        this.userInfo = null
        this.isLogin = false
        removeToken()
        removeUserInfo()
      }
    },

    /**
     * Refresh token
     */
    async refreshToken() {
      try {
        const newToken = await authApi.refreshToken()
        this.token = newToken
        setToken(newToken)
        return newToken
      } catch (error) {
        console.error('Refresh token failed:', error)
        this.logout()
        throw error
      }
    },

    /**
     * Clear auth info
     */
    clearAuth() {
      this.token = null
      this.userInfo = null
      this.isLogin = false
      removeToken()
      removeUserInfo()
    },

    /**
     * Update user info
     */
    updateUserInfo(userInfo: Partial<UserVO>) {
      if (this.userInfo) {
        this.userInfo = { ...this.userInfo, ...userInfo }
        setUserInfo(this.userInfo)
      }
    }
  }
})
