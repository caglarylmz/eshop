<template>
  <div class="container-fluid text-right">
    <Breadcrumb :home="home" :model="crumbs" />
  </div>
</template>
<script>
import Breadcrumb from "primevue/breadcrumb";

export default {
  components: {
    Breadcrumb
  },
  data() {
    return {
      home: { icon: "pi pi-home", to: "/" }
    };
  },
  computed: {
    crumbs: function() {
      console.log(this.$route.path);
      let pathArray = this.$route.path.split("/");
      pathArray.shift();
      console.log(pathArray);
      let breads = [];
      pathArray.forEach(element => {
        let crumb = { label: element, to: element };
        breads.push(crumb);
      });
      console.log(breads);
      return breads;
    }
  }
};
</script>
<style scoped>
</style>