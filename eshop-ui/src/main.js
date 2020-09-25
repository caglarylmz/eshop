import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
//Jquery çalıştırmak için
import jQuery from 'jquery';
window.$ = window.jQuery = jQuery;
import 'bootstrap';
import './assets/app.scss';


//primevue

import ToastService from 'primevue/toastservice';
Vue.use(ToastService);


import VeeValidate from 'vee-validate';
Vue.use(VeeValidate);

Vue.config.productionTip = false;
Vue.prototype.$primevue = { ripple: true };

Vue.forceUpdate;
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
