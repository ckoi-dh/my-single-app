import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '../stores/useAuthStore'

const routes = [
  // Admin routes
  {
    path: '/admin',
    name: 'admin',
    redirect: '/admin/dashboard',
    component: () => import('../views/admin/Layout.vue'),
    meta: { requireAuth: true, title: '管理后台' },
    children: [
      {
        path: 'dashboard',
        name: 'admin-dashboard',
        component: () => import('../views/admin/Dashboard.vue'),
        meta: { requireAuth: true, title: '仪表板' }
      },
      {
        path: 'articles',
        name: 'admin-articles',
        component: () => import('../views/admin/ArticleList.vue'),
        meta: { requireAuth: true, title: '文章管理' }
      },
      {
        path: 'articles/create',
        name: 'admin-article-create',
        component: () => import('../views/admin/ArticleEdit.vue'),
        meta: { requireAuth: true, title: '创建文章' }
      },
      {
        path: 'articles/:id/edit',
        name: 'admin-article-edit',
        component: () => import('../views/admin/ArticleEdit.vue'),
        meta: { requireAuth: true, title: '编辑文章' }
      },
      {
        path: 'categories',
        name: 'admin-categories',
        component: () => import('../views/admin/CategoryList.vue'),
        meta: { requireAuth: true, title: '分类管理' }
      },
      {
        path: 'tags',
        name: 'admin-tags',
        component: () => import('../views/admin/TagList.vue'),
        meta: { requireAuth: true, title: '标签管理' }
      },
      {
        path: 'comments',
        name: 'admin-comments',
        component: () => import('../views/admin/CommentList.vue'),
        meta: { requireAuth: true, title: '评论管理' }
      },
      {
        path: 'users',
        name: 'admin-users',
        component: () => import('../views/admin/UserList.vue'),
        meta: { requireAuth: true, title: '用户管理' }
      },
      {
        path: 'config',
        name: 'admin-config',
        component: () => import('../views/admin/Config.vue'),
        meta: { requireAuth: true, title: '系统配置' }
      }
    ]
  },
  // Portal routes
  {
    path: '/',
    name: 'home',
    component: () => import('../views/portal/Home.vue'),
    meta: { title: '首页' }
  },
  {
    path: '/article/:id',
    name: 'article-detail',
    component: () => import('../views/portal/ArticleDetail.vue'),
    meta: { title: '文章详情' }
  },
  {
    path: '/category/:id',
    name: 'category-detail',
    component: () => import('../views/portal/CategoryDetail.vue'),
    meta: { title: '分类' }
  },
  {
    path: '/tag/:id',
    name: 'tag-detail',
    component: () => import('../views/portal/TagDetail.vue'),
    meta: { title: '标签' }
  },
  {
    path: '/search',
    name: 'search',
    component: () => import('../views/portal/Search.vue'),
    meta: { title: '搜索' }
  },
  {
    path: '/archive',
    name: 'archive',
    component: () => import('../views/portal/Archive.vue'),
    meta: { title: '时间线' }
  },
  // User routes
  {
    path: '/user',
    name: 'user',
    redirect: '/user/profile',
    component: () => import('../views/user/Layout.vue'),
    meta: { requireAuth: true },
    children: [
      {
        path: 'profile',
        name: 'user-profile',
        component: () => import('../views/user/Profile.vue'),
        meta: { requireAuth: true, title: '个人资料' }
      },
      {
        path: 'articles',
        name: 'user-articles',
        component: () => import('../views/user/MyArticles.vue'),
        meta: { requireAuth: true, title: '我的文章' }
      },
      {
        path: 'favorites',
        name: 'user-favorites',
        component: () => import('../views/user/Favorites.vue'),
        meta: { requireAuth: true, title: '我的收藏' }
      },
      {
        path: 'comments',
        name: 'user-comments',
        component: () => import('../views/user/MyComments.vue'),
        meta: { requireAuth: true, title: '我的评论' }
      },
      {
        path: 'settings',
        name: 'user-settings',
        component: () => import('../views/user/Settings.vue'),
        meta: { requireAuth: true, title: '个人设置' }
      }
    ]
  },
  // Auth routes
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/admin/Login.vue'),
    meta: { title: '登录' }
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('../views/admin/Register.vue'),
    meta: { title: '注册' }
  },
  // 404
  {
    path: '/:pathMatch(.*)*',
    name: 'not-found',
    component: () => import('../views/common/NotFound.vue'),
    meta: { title: '404' }
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

// Navigation guard
router.beforeEach((to, _, next) => {
  const authStore = useAuthStore()
  const requireAuth = to.meta.requireAuth

  if (to.meta.title) {
    document.title = to.meta.title + ' - 博客系统'
  }

  if (requireAuth && !authStore.isAuthenticated) {
    next('/login')
  } else if (authStore.isAuthenticated && (to.name === 'login' || to.name === 'register')) {
    next('/')
  } else {
    next()
  }
})

export default router
