<script setup lang="ts">
import { ref, onMounted } from 'vue'

const loading = ref(false)
const carouselItems = ref([
  {
    id: 1,
    title: '欢迎来到我的博客',
    description: '分享技术，分享生活',
    image: 'https://picsum.photos/1200/400?random=1'
  },
  {
    id: 2,
    title: '技术文章精选',
    description: '深入探索前端技术',
    image: 'https://picsum.photos/1200/400?random=2'
  },
  {
    id: 3,
    title: '编程心得分享',
    description: '记录成长的点滴',
    image: 'https://picsum.photos/1200/400?random=3'
  }
])

onMounted(() => {
  loading.value = true
  setTimeout(() => {
    loading.value = false
  }, 1000)
})
</script>

<template>
  <div class="portal-home">
    <!-- Header -->
    <header class="bg-white shadow-sm sticky top-0 z-50">
      <div class="max-w-7xl mx-auto px-4">
        <div class="flex items-center justify-between h-16">
          <div class="flex items-center">
            <h1 class="text-xl font-bold text-blue-600">我的博客</h1>
          </div>
          <nav class="hidden md:flex space-x-8">
            <router-link to="/" class="text-gray-700 hover:text-blue-600">首页</router-link>
            <router-link to="/archive" class="text-gray-700 hover:text-blue-600">归档</router-link>
            <router-link to="/search" class="text-gray-700 hover:text-blue-600">搜索</router-link>
          </nav>
          <div class="flex items-center space-x-4">
            <router-link to="/login" class="text-gray-700 hover:text-blue-600">登录</router-link>
            <router-link
              to="/register"
              class="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700"
              >注册</router-link
            >
          </div>
        </div>
      </div>
    </header>

    <!-- Carousel -->
    <div class="max-w-7xl mx-auto px-4 py-6">
      <el-carousel height="400px" class="rounded-lg overflow-hidden">
        <el-carousel-item v-for="item in carouselItems" :key="item.id">
          <div class="relative h-full">
            <img :src="item.image" class="w-full h-full object-cover" />
            <div class="absolute inset-0 bg-black bg-opacity-40 flex items-center justify-center">
              <div class="text-center text-white">
                <h2 class="text-4xl font-bold mb-4">{{ item.title }}</h2>
                <p class="text-xl">{{ item.description }}</p>
              </div>
            </div>
          </div>
        </el-carousel-item>
      </el-carousel>
    </div>

    <!-- Main Content -->
    <div class="max-w-7xl mx-auto px-4 py-6">
      <div class="flex gap-6">
        <!-- Article List -->
        <div class="flex-1">
          <div class="bg-white rounded-lg shadow-sm p-6 mb-6">
            <h2 class="text-2xl font-bold mb-6">最新文章</h2>
            <div class="space-y-6">
              <el-skeleton :loading="loading" animated :rows="6">
                <div v-for="i in 5" :key="i" class="border-b pb-6">
                  <router-link to="/article/1" class="block">
                    <h3 class="text-xl font-bold text-gray-800 hover:text-blue-600 mb-2">
                      示例文章标题 {{ i }}
                    </h3>
                  </router-link>
                  <p class="text-gray-600 mb-4">这是文章摘要内容，简要介绍文章的主要内容...</p>
                  <div class="flex items-center text-sm text-gray-500">
                    <span>作者</span>
                    <span class="mx-2">•</span>
                    <span>2024-01-01</span>
                    <span class="mx-2">•</span>
                    <span>浏览 100</span>
                  </div>
                </div>
              </el-skeleton>
            </div>
            <div class="mt-6 flex justify-center">
              <el-pagination layout="prev, pager, next" :total="50" />
            </div>
          </div>
        </div>

        <!-- Sidebar -->
        <div class="w-80">
          <!-- Search -->
          <div class="bg-white rounded-lg shadow-sm p-4 mb-6">
            <el-input
              placeholder="搜索文章..."
              prefix-icon="Search"
              @keyup.enter="$router.push('/search')"
            />
          </div>

          <!-- Categories -->
          <div class="bg-white rounded-lg shadow-sm p-4 mb-6">
            <h3 class="text-lg font-bold mb-4">文章分类</h3>
            <el-skeleton :loading="loading" animated :rows="5">
              <div class="space-y-2">
                <div v-for="i in 5" :key="i" class="flex justify-between">
                  <router-link to="/category/1" class="text-gray-600 hover:text-blue-600"
                    >分类 {{ i }}</router-link
                  >
                  <span class="text-gray-400">{{ 10 * i }}</span>
                </div>
              </div>
            </el-skeleton>
          </div>

          <!-- Tags -->
          <div class="bg-white rounded-lg shadow-sm p-4 mb-6">
            <h3 class="text-lg font-bold mb-4">热门标签</h3>
            <el-skeleton :loading="loading" animated :rows="3">
              <div class="flex flex-wrap gap-2">
                <el-tag v-for="i in 10" :key="i" class="cursor-pointer">标签 {{ i }}</el-tag>
              </div>
            </el-skeleton>
          </div>
        </div>
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
