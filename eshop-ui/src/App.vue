<template>
  <div id="app">
    <Toast position="bottomright"/>
    <router-view />
  </div>
</template>


//Jquery $ çalışması için ekliyoruz
<script src="./main.js" type="text/javascript"></script>
<script>
import Toast from "primevue/toast";

export default {
  components: {
    Toast
  },

  data: () => ({
    authenticated: false
  }),
  methods: {},
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  created() {
    this.$store.dispatch("category/getParents");
  }
};
</script>

<style lang="scss">
</style>