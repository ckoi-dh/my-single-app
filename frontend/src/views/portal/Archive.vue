<script setup lang="ts">
import { ref, onMounted } from 'vue'

interface ArchiveArticle {
  id: number
  title: string
  createTime: string
}

interface ArchiveMonth {
  month: string
  articles: ArchiveArticle[]
}

interface ArchiveYear {
  year: string
  months: ArchiveMonth[]
}

const loading = ref(false)
const archiveData = ref<ArchiveYear[]>([])

onMounted(() => {
  loading.value = true
  setTimeout(() => {
    archiveData.value = [
      {
        year: '2024',
        months: [
          {
            month: '01',
            articles: [
              { id: 1, title: '2024年1月文章1', createTime: '2024-01-15' },
              { id: 2, title: '2024年1月文章2', createTime: '2024-01-10' }
            ]
          }
        ]
      },
      {
        year: '2023',
        months: [
          {
            month: '12',
            articles: [
              { id: 3, title: '2023年12月文章1', createTime: '2023-12-25' },
              { id: 4, title: '2023年12月文章2', createTime: '2023-12-20' },
              { id: 5, title: '2023年12月文章3', createTime: '2023-12-15' }
            ]
          },
          {
            month: '11',
            articles: [{ id: 6, title: '2023年11月文章1', createTime: '2023-11-30' }]
          }
        ]
      }
    ]
    loading.value = false
  }, 1000)
})
</script>

<template>
  <div class="archive">
    <!-- Header -->
    <header class="bg-white shadow-sm sticky top-0 z-50">
      <div class="max-w-7xl mx-auto px-4">
        <div class="flex items-center justify-between h-16">
          <router-link to="/" class="text-xl font-bold text-blue-600">我的博客</router-link>
          <nav class="flex space-x-6">
            <router-link to="/" class="text-gray-700 hover:text-blue-600">首页</router-link>
            <router-link to="/search" class="text-gray-700 hover:text-blue-600">搜索</router-link>
          </nav>
        </div>
      </div>
    </header>

    <!-- Main Content -->
    <div class="max-w-4xl mx-auto px-4 py-6">
      <div class="bg-white rounded-lg shadow-sm p-6">
        <h1 class="text-3xl font-bold mb-8">时间线归档</h1>

        <el-skeleton :loading="loading" animated>
          <div class="space-y-8">
            <div v-for="yearData in archiveData" :key="yearData.year" class="year-section">
              <h2 class="text-2xl font-bold text-gray-700 mb-4 flex items-center">
                <el-icon class="mr-2"><Date /></el-icon>
                {{ yearData.year }} 年
              </h2>
              <div class="ml-6 space-y-6">
                <div
                  v-for="monthData in yearData.months"
                  :key="monthData.month"
                  class="month-section"
                >
                  <h3 class="text-lg font-bold text-gray-600 mb-3">{{ monthData.month }} 月</h3>
                  <ul class="ml-6 space-y-2">
                    <li v-for="article in monthData.articles" :key="article.id">
                      <router-link
                        :to="`/article/${article.id}`"
                        class="flex items-center text-gray-700 hover:text-blue-600"
                      >
                        <span class="text-gray-400 w-24">{{ article.createTime }}</span>
                        <span>{{ article.title }}</span>
                      </router-link>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </el-skeleton>
      </div>
    </div>

    <!-- Footer -->
    <footer class="bg-gray-800 text-white py-8">
      <div class="max-w-7xl mx-auto px-4 text-center">
        <p class="text-gray-400">© 2024 我的博客. All rights reserved.</p>
      </div>
    </footer>
  </div>
</template>

<style scoped></style>
