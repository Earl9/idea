import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import axios from 'axios'

// var axios = require('axios')
// // 设置反向代理，前端请求默认发送到 http://localhost:8080/api，后端的端口是9090,vue项目端口是8080
// axios.defaults.baseURL = 'http://localhost:9090/api'


const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(ElementPlus)

app.config.globalProperties.$http=axios
axios.defaults.baseURL = 'http://localhost:9090/api' 

app.mount('#app')
