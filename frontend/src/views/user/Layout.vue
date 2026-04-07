<script setup lang="ts">
import { computed } from 'vue'
import { useRoute } from 'vue-router'
import { useAuthStore } from '../../stores/useAuthStore'
import { User, Document, Star, ChatDotRound, Setting } from '@element-plus/icons-vue'

const route = useRoute()
const authStore = useAuthStore()

const currentPath = computed(() => route.path)

const menuItems = [
  { index: '/user/profile', icon: User, title: '个人资料' },
  { index: '/user/articles', icon: Document, title: '我的文章' },
  { index: '/user/favorites', icon: Star, title: '我的收藏' },
  { index: '/user/comments', icon: ChatDotRound, title: '我的评论' },
  { index: '/user/settings', icon: Setting, title: '个人设置' }
]
</script>

<template>
  <div class="user-layout min-h-screen bg-gray-100">
    <!-- Header -->
    <header class="bg-white shadow-sm">
      <div class="max-w-6xl mx-auto px-4">
        <div class="flex items-center justify-between h-16">
          <router-link to="/" class="text-xl font-bold text-blue-600">我的博客</router-link>
          <div class="flex items-center gap-4">
            <router-link to="/" class="text-gray-600 hover:text-blue-600">返回首页</router-link>
            <router-link to="/admin" class="text-gray-600 hover:text-blue-600"
              >管理后台</router-link
            >
          </div>
        </div>
      </div>
    </header>

    <div class="max-w-6xl mx-auto px-4 py-6">
      <div class="flex gap-6">
        <!-- Sidebar -->
        <aside class="w-64">
          <div class="bg-white rounded-lg shadow-sm">
            <!-- User Info -->
            <div class="p-6 text-center border-b">
              <el-avatar :size="80" :src="authStore.currentUser?.avatar">
                <el-icon :size="40"><User /></el-icon>
              </el-avatar>
              <h3 class="mt-4 text-lg font-bold">
                {{ authStore.currentUser?.nickname || authStore.currentUser?.username }}
              </h3>
              <p class="text-gray-500 text-sm mt-1">{{ authStore.currentUser?.email }}</p>
            </div>
            <!-- Menu -->
            <el-menu :default-active="currentPath" router class="border-none">
              <el-menu-item v-for="item in menuItems" :key="item.index" :index="item.index">
                <el-icon><component :is="item.icon" /></el-icon>
                <template #title>{{ item.title }}</template>
              </el-menu-item>
            </el-menu>
          </div>
        </aside>

        <!-- Main Content -->
        <main class="flex-1">
          <router-view />
        </main>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
