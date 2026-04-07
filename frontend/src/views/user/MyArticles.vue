<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { Edit, View, Delete, Plus } from '@element-plus/icons-vue'

const router = useRouter()
const loading = ref(false)
const tableData = ref([])
const total = ref(0)

const pagination = reactive({
  page: 1,
  size: 10
})

const handleCreate = () => {
  router.push('/admin/articles/create')
}

const handleEdit = (row: any) => {
  router.push(`/admin/articles/${row.id}/edit`)
}

const handleView = (row: any) => {
  router.push(`/article/${row.id}`)
}

const handleDelete = (row: any) => {
  console.log('Delete article:', row)
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
    total.value = 10
  }, 1000)
}

onMounted(() => {
  fetchData()
})
</script>

<template>
  <div class="my-articles">
    <el-card>
      <template #header>
        <div class="flex items-center justify-between">
          <span class="font-bold">我的文章</span>
          <el-button type="primary" :icon="Plus" @click="handleCreate">写文章</el-button>
        </div>
      </template>

      <el-table :data="tableData" v-loading="loading" style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="title" label="标题" min-width="200" />
        <el-table-column prop="status" label="状态" width="100">
          <template #default="{ row }">
            <el-tag v-if="row.status === 1" type="success">已发布</el-tag>
            <el-tag v-else type="info">草稿</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="viewCount" label="浏览" width="100" />
        <el-table-column prop="likeCount" label="点赞" width="100" />
        <el-table-column prop="commentCount" label="评论" width="100" />
        <el-table-column prop="updateTime" label="更新时间" width="180" />
        <el-table-column label="操作" width="200" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" link size="small" :icon="View" @click="handleView(row)"
              >查看</el-button
            >
            <el-button type="primary" link size="small" :icon="Edit" @click="handleEdit(row)"
              >编辑</el-button
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
