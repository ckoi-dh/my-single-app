<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { useAuthStore } from '../../stores/useAuthStore'

const authStore = useAuthStore()
const loading = ref(false)
const submitting = ref(false)

const profileForm = reactive({
  username: '',
  nickname: '',
  email: '',
  avatar: ''
})

const handleAvatarUpload = (file: any) => {
  console.log('Upload avatar:', file)
  return false
}

const handleSave = async () => {
  try {
    submitting.value = true
    await new Promise((resolve) => setTimeout(resolve, 1000))
    ElMessage.success('保存成功')
    authStore.updateUserInfo(profileForm)
  } catch {
    ElMessage.error('保存失败')
  } finally {
    submitting.value = false
  }
}

onMounted(() => {
  loading.value = true
  setTimeout(() => {
    Object.assign(
      profileForm,
      authStore.currentUser || {
        username: 'demo',
        nickname: '演示用户',
        email: 'demo@example.com',
        avatar: ''
      }
    )
    loading.value = false
  }, 500)
})
</script>

<template>
  <div class="user-profile">
    <el-card>
      <template #header>
        <span class="font-bold">个人资料</span>
      </template>

      <el-skeleton :loading="loading" animated>
        <el-form :model="profileForm" label-width="100px">
          <el-form-item label="头像">
            <el-upload
              class="avatar-uploader"
              :show-file-list="false"
              :before-upload="handleAvatarUpload"
            >
              <img v-if="profileForm.avatar" :src="profileForm.avatar" class="avatar" />
              <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
            </el-upload>
          </el-form-item>

          <el-form-item label="用户名">
            <el-input v-model="profileForm.username" disabled />
          </el-form-item>

          <el-form-item label="昵称">
            <el-input v-model="profileForm.nickname" placeholder="请输入昵称" />
          </el-form-item>

          <el-form-item label="邮箱">
            <el-input v-model="profileForm.email" placeholder="请输入邮箱" />
          </el-form-item>

          <el-form-item>
            <el-button type="primary" :loading="submitting" @click="handleSave">保存修改</el-button>
          </el-form-item>
        </el-form>
      </el-skeleton>
    </el-card>
  </div>
</template>

<style scoped>
.avatar-uploader .avatar {
  width: 100px;
  height: 100px;
  display: block;
  border-radius: 50%;
  object-fit: cover;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100px;
  text-align: center;
  line-height: 100px;
  border: 1px dashed #d9d9d9;
  border-radius: 50%;
  cursor: pointer;
}
</style>
