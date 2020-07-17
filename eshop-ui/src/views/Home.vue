<template>
  <div class="home">
    <Navbar />
    <Sidebar :visible.sync="visibleLeft" :baseZIndex="0" :autoZIndex=true class="p-sidebar-sm">
      <h1 style="fontWeight:normal">Left Sidebar</h1>
      <Button
        type="button"
        @click="visibleLeft = false"
        label="Save"
        class="p-button-success"
        style="margin-right:.25em"
      />
      <Button type="button" @click="visibleLeft = false" label="Cancel" class="p-button-secondary" />
    </Sidebar>
    <router-view />
  </div>
</template>

<script>
// @ is an alias to /src
import Navbar from "@/components/Navbar.vue";
import Sidebar from "primevue/sidebar";
export default {
  name: "home",
  components: {
    Navbar,
    Sidebar
  },
  data() {
    return {
      content: "Merhaba",
      visibleLeft:true
    };
  },
  mounted() {}
};
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>