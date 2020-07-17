<template>
  <div class="sidebar">
    <nav id="sidebar" class="sidebar-wrapper">
      <div class="sidebar-content">
        <!-- sidebar-brand  -->
        <div class="sidebar-item sidebar-brand">
          <router-link to="/">
            <img class="img-responsive img-rounded" src="/img/logo.png" alt="User picture" />
          </router-link>
          <div id="close-sidebar">
            <i class="fas fa-times" @click="toggleSidebar"></i>
          </div>
        </div>
        <!-- sidebar-header  -->
        <div class="sidebar-item sidebar-header d-flex flex-nowrap">
          <div class="user-pic">
            <img class="img-responsive img-rounded" src="/img/profile.png" alt="User picture" />
          </div>
          <div class="user-info">
            <span class="user-name">
              <strong>{{currentUser.name}}</strong>
            </span>
            <span class="user-name">
              <span>{{currentUser.email}}</span>
            </span>
            <ul>
              <li v-for="(role,index) in currentUser.roles" :key="index">
                <span class="user-status">{{role}}</span>
              </li>
            </ul>
          </div>
        </div>
        <!-- sidebar-search  -->
        <div class="sidebar-item sidebar-search">
          <div>
            <div class="input-group">
              <input type="text" class="form-control search-menu" placeholder="Search..." />
              <div class="input-group-append">
                <span class="input-group-text">
                  <i class="fa fa-search" aria-hidden="true"></i>
                </span>
              </div>
            </div>
          </div>
        </div>
        <!-- sidebar-menu  -->
        <div class="sidebar-item sidebar-menu">
          <ul>
            <li class="header-menu">
              <span>Menu</span>
            </li>
            <li>
              <router-link to="/panel">
                <i class="fa fa-chart-line"></i>
                <span>Overview</span>
              </router-link>
            </li>
            <li>
              <router-link to="/panel/categories" v-if="isAdmin">
                <i class="fa fa-shopping-cart"></i>
                <span>Kategori Yönetimi</span>
              </router-link>
            </li>
            <li>
              <router-link to="/panel/users">
                <i class="fa fa-shopping-cart"></i>
                <span>Kullanıcı Yönetimi</span>
              </router-link>
            </li>
            <li>
              <router-link to="/panel/adverts">
                <i class="fab fa-amazon"></i>
                <span>İlan Yönetimi</span>
              </router-link>
            </li>
            <li>
              <router-link to="/panel/myadverts">
                <i class="fab fa-amazon"></i>
                <span>İlanlarım</span>
              </router-link>
            </li>
            <li>
              <router-link to="/panel/messages">
                <i class="fa fa-shopping-cart"></i>
                <span>Mesajlarım</span>
              </router-link>
            </li>
            <li>
              <router-link to="/panel/profile">
                <i class="fa fa-user"></i>
                <span>Profile</span>
              </router-link>
            </li>
            <li>
              <a href="#" @click="logOut">
                <i class="fa fa-power-off"></i>
                <span>Logout</span>
              </a>
            </li>
          </ul>
        </div>
        <!-- sidebar-menu  -->
      </div>
      <!-- sidebar-footer  -->
    </nav>
  </div>
</template>
<script src="./main.js" type="text/javascript"></script>
<script>
export default {
  name: "Sidebar",
  props: {},
  methods: {
    toggleSidebar() {
      $(".page-wrapper").toggleClass("toggled");
    },
    logOut() {
      this.$store.dispatch("auth/logout");
      this.$router.push("/home");
    }
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    isAdmin() {
      return this.currentUser.roles.includes("ADMIN");
    }
  },
  created() {},
  mounted() {
    if (!this.currentUser) {
      this.$router.push("/login");
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.router-link-exact-active {
  background-color: #3f51b5;
}
.router-link-exact-active > span {
  color: whitesmoke;
}
.router-link-exact-active > i {
  color: whitesmoke;
}
</style>
