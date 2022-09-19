import { createRouter, createWebHistory } from 'vue-router'
import StartView from '../views/Start.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'start',
      component: StartView
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/About.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/user management/Login.vue')
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('../views/user management/Register.vue')
    }
  ]
})

export default router
