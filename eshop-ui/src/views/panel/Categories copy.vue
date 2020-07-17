<template>
  <div class="categories">
    <div class="container-fluid">
      <div class="col-12">
        <div class="card">
          <div class="card-header">
            <div class="card-tools">
              Alt Kategoriler
              <div class="input-group input-group-sm float-right" style="width: 150px;">
                <input
                  type="text"
                  name="table_search"
                  class="form-control float-right"
                  placeholder="Search"
                />
                <div class="input-group-append">
                  <button type="submit" class="btn btn-default">
                    <i class="fas fa-search"></i>
                  </button>
                </div>
              </div>
            </div>
          </div>
          <!-- /.card-header -->
          <div class="card-body table-responsive p-0">
            <table class="table table-hover text-nowrap">
              <thead>
                <tr>
                  <th>Kategori</th>
                  <th>Durum</th>
                  <th></th>
                  <th></th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="category in parents" :key="category.id">
                  <td class="table-cell">{{category.name}}</td>
                  <td class="table-cell">
                    <InputSwitch v-model="category.status" />
                  </td>
                  <td class="table-cell">
                    <router-link :to="{name:'subcategories', params:{categoryId:category.id}}">
                      <i class="fa fa-chart-line mr-1"></i>
                      <span>Alt Kategoriler</span>
                    </router-link>
                  </td>
                  <td class="table-cell">
                    <router-link :to="{name:'advertsOfCategory', params:{id:category.id}}">
                      <i class="fa fa-chart-line mr-1"></i>
                      <span>İlanlar</span>
                    </router-link>
                  </td>
                  <td class="table-cell">
                    <a href="#">
                      <i class="fas fa-edit mr-1 text-info"></i>
                    </a>
                    <a href="#">
                      <i class="fas fa-minus-circle mr-1 text-danger"></i>
                    </a>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <!-- /.card-body -->
        </div>
        <!-- /.card -->
      </div>
    </div>
  </div>
</template>

<script>
import InputSwitch from "primevue/inputswitch";

export default {
  name: "categories",
  components: {
    InputSwitch
  },
  props: {},
  data() {
    return {
      loading: false,
      message: ""
    };
  },
  methods: {},
  computed: {
    parents() {
      return this.$store.state.category.parentCategories;
    }
  },
  beforeCreate() {
    this.$store.dispatch("category/getParents");
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.table-cell {
  vertical-align: middle;
  display: table-cell;
}
</style>
