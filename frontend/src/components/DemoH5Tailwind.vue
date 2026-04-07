<script setup lang="ts">
import { ref } from 'vue'

const products = ref([
  {
    id: 1,
    name: 'iPhone 15 Pro',
    price: '¥7,999',
    image: 'https://images.unsplash.com/photo-1695048133089-db8c7e12e189?w=400&h=300&fit=crop',
    category: '手机'
  },
  {
    id: 2,
    name: 'MacBook Air M3',
    price: '¥8,999',
    image: 'https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=400&h=300&fit=crop',
    category: '笔记本'
  },
  {
    id: 3,
    name: 'Apple Watch Series 9',
    price: '¥2,999',
    image: 'https://images.unsplash.com/photo-1542744173-8e7e53415bb0?w=400&h=300&fit=crop',
    category: '手表'
  },
  {
    id: 4,
    name: 'AirPods Pro 2',
    price: '¥1,899',
    image: 'https://images.unsplash.com/photo-1622396481312-9e77a85e4246?w=400&h=300&fit=crop',
    category: '耳机'
  }
])

const cartCount = ref(0)

const addToCart = (_product: any) => {
  cartCount.value++
  // 可以在这里添加动画反馈
}
</script>

<template>
  <div class="bg-gray-50 min-h-screen">
    <!-- Header -->
    <header class="bg-white shadow-sm sticky top-0 z-10">
      <div class="max-w-2xl mx-auto px-4 py-4">
        <div class="flex items-center justify-between">
          <h1 class="text-xl font-bold text-gray-800">精选商品</h1>
          <div class="relative">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              class="h-6 w-6 text-gray-600"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="M16 11V7a4 4 0 00-8 0v4M5 9h14l1 12H4L5 9z"
              />
            </svg>
            <span
              v-if="cartCount > 0"
              class="absolute -top-2 -right-2 bg-red-500 text-white text-xs font-bold rounded-full h-5 w-5 flex items-center justify-center"
            >
              {{ cartCount }}
            </span>
          </div>
        </div>
      </div>
    </header>

    <!-- Search Bar -->
    <div class="bg-white border-b border-gray-200">
      <div class="max-w-2xl mx-auto px-4 py-3">
        <div class="relative">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="h-5 w-5 absolute left-3 top-1/2 -translate-y-1/2 text-gray-400"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
            />
          </svg>
          <input
            type="text"
            placeholder="搜索商品..."
            class="w-full pl-10 pr-4 py-2 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
          />
        </div>
      </div>
    </div>

    <!-- Product Grid -->
    <main class="max-w-2xl mx-auto px-4 py-6">
      <div class="grid grid-cols-2 gap-4 mb-8">
        <button class="bg-blue-500 text-white px-4 py-2 rounded-lg font-medium">全部商品</button>
        <button
          class="bg-white text-gray-600 border border-gray-200 px-4 py-2 rounded-lg font-medium"
        >
          手机
        </button>
        <button
          class="bg-white text-gray-600 border border-gray-200 px-4 py-2 rounded-lg font-medium"
        >
          笔记本
        </button>
        <button
          class="bg-white text-gray-600 border border-gray-200 px-4 py-2 rounded-lg font-medium"
        >
          耳机
        </button>
      </div>

      <div class="grid grid-cols-2 gap-4">
        <div
          v-for="product in products"
          :key="product.id"
          class="bg-white rounded-lg shadow-sm overflow-hidden hover:shadow-md transition-shadow"
        >
          <div class="relative">
            <img :src="product.image" :alt="product.name" class="w-full h-36 object-cover" />
            <span class="absolute top-2 left-2 bg-red-500 text-white text-xs px-2 py-1 rounded">{{
              product.category
            }}</span>
          </div>
          <div class="p-3">
            <div class="text-sm text-gray-500 mb-1">{{ product.category }}</div>
            <h3 class="font-medium text-gray-800 mb-1">{{ product.name }}</h3>
            <div class="text-lg font-bold text-red-600">{{ product.price }}</div>
            <button
              @click="addToCart(product)"
              class="w-full mt-2 bg-blue-500 hover:bg-blue-600 text-white text-sm py-2 rounded-lg transition-colors"
            >
              加入购物车
            </button>
          </div>
        </div>
      </div>
    </main>

    <!-- Footer -->
    <footer class="bg-white border-t border-gray-200 mt-8">
      <div class="max-w-2xl mx-auto px-4 py-6 text-center text-gray-600 text-sm">
        <div class="flex justify-center gap-4 mb-4">
          <a href="#" class="hover:text-blue-500">关于我们</a>
          <a href="#" class="hover:text-blue-500">联系我们</a>
          <a href="#" class="hover:text-blue-500">隐私政策</a>
        </div>
        <div>© 2026 电商平台. 保留所有权利.</div>
      </div>
    </footer>
  </div>
</template>
