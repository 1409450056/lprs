import Vue from 'vue'
import vUploader from 'v-uploader'
import vDialogs from 'v-dialogs'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en' // lang i18n

import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'

import '@/icons' // icon
import '@/permission' // permission control

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */
Vue.use(vDialogs, { instanceName: '$dlg' })
const uploaderConfig = {
  uploadFileUrl: 'http://106.54.238.66:8080/api/upload',
  deleteFileUrl: 'http://localhost:8080/api/deleteimg',
  uploadFileObjName: 'file',
  showMessage: (vue, message) => {
    vue.$dlg.alert(message, { messageType: 'error' })
  }
}
Vue.use(vUploader, uploaderConfig)

import { mockXHR } from '../mock'
if (process.env.NODE_ENV === 'production') {
  mockXHR()
}

// set ElementUI lang to EN
Vue.use(ElementUI, { locale })

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
