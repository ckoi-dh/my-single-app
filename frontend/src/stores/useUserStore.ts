import { defineStore } from 'pinia'
import type { UserVO, PageResult } from '../types'
import { userApi } from '../api/user'

interface UserState {
  users: UserVO[]
  currentUser: UserVO | null
  total: number
  page: number
  size: number
  loading: boolean
}

export const useUserStore = defineStore('user', {
  state: (): UserState => ({
    users: [],
    currentUser: null,
    total: 0,
    page: 1,
    size: 10,
    loading: false
  }),

  actions: {
    /**
     * Get current user info
     */
    async getCurrentUser() {
      try {
        this.loading = true
        const user: UserVO = await userApi.getCurrentUser()
        this.currentUser = user
        return user
      } catch (error) {
        console.error('Get current user failed:', error)
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * Get user list
     */
    async getUserList(params: { page?: number; size?: number; keyword?: string }) {
      try {
        this.loading = true
        const result: PageResult<UserVO> = await userApi.getUserList(params)
        this.users = result.list
        this.total = result.total
        this.page = params.page || 1
        this.size = params.size || 10
        return result
      } catch (error) {
        console.error('Get user list failed:', error)
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * Get user by id
     */
    async getUser(id: number) {
      try {
        this.loading = true
        const user: UserVO = await userApi.getUser(id)
        return user
      } catch (error) {
        console.error('Get user failed:', error)
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * Update user
     */
    async updateUser(data: Partial<UserVO>) {
      try {
        this.loading = true
        const updatedUser: UserVO = await userApi.updateUser(data)
        if (this.currentUser?.id === updatedUser.id) {
          this.currentUser = updatedUser
        }
        return updatedUser
      } catch (error) {
        console.error('Update user failed:', error)
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * Upload avatar
     */
    async uploadAvatar(formData: FormData) {
      try {
        this.loading = true
        const avatarUrl = await userApi.uploadAvatar(formData)
        if (this.currentUser) {
          this.currentUser.avatar = avatarUrl
        }
        return avatarUrl
      } catch (error) {
        console.error('Upload avatar failed:', error)
        throw error
      } finally {
        this.loading = false
      }
    }
  }
})
