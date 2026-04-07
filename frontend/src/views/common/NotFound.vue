<script setup lang="ts">
import { useRouter } from 'vue-router'

const router = useRouter()
</script>

<template>
  <div class="not-found flex flex-col items-center justify-center min-h-screen">
    <div class="text-9xl font-bold text-gray-200">404</div>
    <div class="text-2xl text-gray-600 mt-4">页面不存在</div>
    <div class="text-gray-400 mt-2">抱歉，您访问的页面不存在</div>
    <el-button type="primary" class="mt-8" @click="router.push('/')">返回首页</el-button>
  </div>
</template>

<style scoped></style>
