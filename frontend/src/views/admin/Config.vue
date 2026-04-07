<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { ElMessage } from 'element-plus'

const loading = ref(false)
const submitting = ref(false)
const activeTab = ref('basic')

const basicConfig = reactive({
  siteName: '我的博客',
  siteDescription: '分享技术，分享生活',
  siteKeywords: '博客,技术,编程',
  siteIcp: '',
  siteCopyright: '© 2024 My Blog'
})

const emailConfig = reactive({
  smtpHost: '',
  smtpPort: 465,
  smtpUser: '',
  smtpPass: '',
  fromEmail: '',
  fromName: '博客系统'
})

const uploadConfig = reactive({
  uploadType: 'local',
  qiniuAccessKey: '',
  qiniuSecretKey: '',
  qiniuBucket: '',
  qiniuDomain: ''
})

const handleSave = async () => {
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
    loading.value = false
  }, 1000)
})
</script>

<template>
  <div class="config">
    <el-card>
      <template #header>
        <span class="font-bold">系统配置</span>
      </template>

      <el-tabs v-model="activeTab">
        <el-tab-pane label="基础配置" name="basic">
          <el-form :model="basicConfig" label-width="150px" v-loading="loading">
            <el-form-item label="网站名称">
              <el-input v-model="basicConfig.siteName" placeholder="请输入网站名称" />
            </el-form-item>
            <el-form-item label="网站描述">
              <el-input
                v-model="basicConfig.siteDescription"
                type="textarea"
                :rows="3"
                placeholder="请输入网站描述"
              />
            </el-form-item>
            <el-form-item label="网站关键词">
              <el-input v-model="basicConfig.siteKeywords" placeholder="请输入网站关键词" />
            </el-form-item>
            <el-form-item label="ICP备案号">
              <el-input v-model="basicConfig.siteIcp" placeholder="请输入ICP备案号" />
            </el-form-item>
            <el-form-item label="版权信息">
              <el-input v-model="basicConfig.siteCopyright" placeholder="请输入版权信息" />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" :loading="submitting" @click="handleSave"
                >保存配置</el-button
              >
            </el-form-item>
          </el-form>
        </el-tab-pane>

        <el-tab-pane label="邮件配置" name="email">
          <el-form :model="emailConfig" label-width="150px" v-loading="loading">
            <el-form-item label="SMTP服务器">
              <el-input v-model="emailConfig.smtpHost" placeholder="请输入SMTP服务器地址" />
            </el-form-item>
            <el-form-item label="SMTP端口">
              <el-input-number v-model="emailConfig.smtpPort" :min="1" :max="65535" />
            </el-form-item>
            <el-form-item label="SMTP用户名">
              <el-input v-model="emailConfig.smtpUser" placeholder="请输入SMTP用户名" />
            </el-form-item>
            <el-form-item label="SMTP密码">
              <el-input
                v-model="emailConfig.smtpPass"
                type="password"
                placeholder="请输入SMTP密码"
                show-password
              />
            </el-form-item>
            <el-form-item label="发件人邮箱">
              <el-input v-model="emailConfig.fromEmail" placeholder="请输入发件人邮箱" />
            </el-form-item>
            <el-form-item label="发件人名称">
              <el-input v-model="emailConfig.fromName" placeholder="请输入发件人名称" />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" :loading="submitting" @click="handleSave"
                >保存配置</el-button
              >
            </el-form-item>
          </el-form>
        </el-tab-pane>

        <el-tab-pane label="上传配置" name="upload">
          <el-form :model="uploadConfig" label-width="150px" v-loading="loading">
            <el-form-item label="存储类型">
              <el-radio-group v-model="uploadConfig.uploadType">
                <el-radio label="local">本地存储</el-radio>
                <el-radio label="qiniu">七牛云</el-radio>
              </el-radio-group>
            </el-form-item>
            <template v-if="uploadConfig.uploadType === 'qiniu'">
              <el-form-item label="AccessKey">
                <el-input
                  v-model="uploadConfig.qiniuAccessKey"
                  placeholder="请输入七牛云AccessKey"
                />
              </el-form-item>
              <el-form-item label="SecretKey">
                <el-input
                  v-model="uploadConfig.qiniuSecretKey"
                  type="password"
                  placeholder="请输入七牛云SecretKey"
                  show-password
                />
              </el-form-item>
              <el-form-item label="Bucket">
                <el-input v-model="uploadConfig.qiniuBucket" placeholder="请输入七牛云Bucket" />
              </el-form-item>
              <el-form-item label="域名">
                <el-input v-model="uploadConfig.qiniuDomain" placeholder="请输入七牛云域名" />
              </el-form-item>
            </template>
            <el-form-item>
              <el-button type="primary" :loading="submitting" @click="handleSave"
                >保存配置</el-button
              >
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<style scoped></style>
