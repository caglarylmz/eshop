<template>
  <div class="searchbox">
    <form>
      <div class="input-group">
        <input type="text" class="form-control" placeholder="İlan ara..." />
        <div class="input-group-prepend">
          <button class="btn btn-danger rounded-right">
            <i class="fas fa-search"></i>
          </button>
        </div>
      </div>
    </form>
  </div>
</template>
<script>
export default {
  name: "Searchbox",
  props: {},
  methods: {},
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  }
};
</script>

<style scoped lang="css">
.searchbox input:first-child {
  height: 40px;
  border-right: none;
  border-color: #e8e8e8;
}
</style>
