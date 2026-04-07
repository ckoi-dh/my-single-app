import { defineStore } from 'pinia'
import type { ArticleVO, PageResult } from '../types'
import { articleApi } from '../api/article'

interface ArticleState {
  articles: ArticleVO[]
  currentArticle: ArticleVO | null
  total: number
  page: number
  size: number
  loading: boolean
}

export const useArticleStore = defineStore('article', {
  state: (): ArticleState => ({
    articles: [],
    currentArticle: null,
    total: 0,
    page: 1,
    size: 10,
    loading: false
  }),

  getters: {
    publishedArticles: (state) => state.articles.filter((article) => article.status === 1)
  },

  actions: {
    /**
     * Get article list
     */
    async getArticleList(params: {
      page?: number
      size?: number
      keyword?: string
      categoryId?: number
      tagId?: number
      status?: number
    }) {
      const queryParams = {
        page: params.page || 1,
        size: params.size || 10,
        keyword: params.keyword,
        categoryId: params.categoryId,
        tagId: params.tagId,
        status: params.status
      }
      try {
        this.loading = true
        const result: PageResult<ArticleVO> = await articleApi.getArticleList(queryParams)
        this.articles = result.list
        this.total = result.total
        this.page = params.page || 1
        this.size = params.size || 10
        return result
      } catch (error) {
        console.error('Get article list failed:', error)
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * Get article by id
     */
    async getArticle(id: number) {
      try {
        this.loading = true
        const article: ArticleVO = await articleApi.getArticle(id)
        this.currentArticle = article
        return article
      } catch (error) {
        console.error('Get article failed:', error)
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * Create article
     */
    async createArticle(data: Partial<ArticleVO>) {
      try {
        this.loading = true
        const result: ArticleVO = await articleApi.createArticle(data)
        this.articles.unshift(result)
        this.total += 1
        return result
      } catch (error) {
        console.error('Create article failed:', error)
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * Update article
     */
    async updateArticle(id: number, data: Partial<ArticleVO>) {
      try {
        this.loading = true
        const result: ArticleVO = await articleApi.updateArticle(id, data)
        const index = this.articles.findIndex((article) => article.id === id)
        if (index !== -1) {
          this.articles[index] = result
        }
        if (this.currentArticle?.id === id) {
          this.currentArticle = result
        }
        return result
      } catch (error) {
        console.error('Update article failed:', error)
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * Delete article
     */
    async deleteArticle(id: number) {
      try {
        this.loading = true
        await articleApi.deleteArticle(id)
        this.articles = this.articles.filter((article) => article.id !== id)
        this.total -= 1
      } catch (error) {
        console.error('Delete article failed:', error)
        throw error
      } finally {
        this.loading = false
      }
    },

    /**
     * Clear current article
     */
    clearCurrentArticle() {
      this.currentArticle = null
    }
  }
})
