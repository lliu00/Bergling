import Vue from 'vue'
import VueRouter from 'vue-router'
import Blogs from '../views/Blogs.vue'
import Login from '../views/Login.vue'
import BlogEdit from '../views/BlogEdit.vue'
import BlogDetail from '../views/BlogDetail.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'index',
    component: Blogs
  },
    {
      path: '/blogs',
      name: 'blogs',
      component: Blogs
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/blogs/add',
      name: 'blogAdd',
      component: BlogEdit
    },
    {
      path: '/blogs/:blogId',
      name: 'blogDetail',
      component: BlogDetail
    },
    {
      path: '/blogs/:blogId/edit',
      name: 'blogEdit',
      component: BlogEdit
    },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
