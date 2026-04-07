<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'

const loading = ref(false)
const articles = ref([])
const total = ref(0)

const searchForm = reactive({
  keyword: ''
})

const handleSearch = () => {
  if (!searchForm.keyword.trim()) return
  fetchData()
}

const fetchData = () => {
  loading.value = true
  setTimeout(() => {
    total.value = 5
    loading.value = false
  }, 1000)
}

onMounted(() => {
  // Check if there's a search keyword in URL
})
</script>

<template>
  <div class="search">
    <!-- Header -->
    <header class="bg-white shadow-sm sticky top-0 z-50">
      <div class="max-w-7xl mx-auto px-4">
        <div class="flex items-center justify-between h-16">
          <router-link to="/" class="text-xl font-bold text-blue-600">我的博客</router-link>
          <nav class="flex space-x-6">
            <router-link to="/" class="text-gray-700 hover:text-blue-600">首页</router-link>
            <router-link to="/archive" class="text-gray-700 hover:text-blue-600">归档</router-link>
          </nav>
        </div>
      </div>
    </header>

    <!-- Main Content -->
    <div class="max-w-7xl mx-auto px-4 py-6">
      <!-- Search Box -->
      <div class="bg-white rounded-lg shadow-sm p-6 mb-6">
        <div class="max-w-2xl mx-auto">
          <el-input
            v-model="searchForm.keyword"
            size="large"
            placeholder="搜索文章..."
            clearable
            @keyup.enter="handleSearch"
          >
            <template #append>
              <el-button @click="handleSearch">搜索</el-button>
            </template>
          </el-input>
        </div>
      </div>

      <!-- Search Results -->
      <div class="bg-white rounded-lg shadow-sm p-6">
        <h2 class="text-xl font-bold mb-6" v-if="searchForm.keyword">
          搜索结果：{{ searchForm.keyword }} ({{ total }})
        </h2>

        <el-skeleton :loading="loading" animated>
          <div class="space-y-6">
            <div v-if="!loading && articles.length === 0" class="text-center py-12 text-gray-500">
              <el-empty description="暂无搜索结果" />
            </div>
            <div v-for="i in 5" :key="i" class="border-b pb-6">
              <router-link to="/article/1" class="block">
                <h3 class="text-xl font-bold text-gray-800 hover:text-blue-600 mb-2">
                  搜索结果文章示例 {{ i }}
                </h3>
              </router-link>
              <p class="text-gray-600 mb-4">这是文章摘要内容...</p>
              <div class="flex items-center text-sm text-gray-500">
                <span>作者</span>
                <span class="mx-2">•</span>
                <span>2024-01-01</span>
                <span class="mx-2">•</span>
                <span>浏览 100</span>
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
