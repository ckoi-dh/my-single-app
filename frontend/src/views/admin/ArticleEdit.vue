<script setup lang="ts">
import { ref, reactive, onMounted, computed } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { ElMessage } from 'element-plus'
import { ArrowLeft, Check as Save } from '@element-plus/icons-vue'

const router = useRouter()
const route = useRoute()

const articleId = computed(() => route.params.id as string)
const isEdit = computed(() => !!articleId.value)

const loading = ref(false)
const submitting = ref(false)

const articleForm = reactive({
  title: '',
  summary: '',
  content: '',
  coverImage: '',
  categoryId: '',
  status: 1,
  tags: [] as number[]
})

interface CategoryOption {
  id: number
  name: string
}

interface TagOption {
  id: number
  name: string
}

const categoryOptions = ref<CategoryOption[]>([])
const tagOptions = ref<TagOption[]>([])

const handleBack = () => {
  router.back()
}

const handleSubmit = async (publish: boolean) => {
  articleForm.status = publish ? 1 : 0
  try {
    submitting.value = true
    await new Promise((resolve) => setTimeout(resolve, 1000))
    ElMessage.success(publish ? '发布成功' : '保存成功')
    router.push('/admin/articles')
  } catch (error) {
    ElMessage.error('操作失败')
  } finally {
    submitting.value = false
  }
}

const handleCoverUpload = (file: any) => {
  console.log('Upload cover:', file)
  return false
}

onMounted(() => {
  if (isEdit.value) {
    loading.value = true
    setTimeout(() => {
      articleForm.title = '示例文章标题'
      articleForm.summary = '这是文章摘要...'
      articleForm.content = '# 这是文章内容\n\n这是 Markdown 格式的文章内容...'
      loading.value = false
    }, 1000)
  }
})
</script>

<template>
  <div class="article-edit">
    <el-page-header :icon="ArrowLeft" @back="handleBack">
      <template #content>
        <span>{{ isEdit ? '编辑文章' : '新建文章' }}</span>
      </template>
    </el-page-header>

    <el-card class="mt-4" v-loading="loading">
      <el-form :model="articleForm" label-width="100px">
        <el-form-item label="文章标题">
          <el-input v-model="articleForm.title" placeholder="请输入文章标题" />
        </el-form-item>

        <el-form-item label="文章摘要">
          <el-input
            v-model="articleForm.summary"
            type="textarea"
            :rows="3"
            placeholder="请输入文章摘要"
          />
        </el-form-item>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="文章分类">
              <el-select
                v-model="articleForm.categoryId"
                placeholder="请选择分类"
                style="width: 100%"
              >
                <el-option
                  v-for="item in categoryOptions"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="文章标签">
              <el-select
                v-model="articleForm.tags"
                multiple
                placeholder="请选择标签"
                style="width: 100%"
              >
                <el-option
                  v-for="item in tagOptions"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="封面图片">
          <el-upload
            class="cover-uploader"
            :show-file-list="false"
            :before-upload="handleCoverUpload"
          >
            <img v-if="articleForm.coverImage" :src="articleForm.coverImage" class="cover" />
            <el-icon v-else class="cover-uploader-icon"><Plus /></el-icon>
          </el-upload>
        </el-form-item>

        <el-form-item label="文章内容">
          <div class="markdown-editor">
            <el-input
              v-model="articleForm.content"
              type="textarea"
              :rows="20"
              placeholder="请输入文章内容（支持 Markdown）"
            />
          </div>
        </el-form-item>

        <el-form-item>
          <el-button :loading="submitting" @click="handleSubmit(false)"> 保存草稿 </el-button>
          <el-button type="primary" :loading="submitting" :icon="Save" @click="handleSubmit(true)">
            立即发布
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<style scoped>
.cover-uploader .cover {
  width: 178px;
  height: 178px;
  display: block;
  object-fit: cover;
}

.cover-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
  line-height: 178px;
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
}
</style>
