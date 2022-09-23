import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import store from './store'
import './assets/main.css'

// BOOTSTRAP
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"

// AOS Transitions
import AOS from 'aos'
import 'aos/dist/aos.css'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(store)


app.mount('#app')
