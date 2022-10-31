import { createRouter, createWebHistory } from 'vue-router'
import StartView from '../views/Start.vue'
import HomeView from '../views/Home.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'start',
      component: StartView
    },
    {
      path: '/home',
      name: 'home',
      component: HomeView
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
    },
    {
      path: '/forgotPassword',
      name: 'forgotPassword',
      component: () => import('../views/user management/ForgotPass.vue')
    },
    {
      path: '/resetPassword',
      name: 'resetPassword',
      component: () => import('../views/user management/ResetPass.vue')
    }
    ,
    {
      path: '/logging',
      name: 'log',
      component: () => import('../views/logging/Log.vue')
    },
    {
      path: '/singlelog/:id',
      name: 'logpage',
      component: () => import('../views/logging/LogPage.vue'),
    },
    {
      path: '/enterlog',
      name: 'enterlog',
      component: () => import('../views/logging/EnterLog.vue')
    },
    {
      path: '/editlog/:userId/:id',
      name: 'editlog',
      component: () => import('../views/logging/EditLog.vue')
    },
    {
      path: '/binlocator',
      name: 'binlocator',
      component: () => import('../views/bin locator/BinLocator.vue')
    },
    {
      path: '/profile',
      name: 'profile',
      component: () => import('../views/user management/Profile.vue')
    },
    {
      path: '/returnedbins',
      name: 'returnedbins',
      component: () => import('../views/bin locator/ReturnedBins.vue')
    }
  ]
})

router.beforeEach((to, from, next) => {
  const publicPage = ['/','/login', '/register',  '/about', '/forgotPassword', '/resetPassword']; //
  const authNeeded = !(publicPage.includes(to.path));
  const loggedIn = localStorage.getItem('user');

  // trying to access a restricted page + not logged in
  // redirect to login page
  if (authNeeded && !loggedIn) {
    next('/login');
  } else {
    next();
  }
});

export default router
