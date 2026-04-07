<script setup lang="ts">
import { ref, reactive, onMounted, computed } from 'vue'
import { ElMessage, ElMessageBox, type FormInstance, type FormRules } from 'element-plus'
import { Delete, Edit, Plus } from '@element-plus/icons-vue'

const loading = ref(false)
const tableData = ref([])
const total = ref(0)
const dialogVisible = ref(false)
const isEdit = ref(false)
const dialogTitle = computed(() => isEdit.value ? '编辑标签' : '新增标签')

const tagFormRef = ref<FormInstance>()
const tagForm = reactive({
  name: '',
  slug: ''
})

const tagRules: FormRules = {
  name: [{ required: true, message: '请输入标签名称', trigger: 'blur' }],
  slug: [{ required: true, message: '请输入标签别名', trigger: 'blur' }]
}

const pagination = reactive({
  page: 1,
  size: 10
})

const handleCreate = () => {
  isEdit.value = false
  resetForm()
  dialogVisible.value = true
}

const handleEdit = (_row: unknown) => {
  isEdit.value = true
  dialogVisible.value = true
}

const handleDelete = async (_row: unknown) => {
  try {
    await ElMessageBox.confirm('确定要删除这个标签吗？', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    ElMessage.success('删除成功')
    fetchData()
  } catch {
    // User cancelled
  }
}

const handleSubmit = async () => {
  if (!tagFormRef.value) return
  await tagFormRef.value.validate(async (valid) => {
    if (valid) {
      try {
        await new Promise((resolve) => setTimeout(resolve, 1000))
        ElMessage.success(isEdit.value ? '更新成功' : '创建成功')
        dialogVisible.value = false
        fetchData()
      } catch {
        ElMessage.error('操作失败')
      }
    }
  })
}

const resetForm = () => {
  tagForm.name = ''
  tagForm.slug = ''
  tagFormRef.value?.resetFields()
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
  <div class="tag-list">
    <el-card>
      <template #header>
        <div class="flex items-center justify-between">
          <span class="font-bold">标签管理</span>
          <el-button type="primary" :icon="Plus" @click="handleCreate">新增标签</el-button>
        </div>
      </template>

      <el-table :data="tableData" v-loading="loading" style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="name" label="标签名称" min-width="150" />
        <el-table-column prop="slug" label="标签别名" min-width="150" />
        <el-table-column prop="articleCount" label="文章数" width="100" />
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column label="操作" width="150" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" link size="small" :icon="Edit" @click="handleEdit(row)">编辑</el-button>
            <el-button type="danger" link size="small" :icon="Delete" @click="handleDelete(row)">删除</el-button>
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

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="500px">
      <el-form ref="tagFormRef" :model="tagForm" :rules="tagRules" label-width="100px">
        <el-form-item label="标签名称" prop="name">
          <el-input v-model="tagForm.name" placeholder="请输入标签名称" />
        </el-form-item>
        <el-form-item label="标签别名" prop="slug">
          <el-input v-model="tagForm.slug" placeholder="请输入标签别名" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<style scoped></style>
