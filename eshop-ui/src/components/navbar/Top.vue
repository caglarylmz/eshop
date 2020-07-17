<template>
  <div class="top">
    <!--header-top-->
    <div class="header-top border-bottom" v-if="!currentUser">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-12 text-center col-lg-8 text-lg-left">
            <p class="mb-0">
              <span class="text-danger">AlSat</span> En iyi Satış Sitesi!
            </p>
          </div>
          <div class="col-12 text-center col-lg-4 text-lg-right">
            <ul class="list-inline mb-0">
              <li class="list-inline-item">
                <a href="#">
                  <i class="fas fa-mobile-alt mr-1"></i>(555)8317824
                </a>
              </li>
              <li class="list-inline-item">
                <a href="#">
                  <i class="fas fa-headphones mr-1"></i>Support
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <!--/header-top-->
  </div>
</template>
<script>
export default {
  name: "Top",
  props: {
  },
  methods: {},
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
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
</style>
