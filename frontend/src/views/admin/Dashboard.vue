<script setup lang="ts">
import { ref, onMounted } from 'vue'

interface StatCard {
  title: string
  value: number
  icon: string
  color: string
  change: string
  changeType: 'increase' | 'decrease'
}

const loading = ref(true)
const stats = ref<StatCard[]>([
  {
    title: '文章总数',
    value: 128,
    icon: 'Document',
    color: '#409eff',
    change: '+12%',
    changeType: 'increase'
  },
  {
    title: '用户总数',
    value: 1024,
    icon: 'User',
    color: '#67c23a',
    change: '+8%',
    changeType: 'increase'
  },
  {
    title: '评论总数',
    value: 2048,
    icon: 'ChatDotRound',
    color: '#e6a23c',
    change: '+15%',
    changeType: 'increase'
  },
  {
    title: '访问总量',
    value: 50000,
    icon: 'View',
    color: '#f56c6c',
    change: '+5%',
    changeType: 'increase'
  }
])

onMounted(() => {
  // Simulate loading data
  setTimeout(() => {
    loading.value = false
  }, 1000)
})
</script>

<template>
  <div class="dashboard">
    <h2 class="text-2xl font-bold mb-6">欢迎回来</h2>

    <el-row :gutter="20" class="mb-6">
      <el-col :span="6" v-for="stat in stats" :key="stat.title">
        <el-card class="stat-card">
          <div class="flex items-center">
            <div
              class="stat-icon flex items-center justify-center w-16 h-16 rounded-lg mr-4"
              :style="{ backgroundColor: stat.color + '20' }"
            >
              <el-icon :size="32" :color="stat.color">
                <component :is="stat.icon" />
              </el-icon>
            </div>
            <div class="stat-content">
              <div class="text-gray-500 text-sm">{{ stat.title }}</div>
              <div class="text-2xl font-bold">{{ stat.value.toLocaleString() }}</div>
              <div
                class="text-sm"
                :class="stat.changeType === 'increase' ? 'text-green-500' : 'text-red-500'"
              >
                {{ stat.change }}
              </div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20">
      <el-col :span="16">
        <el-card>
          <template #header>
            <div class="flex items-center justify-between">
              <span class="font-bold">最近文章</span>
            </div>
          </template>
          <el-skeleton :loading="loading" animated :rows="5">
            <el-table :data="[]" style="width: 100%">
              <el-table-column prop="title" label="标题" />
              <el-table-column prop="author" label="作者" width="120" />
              <el-table-column prop="views" label="浏览量" width="100" />
              <el-table-column prop="createTime" label="创建时间" width="180" />
            </el-table>
          </el-skeleton>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card>
          <template #header>
            <div class="flex items-center justify-between">
              <span class="font-bold">快捷操作</span>
            </div>
          </template>
          <el-skeleton :loading="loading" animated :rows="4">
            <div class="space-y-3">
              <el-button
                type="primary"
                class="w-full"
                @click="$router.push('/admin/articles/create')"
              >
                <el-icon class="mr-2"><Plus /></el-icon>
                新建文章
              </el-button>
              <el-button class="w-full" @click="$router.push('/admin/categories')">
                <el-icon class="mr-2"><Menu /></el-icon>
                分类管理
              </el-button>
              <el-button class="w-full" @click="$router.push('/admin/tags')">
                <el-icon class="mr-2"><Tickets /></el-icon>
                标签管理
              </el-button>
              <el-button class="w-full" @click="$router.push('/admin/comments')">
                <el-icon class="mr-2"><ChatDotRound /></el-icon>
                评论审核
              </el-button>
            </div>
          </el-skeleton>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped></style>
