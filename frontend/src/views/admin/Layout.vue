<script setup lang="ts">
import { computed, ref } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useAuthStore } from '../../stores/useAuthStore'
import {
  Document,
  Menu as MenuIcon,
  User,
  Tickets,
  ChatDotRound,
  UserFilled,
  Tools,
  Monitor,
  ArrowDown
} from '@element-plus/icons-vue'

const router = useRouter()
const route = useRoute()
const authStore = useAuthStore()

const isCollapse = ref(false)
const currentPath = computed(() => route.path)

const handleLogout = () => {
  authStore.logout()
  router.push('/login')
}

const menuItems = [
  { index: '/admin/dashboard', icon: Monitor, title: '仪表板' },
  { index: '/admin/articles', icon: Document, title: '文章管理' },
  { index: '/admin/categories', icon: MenuIcon, title: '分类管理' },
  { index: '/admin/tags', icon: Tickets, title: '标签管理' },
  { index: '/admin/comments', icon: ChatDotRound, title: '评论管理' },
  { index: '/admin/users', icon: UserFilled, title: '用户管理' },
  { index: '/admin/config', icon: Tools, title: '系统配置' }
]
</script>

<template>
  <el-container class="admin-layout h-screen">
    <el-aside :width="isCollapse ? '64px' : '200px'" class="transition-all">
      <div class="logo h-16 flex items-center justify-center bg-blue-600 text-white">
        <span v-if="!isCollapse" class="text-lg font-bold">博客管理</span>
        <span v-else class="text-lg font-bold">博</span>
      </div>
      <el-menu
        :default-active="currentPath"
        :collapse="isCollapse"
        :collapse-transition="false"
        router
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
      >
        <el-menu-item v-for="item in menuItems" :key="item.index" :index="item.index">
          <el-icon><component :is="item.icon" /></el-icon>
          <template #title>{{ item.title }}</template>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header class="flex items-center justify-between bg-white shadow-sm px-4">
        <el-icon class="cursor-pointer text-xl" @click="isCollapse = !isCollapse">
          <Fold v-if="!isCollapse" />
          <Expand v-else />
        </el-icon>
        <div class="flex items-center space-x-4">
          <el-dropdown>
            <span class="flex items-center cursor-pointer">
              <el-avatar :size="32" :src="authStore.currentUser?.avatar">
                <el-icon><User /></el-icon>
              </el-avatar>
              <span class="ml-2">{{
                authStore.currentUser?.nickname || authStore.currentUser?.username
              }}</span>
              <el-icon class="ml-1"><ArrowDown /></el-icon>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="router.push('/user/profile')">
                  <el-icon class="mr-2"><User /></el-icon>
                  个人中心
                </el-dropdown-item>
                <el-dropdown-item divided @click="handleLogout">
                  <el-icon class="mr-2"><SwitchButton /></el-icon>
                  退出登录
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>

      <el-main class="bg-gray-100 overflow-auto">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<style scoped></style>
