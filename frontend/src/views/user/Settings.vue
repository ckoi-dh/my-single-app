<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { ElMessage } from 'element-plus'

const loading = ref(false)
const submitting = ref(false)
const activeTab = ref('basic')

const basicSettings = reactive({
  nickname: '',
  email: '',
  bio: ''
})

const passwordSettings = reactive({
  oldPassword: '',
  newPassword: '',
  confirmPassword: ''
})

const notificationSettings = reactive({
  emailNotification: true,
  commentNotification: true,
  likeNotification: true
})

const handleSaveBasic = async () => {
  try {
    submitting.value = true
    await new Promise((resolve) => setTimeout(resolve, 1000))
    ElMessage.success('保存成功')
  } catch {
    ElMessage.error('保存失败')
  } finally {
    submitting.value = false
  }
}

const handleSavePassword = async () => {
  if (passwordSettings.newPassword !== passwordSettings.confirmPassword) {
    ElMessage.error('两次输入的密码不一致')
    return
  }
  try {
    submitting.value = true
    await new Promise((resolve) => setTimeout(resolve, 1000))
    ElMessage.success('密码修改成功')
    passwordSettings.oldPassword = ''
    passwordSettings.newPassword = ''
    passwordSettings.confirmPassword = ''
  } catch {
    ElMessage.error('保存失败')
  } finally {
    submitting.value = false
  }
}

const handleSaveNotification = async () => {
  try {
    submitting.value = true
    await new Promise((resolve) => setTimeout(resolve, 1000))
    ElMessage.success('保存成功')
  } catch {
    ElMessage.error('保存失败')
  } finally {
    submitting.value = false
  }
}

onMounted(() => {
  loading.value = true
  setTimeout(() => {
    basicSettings.nickname = '演示用户'
    basicSettings.email = 'demo@example.com'
    basicSettings.bio = '这是个人简介...'
    loading.value = false
  }, 500)
})
</script>

<template>
  <div class="settings">
    <el-card>
      <template #header>
        <span class="font-bold">个人设置</span>
      </template>

      <el-tabs v-model="activeTab">
        <el-tab-pane label="基本设置" name="basic">
          <el-form :model="basicSettings" label-width="100px" v-loading="loading">
            <el-form-item label="昵称">
              <el-input v-model="basicSettings.nickname" placeholder="请输入昵称" />
            </el-form-item>
            <el-form-item label="邮箱">
              <el-input v-model="basicSettings.email" placeholder="请输入邮箱" />
            </el-form-item>
            <el-form-item label="个人简介">
              <el-input
                v-model="basicSettings.bio"
                type="textarea"
                :rows="4"
                placeholder="请输入个人简介"
              />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" :loading="submitting" @click="handleSaveBasic"
                >保存修改</el-button
              >
            </el-form-item>
          </el-form>
        </el-tab-pane>

        <el-tab-pane label="修改密码" name="password">
          <el-form :model="passwordSettings" label-width="100px" v-loading="loading">
            <el-form-item label="当前密码">
              <el-input
                v-model="passwordSettings.oldPassword"
                type="password"
                placeholder="请输入当前密码"
                show-password
              />
            </el-form-item>
            <el-form-item label="新密码">
              <el-input
                v-model="passwordSettings.newPassword"
                type="password"
                placeholder="请输入新密码"
                show-password
              />
            </el-form-item>
            <el-form-item label="确认密码">
              <el-input
                v-model="passwordSettings.confirmPassword"
                type="password"
                placeholder="请再次输入新密码"
                show-password
              />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" :loading="submitting" @click="handleSavePassword"
                >修改密码</el-button
              >
            </el-form-item>
          </el-form>
        </el-tab-pane>

        <el-tab-pane label="消息通知" name="notification">
          <el-form :model="notificationSettings" label-width="150px" v-loading="loading">
            <el-form-item label="邮件通知">
              <el-switch v-model="notificationSettings.emailNotification" />
            </el-form-item>
            <el-form-item label="评论通知">
              <el-switch v-model="notificationSettings.commentNotification" />
            </el-form-item>
            <el-form-item label="点赞通知">
              <el-switch v-model="notificationSettings.likeNotification" />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" :loading="submitting" @click="handleSaveNotification"
                >保存设置</el-button
              >
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<style scoped></style>
