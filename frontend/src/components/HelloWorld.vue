<script setup lang="ts">
import { ref } from 'vue'
import viteLogo from '../assets/vite.svg'
import heroImg from '../assets/hero.png'
import vueLogo from '../assets/vue.svg'
import DemoElementPlusWithTailwind from './DemoElementPlusWithTailwind.vue'
import DemoH5Tailwind from './DemoH5Tailwind.vue'

const mode = ref<'demo' | 'admin' | 'h5'>('demo')
</script>

<template>
  <div class="min-h-screen bg-gray-50">
    <!-- Header -->
    <header class="bg-white shadow-sm">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-4">
        <div class="flex items-center justify-between">
          <div class="flex items-center gap-3">
            <img :src="vueLogo" alt="Vue Logo" class="h-8" />
            <h1 class="text-xl font-bold text-gray-800">Vue 3 + Element Plus + Tailwind</h1>
          </div>
          <div class="flex gap-2">
            <button @click="mode = 'demo'" :class="[mode === 'demo' ? 'bg-blue-500 text-white' : 'bg-gray-200 text-gray-700', 'px-3 py-1 rounded-md text-sm']">
              Demo
            </button>
            <button @click="mode = 'admin'" :class="[mode === 'admin' ? 'bg-blue-500 text-white' : 'bg-gray-200 text-gray-700', 'px-3 py-1 rounded-md text-sm']">
              管理后台
            </button>
            <button @click="mode = 'h5'" :class="[mode === 'h5' ? 'bg-blue-500 text-white' : 'bg-gray-200 text-gray-700', 'px-3 py-1 rounded-md text-sm']">
              H5 页面
            </button>
          </div>
        </div>
      </div>
    </header>

    <!-- Main Content -->
    <main>
      <!-- Demo Mode -->
      <div v-if="mode === 'demo'" class="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div class="text-center mb-8">
          <div class="hero relative inline-block">
            <img :src="heroImg" class="mx-auto w-40" alt="" />
            <img :src="vueLogo" class="absolute left-1/2 top-1/4 -translate-x-1/2 w-12 h-7" alt="Vue logo" />
            <img :src="viteLogo" class="absolute left-1/2 top-3/4 -translate-x-1/2 w-10 h-6" alt="Vite logo" />
          </div>
          <h2 class="text-3xl font-bold text-gray-800 mt-6">混合使用架构演示</h2>
          <p class="text-gray-600 mt-3">点击上方按钮查看不同场景的实现</p>
        </div>

        <div class="grid md:grid-cols-2 gap-8 mt-12">
          <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
            <h3 class="text-lg font-semibold text-gray-800 mb-3">管理后台模式</h3>
            <p class="text-gray-600 mb-4">使用 Element Plus 组件库快速搭建管理后台</p>
            <ul class="space-y-2 text-sm text-gray-700">
              <li>• Element Plus 表格</li>
              <li>• Element Plus 表单</li>
              <li>• Tailwind 工具类调整</li>
            </ul>
          </div>
          <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
            <h3 class="text-lg font-semibold text-gray-800 mb-3">H5 移动端模式</h3>
            <p class="text-gray-600 mb-4">使用 Tailwind CSS 构建轻量响应式页面</p>
            <ul class="space-y-2 text-sm text-gray-700">
              <li>• Tailwind 响应式布局</li>
              <li>• 移动端优化</li>
              <li>• 纯 CSS 实现</li>
            </ul>
          </div>
        </div>
      </div>

      <!-- Admin Mode -->
      <div v-if="mode === 'admin'">
        <DemoElementPlusWithTailwind />
      </div>

      <!-- H5 Mode -->
      <div v-if="mode === 'h5'">
        <DemoH5Tailwind />
      </div>
    </main>
  </div>
</template>
