import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//Jquery çalıştırmak için
import jQuery from 'jquery'
window.$ = window.jQuery = jQuery;
import 'bootstrap'
import './assets/app.scss'

import 'primevue/resources/themes/nova-light/theme.css';
import 'primevue/resources/primevue.min.css';
import 'primeicons/primeicons.css';
import 'primeflex/primeflex.css';

//primevue

import ToastService from 'primevue/toastservice';
Vue.use(ToastService);


import VeeValidate from 'vee-validate';
Vue.use(VeeValidate);

Vue.config.productionTip = false
Vue.forceUpdate;
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
