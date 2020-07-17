<template>
  <div class="panel">
    <div class="page-wrapper default-theme sidebar-bg bg1 toggled">
      <a href="#" class="btn btn-sm-btn-dark" id="show-sidebar" @click="toggleSidebar">
        <i class="fas fa-bars"></i>
      </a>
      <Sidebar></Sidebar>
      <!-- page-content  -->
      <main class="page-content">
        <router-view />
      </main>
      <!-- page-content" -->
    </div>
    <!-- page-wrapper -->
  </div>
</template>

//Jquery $ çalışması için ekliyoruz
<script src="./main.js" type="text/javascript"></script>
<script>
// @ is an alias to /src
import Sidebar from "@/components/panel/Sidebar.vue";
//import BreadCrumb from '@/components/panel/BreadCrumb'

export default {
  name: "Panel",
  data() {
    return {};
  },
  components: {
    Sidebar
    //BreadCrumb
  },
  methods: {
    toggleSidebar() {
      $(".page-wrapper").toggleClass("toggled");
    }
  },
  computed: {},
  created() {},
  mounted() {}
};
</script>
<style scoped lang="scss">
</style>
