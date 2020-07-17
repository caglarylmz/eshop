<template>
  <div class="hello">
    <!--header-top-->
    <Top></Top>
    <!--/header-top-->
    <nav class="navbar navbar-expand-lg border-bottom navbar-light">
      <div class="container">
        <router-link to="/" class="nav-link">
          <img src="/img/logo.png" width="100" alt />
        </router-link>
        <button
          class="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarText"
          aria-controls="navbarText"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="container-fluid">
          <div class="col-lg-2"></div>
          <div class="col-lg-6">
            <Searchbox></Searchbox>
          </div>
          <div class="col-12 col-lg-4 text-center text-lg-right mt-3 mt-lg-0">
            <ul class="list-inline mb-0">
              <li v-if="!currentUser" class="list-inline-item">
                <router-link to="/login">
                  <i class="btn btn-outline-primary btn-sm btn-round">
                    <span>Giriş Yap</span>
                  </i>
                </router-link>
              </li>
              <li v-else class="list-inline-item">
                <div class="dropdown">
                  <button
                    class="btn btn-outline-primary btn-sm dropdown-toggle btn-circle"
                    type="button"
                    id="dropdownMenu"
                    data-toggle="dropdown"
                    aria-expanded="false"
                  >
                    <i class="fas fa-user"></i>
                  </button>
                  <div v-if="showPanel" class="dropdown-menu" aria-labelledby="dropdownMenu2">
                    <router-link to="/panel/" class="dropdown-item">Panel</router-link>
                    <router-link to="/panel/profile" class="dropdown-item">Profil</router-link>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href @click.prevent="logOut">
                      <i class="fa fa-power-off"></i>
                    </a>
                  </div>
              
                </div>
              </li>

              <li class="list-inline-item">
                <a href class="btn btn-outline-success btn-sm btn-round">İlan Ver</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
import Top from "./navbar/Top.vue";
import Searchbox from "./navbar/Searcbox.vue";

export default {
  name: "Navbar",
  components: {
    Top,
    Searchbox
  },
  props: {},
  data() {
    return {};
  },
  methods: {
    logOut() {
      this.$store.dispatch("auth/logout");
      this.$router.push("/home");
    }
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    showPanel() {
      if (this.currentUser && this.currentUser.roles) {
        //return this.currentUser.roles.includes("ADMIN");
        return true;
      }
      return false;
    }
  }
};
</script>

<style scoped lang="css">
/*header Top*/
.header-top ul li {
  border-left: 1.2px solid #e0e0e0;
  padding: 4px 10px 4px;
  margin: 0px !important;
}
.header-top ul li:last-child {
  border-right: 1.2px solid #e0e0e0;
}

/*user profile*/
.top-user-img {
  width: 21px;
  height: 21px;
  border-radius: 1px solid;
}

/*nth-of-type(1) : Aynı etikete sahip elemanları seçmek için kullamılır
i2 navbar olduğu için biz 1. 'yi seçiyoruz. Onun içinde drowdown-menu elemanını
seçelim*/
header .navbar:nth-of-type(1).dropdown-menu {
  top: 66px;
}
header .navbar:nth-of-type(1).dropdown-item {
  padding: 5px 20px;
  font-size: 13px;
}
.top-user .dropdown-menu {
  border-radius: 0 0 12px 2px !important;
  border-color: #6d7379;
  border-style: solid none none;
  border-width: 2px medium medium;
  box-shadow: 0 0 23px rgba(69, 102, 173, 0.2);
}
.top-user .dropdown-menu i {
  font-size: 12px;
  width: 19px;
}

.bd-placeholder-img {
  font-size: 1.125rem;
  text-anchor: middle;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

.btn-round {
  border-radius: 8px;
}

.btn-circle {
  width: 35px;
  height: 35px;
  padding: 6px 0px;
  border-radius: 25px;
  text-align: center;
  font-size: 12px;
  line-height: 1.42857;
}

.dropdown-toggle::after {
  content: none;
}
.dropdown-menu {
  position: absolute;
  background-color: white;
  right: 0px;
}
@media (min-width: 768px) {
  .bd-placeholder-img-lg {
    font-size: 3.5rem;
  }
}
@media (max-width: 990px) {
  .header-top {
    display: none;
  }
}
</style>
