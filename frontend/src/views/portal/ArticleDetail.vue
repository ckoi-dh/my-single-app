<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const loading = ref(false)
const article = ref<any>(null)

onMounted(() => {
  loading.value = true
  setTimeout(() => {
    article.value = {
      id: route.params.id,
      title: '示例文章标题',
      content:
        '# 这是文章标题\n\n这是文章的正文内容。这里使用 Markdown 格式来展示。\n\n## 二级标题\n\n这是二级标题下的内容。\n\n### 三级标题\n\n这是三级标题下的内容。\n\n- 列表项 1\n- 列表项 2\n- 列表项 3\n\n`代码块`\n\n> 这是引用内容',
      author: '作者名',
      createTime: '2024-01-01 12:00:00',
      viewCount: 100,
      likeCount: 20,
      commentCount: 5,
      tags: ['Vue', 'TypeScript', '前端']
    }
    loading.value = false
  }, 1000)
})
</script>

<template>
  <div class="article-detail">
    <!-- Header -->
    <header class="bg-white shadow-sm sticky top-0 z-50">
      <div class="max-w-4xl mx-auto px-4">
        <div class="flex items-center justify-between h-16">
          <router-link to="/" class="text-xl font-bold text-blue-600">我的博客</router-link>
          <nav class="flex space-x-6">
            <router-link to="/" class="text-gray-700 hover:text-blue-600">首页</router-link>
            <router-link to="/archive" class="text-gray-700 hover:text-blue-600">归档</router-link>
          </nav>
        </div>
      </div>
    </header>

    <!-- Article Content -->
    <div class="max-w-4xl mx-auto px-4 py-8">
      <el-skeleton :loading="loading" animated>
        <div v-if="article">
          <!-- Article Title -->
          <h1 class="text-3xl font-bold mb-4">{{ article.title }}</h1>

          <!-- Article Meta -->
          <div class="flex items-center text-gray-500 mb-6 pb-6 border-b">
            <span>{{ article.author }}</span>
            <span class="mx-2">•</span>
            <span>{{ article.createTime }}</span>
            <span class="mx-2">•</span>
            <span>浏览 {{ article.viewCount }}</span>
          </div>

          <!-- Article Tags -->
          <div class="mb-6">
            <el-tag v-for="tag in article.tags" :key="tag" class="mr-2">
              <router-link :to="`/tag/1`" class="text-inherit">{{ tag }}</router-link>
            </el-tag>
          </div>

          <!-- Article Body -->
          <div class="prose max-w-none mb-8">
            <div class="markdown-body" v-html="article.content"></div>
          </div>

          <!-- Article Actions -->
          <div class="flex items-center justify-center gap-8 py-6 border-t border-b">
            <div class="flex items-center gap-2 cursor-pointer text-gray-600 hover:text-red-500">
              <el-icon :size="24"><Star /></el-icon>
              <span>{{ article.likeCount }}</span>
            </div>
            <div class="flex items-center gap-2 cursor-pointer text-gray-600 hover:text-blue-500">
              <el-icon :size="24"><ChatDotRound /></el-icon>
              <span>{{ article.commentCount }}</span>
            </div>
            <div class="flex items-center gap-2 cursor-pointer text-gray-600 hover:text-blue-500">
              <el-icon :size="24"><Share /></el-icon>
              <span>分享</span>
            </div>
          </div>

          <!-- Comments Section -->
          <div class="mt-8">
            <h3 class="text-xl font-bold mb-6">评论 ({{ article.commentCount }})</h3>

            <!-- Comment Form -->
            <div class="bg-gray-50 rounded-lg p-4 mb-6">
              <el-input type="textarea" :rows="4" placeholder="写下你的评论..." class="mb-4" />
              <div class="flex justify-end">
                <el-button type="primary">发表评论</el-button>
              </div>
            </div>

            <!-- Comment List -->
            <div class="space-y-6">
              <div v-for="i in 3" :key="i" class="flex gap-4">
                <el-avatar :size="40">用</el-avatar>
                <div class="flex-1">
                  <div class="flex items-center gap-2 mb-2">
                    <span class="font-bold">用户名 {{ i }}</span>
                    <span class="text-gray-400 text-sm">2024-01-01 12:00:00</span>
                  </div>
                  <p class="text-gray-700">这是评论内容，用户对文章发表的看法...</p>
                  <div class="flex items-center gap-4 mt-2 text-sm text-gray-500">
                    <span class="cursor-pointer hover:text-blue-500">回复</span>
                    <span class="cursor-pointer hover:text-red-500">点赞 ({{ i }})</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </el-skeleton>
    </div>

    <!-- Footer -->
    <footer class="bg-gray-800 text-white py-8">
      <div class="max-w-4xl mx-auto px-4 text-center">
        <p class="text-gray-400">© 2024 我的博客. All rights reserved.</p>
      </div>
    </footer>
  </div>
</template>

<style scoped>
.markdown-body {
  line-height: 1.8;
}

.markdown-body h1 {
  font-size: 2rem;
  font-weight: bold;
  margin: 1.5rem 0 1rem;
}

.markdown-body h2 {
  font-size: 1.5rem;
  font-weight: bold;
  margin: 1.25rem 0 0.75rem;
}

.markdown-body h3 {
  font-size: 1.25rem;
  font-weight: bold;
  margin: 1rem 0 0.5rem;
}

.markdown-body p {
  margin: 0.75rem 0;
}

.markdown-body ul,
.markdown-body ol {
  padding-left: 1.5rem;
  margin: 0.75rem 0;
}

.markdown-body li {
  margin: 0.25rem 0;
}

.markdown-body code {
  background-color: #f3f4f6;
  padding: 0.125rem 0.375rem;
  border-radius: 0.25rem;
  font-family: monospace;
}

.markdown-body blockquote {
  border-left: 4px solid #3b82f6;
  padding-left: 1rem;
  margin: 1rem 0;
  color: #6b7280;
}
</style>
