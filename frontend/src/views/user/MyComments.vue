<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { View, Delete } from '@element-plus/icons-vue'

const router = useRouter()
const loading = ref(false)
const tableData = ref([])
const total = ref(0)

const pagination = reactive({
  page: 1,
  size: 10
})

const handleViewArticle = (row: any) => {
  router.push(`/article/${row.articleId}`)
}

const handleDelete = (row: any) => {
  console.log('Delete comment:', row)
}

const handlePageChange = (page: number) => {
  pagination.page = page
  fetchData()
}

const handleSizeChange = (size: number) => {
  pagination.size = size
  pagination.page = 1
  fetchData()
}

const fetchData = () => {
  loading.value = true
  setTimeout(() => {
    loading.value = false
    total.value = 15
  }, 1000)
}

onMounted(() => {
  fetchData()
})
</script>

<template>
  <div class="my-comments">
    <el-card>
      <template #header>
        <span class="font-bold">我的评论</span>
      </template>

      <el-table :data="tableData" v-loading="loading" style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="articleTitle" label="文章标题" min-width="200" />
        <el-table-column prop="content" label="评论内容" min-width="200" show-overflow-tooltip />
        <el-table-column prop="likeCount" label="点赞" width="100" />
        <el-table-column prop="createTime" label="评论时间" width="180" />
        <el-table-column label="操作" width="150" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" link size="small" :icon="View" @click="handleViewArticle(row)"
              >文章</el-button
            >
            <el-button type="danger" link size="small" :icon="Delete" @click="handleDelete(row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>

      <div class="flex justify-end mt-4">
        <el-pagination
          v-model:current-page="pagination.page"
          v-model:page-size="pagination.size"
          :page-sizes="[10, 20, 50]"
          :total="total"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="handleSizeChange"
          @current-change="handlePageChange"
        />
      </div>
    </el-card>
  </div>
</template>

<style scoped></style>
