import Vue from 'vue';
import VueRouter from 'vue-router';
import store from '@/store';
import Home from '@/views/Home.vue';
import Login from '@/views/auth/Login.vue';
import Register from '@/views/auth/Register.vue';
import NotFound from '@/views/auth/NotFound.vue';
import NoAccess from '@/views/auth/NoAccess.vue';
import Panel from '@/views/Panel.vue';
import PanelAdverts from '@/views/panel/Adverts.vue';
import PanelCategories from '@/views/panel/category/Categories.vue';
import PanelMainCategory from '@/views/panel/category/MainCategory.vue';
import PanelSubCategory from '@/views/panel/category/SubCategory.vue';
import PanelMessages from '@/views/panel/Messages.vue';
import PanelMyAdverts from '@/views/panel/MyAdverts.vue';
import PanelOverview from '@/views/panel/Overview.vue';
import PanelProfile from '@/views/panel/Profile.vue';
import PanelUsers from '@/views/panel/Users.vue';



Vue.use(VueRouter);

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/no-access',
      name: 'noaccess',
      component: NoAccess
    },
    {
      path: '/home',
      component: Home,
    },
    {
      path: '/panel',
      component: Panel,
      meta: { requiresAuth: true },
      children: [
        {
          path: '',
          name: 'overview',
          component: PanelOverview,
        },
        {
          path: 'adverts',
          name: 'adverts',
          component: PanelAdverts,
          meta: { roles: ['ADMIN'] },
        },
        {
          path: 'categories',
          name: 'categories',
          component: PanelCategories,
          meta: { roles: ['ADMIN'] },
        },
        {
          path: 'categories/main/:categoryId',
          name: 'maincategory',
          component: PanelMainCategory,
          props: true,
          meta: { roles: ['ADMIN'] },
        },
        {
          path: 'categories/sub/:categoryId/',
          name: 'subcategory',
          component: PanelSubCategory,
          props: true,
          meta: { roles: ['ADMIN'] },
        },
        {
          path: 'messages',
          name: 'messages',
          component: PanelMessages,
        },
        {
          path: 'myadverts',
          name: 'myadverts',
          component: PanelMyAdverts,
        },
        {
          path: 'profile',
          name: 'profile',
          component: PanelProfile,
        },
        {
          path: 'users',
          name: 'users',
          component: PanelUsers,
        },

      ],
    },
    {
      path: '*',
      redirect: 'notfound',
      component: NotFound
    }
  ]

});

router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);
  const currentUser = store.state.auth.user;
  //Eğer kullanıcı login olmamışsa ve requiresAuth gerektiren bir route'a girmek isterse
  if (requiresAuth && !currentUser) {
    //login'e yönlenir
    return next({ path: "/login", query: { returnUrl: to.path } });
    //Eğer kullanıc login olmuşsa ve requiresAuth isteyen bir route girmek isterse
  } else if (requiresAuth && currentUser) {
    let lacksRole = to.matched.some(route => {
      //roles isteyen bir route'a girmek isterse ve kullanıcının rolü roles: ['ADMIN'] ise true döner değilse false
      return route.meta.roles && !route.meta.roles.includes(currentUser.roles[0]);
    });
    //eğer login olan kullanıcı roles: ['ADMIN'] uygun değilse noaccess'a yönlenir
    if (lacksRole) {
      return next({ path: "/no-access", query: { returnUrl: to.path } });
    }
    //login olan kullanıcı role sahipse sayfa açılır
    return next();
  } else {
    //requiresAuth istemiyorsa herhangib ir kontrol yapmıyoruz.
    next();
  }
});

export default router;
