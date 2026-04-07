<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const loading = ref(false)
const tag = ref<any>(null)
const total = ref(0)

onMounted(() => {
  loading.value = true
  setTimeout(() => {
    tag.value = {
      id: route.params.id,
      name: '示例标签',
      articleCount: 8
    }
    total.value = 8
    loading.value = false
  }, 1000)
})
</script>

<template>
  <div class="tag-detail">
    <!-- Header -->
    <header class="bg-white shadow-sm sticky top-0 z-50">
      <div class="max-w-7xl mx-auto px-4">
        <div class="flex items-center justify-between h-16">
          <router-link to="/" class="text-xl font-bold text-blue-600">我的博客</router-link>
          <nav class="flex space-x-6">
            <router-link to="/" class="text-gray-700 hover:text-blue-600">首页</router-link>
            <router-link to="/archive" class="text-gray-700 hover:text-blue-600">归档</router-link>
            <router-link to="/search" class="text-gray-700 hover:text-blue-600">搜索</router-link>
          </nav>
        </div>
      </div>
    </header>

    <!-- Main Content -->
    <div class="max-w-7xl mx-auto px-4 py-6">
      <el-skeleton :loading="loading" animated>
        <div v-if="tag">
          <!-- Tag Info -->
          <div class="bg-white rounded-lg shadow-sm p-6 mb-6">
            <div class="flex items-center gap-4">
              <el-tag size="large" type="primary">{{ tag.name }}</el-tag>
              <span class="text-gray-500">共 {{ tag.articleCount }} 篇文章</span>
            </div>
          </div>

          <!-- Article List -->
          <div class="bg-white rounded-lg shadow-sm p-6">
            <div class="space-y-6">
              <div v-for="i in 5" :key="i" class="border-b pb-6">
                <router-link to="/article/1" class="block">
                  <h3 class="text-xl font-bold text-gray-800 hover:text-blue-600 mb-2">
                    标签文章示例 {{ i }}
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

            <div class="mt-6 flex justify-center">
              <el-pagination layout="prev, pager, next" :total="total" />
            </div>
          </div>
        </div>
      </el-skeleton>
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
