<script setup lang="ts">
import { ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'

const formData = ref({
  name: '',
  email: '',
  role: ''
})

const tableData = ref([
  { id: 1, name: '张三', email: 'zhangsan@example.com', role: '管理员' },
  { id: 2, name: '李四', email: 'lisi@example.com', role: '用户' },
  { id: 3, name: '王五', email: 'wangwu@example.com', role: '用户' }
])

const handleSubmit = () => {
  ElMessage.success('表单提交成功！')
}

const handleDelete = (row: any) => {
  ElMessageBox.confirm(`确定要删除 ${row.name} 吗？`, '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    ElMessage.success('删除成功！')
  })
}
</script>

<template>
  <div class="bg-white rounded-xl shadow-lg p-6 max-w-6xl mx-auto my-8">
    <!-- Element Plus 表单组件 + Tailwind 样式 -->
    <div class="mb-8">
      <h2 class="text-2xl font-bold mb-4 text-gray-800">管理后台 - 用户表单</h2>
      <div class="grid grid-cols-1 md:grid-cols-3 gap-4 mb-6">
        <el-input v-model="formData.name" placeholder="请输入用户名" class="w-full" />
        <el-input v-model="formData.email" placeholder="请输入邮箱" class="w-full" />
        <el-select v-model="formData.role" placeholder="请选择角色" class="w-full">
          <el-option label="管理员" value="admin" />
          <el-option label="用户" value="user" />
        </el-select>
      </div>
      <el-button type="primary" @click="handleSubmit" class="bg-blue-600 hover:bg-blue-700">
        提交表单
      </el-button>
    </div>

    <!-- Element Plus 表格组件 + Tailwind 样式 -->
    <div class="border-t border-gray-200 pt-6">
      <h2 class="text-2xl font-bold mb-4 text-gray-800">用户列表</h2>
      <el-table :data="tableData" stripe class="w-full">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="name" label="姓名" width="150" />
        <el-table-column prop="email" label="邮箱" />
        <el-table-column prop="role" label="角色" width="120">
          <template #default="{ row }">
            <span
              :class="
                row.role === '管理员'
                  ? 'px-2 py-1 bg-purple-100 text-purple-800 rounded-full text-xs'
                  : 'px-2 py-1 bg-blue-100 text-blue-800 rounded-full text-xs'
              "
            >
              {{ row.role }}
            </span>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150">
          <template #default="{ row }">
            <el-button type="primary" size="small" class="mr-2">编辑</el-button>
            <el-button type="danger" size="small" @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
