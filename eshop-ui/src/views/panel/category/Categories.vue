<template>
  <div class="categories">
    <DataTable :value="parents" :expandedRows.sync="expandedRows" data-key="id" filters="filters">
      <template #header>
        <div class="table-header">
          <h5 class="p-m-0">Kategoriler</h5>
          <span class="p-input-icon-left">
            <i class="pi pi-search" />
            <InputText v-model="filters['global']" placeholder="Search..." />
          </span>
        </div>
      </template>
      <Column :expander="true" headerStyle="width: 3rem" />
      <Column field="name" header="Kategori" sortable></Column>
      <Column field="status" header="Status" sortable>
        <template #body="slotProps">
          <span>{{slotProps.data.status}}</span>
        </template>
      </Column>
      <template #expansion="slotProps">
        <div class="category-subtable">
          <h5>Alt Kategoriler</h5>
          <DataTable :value="slotProps.data.subCategories">
            <Column field="name" header="Kategori" sortable></Column>
            <Column field="status" header="Status" sortable>
              <template #body="slotProps">
                <span>{{slotProps.data.status}}</span>
              </template>
            </Column>
            <Column headerStyle="width:4rem">
              <template #body>
                <Button icon="pi pi-search" />
              </template>
            </Column>
          </DataTable>
        </div>
      </template>
    </DataTable>

    <!--
  
    <div class="container-fluid">
      <div class="card">
        <div class="card-header">
          <div class="row">
            <div class="col-8">
              <h5 class="card-title">Ana Kategoriler</h5>
            </div>
            <div class="col-4">
              <button
                class="btn btn-sm btn-success float-right"
                @click="openAddDialog"
              >Ana Kategori Ekle</button>
            </div>
          </div>
        </div>
        <div class="card-body">
          <table class="table table-hover table-stripped border-dark border-shadow-lg">
            <thead class="thead-dark">
              <tr>
                <th>Kategori</th>
                <th class="text-center">Durum</th>
                <th class="text-center">Alt Kategori</th>
                <th class="text-center">İşlem</th>
              </tr>
            </thead>
            <tbody v-for="parent in parents" :key="parent.id">
              <Category :pCategory="parent" />
            </tbody>
          </table>
        </div>
      </div>
    </div>-->
    <!-- Add Parent Category Modal -->
    <Dialog header="Ana Kategori Ekle" :visible.sync="display">
      <div class="card-body">
        <div class="form-group row">
          <label for="name" class="col-sm-5 col-form-label">Kategori Adı</label>
          <div class="col-sm-7">
            <input v-model="parentCategory.name" type="text" class="form-control" id="name" />
          </div>
        </div>
        <div class="form-group row">
          <div class="col-sm-5">Kategori Durumu</div>
          <div class="col-sm-7">
            <div class="form-check">
              <input
                v-model="parentCategory.status"
                class="form-check-input"
                type="checkbox"
                id="status"
              />
            </div>
          </div>
        </div>
      </div>
      <template #footer>
        <Button
          label="İptal"
          icon="pi pi-times"
          @click="closeAddDialog"
          class="p-button-secondary"
        />
        <Button label="Kaydet" icon="pi pi-check" @click="addParentCategory" />
      </template>
    </Dialog>
    <!-- Add Parent Category Modal -->
  </div>
</template>

<script>
//import CategoryService from "@/services/category.service";
//import Category from "@/components/panel/categories/Category";
import Dialog from "primevue/dialog";
import Button from "primevue/button";
import DataTable from "primevue/datatable";
import Column from "primevue/column";
import InputText from "primevue/inputtext"

export default {
  name: "categories",
  components: {
    //Category,
    Dialog,
    Button,
    DataTable,
    Column,
    InputText
  },
  props: {},
  data() {
    return {
      display: false,
      expandedRows: [],
      filters: {},

      parentCategory: {
        name: "",
        status: true,
        subCategories: []
      }
    };
  },
  methods: {
    addParentCategory() {
      this.$store.dispatch("category/addParentCategory", this.parentCategory);
      this.display = false;
    },
    openAddDialog() {
      this.display = true;
    },
    closeAddDialog() {
      this.display = false;
    },
    onRowExpand(event) {
      this.$toast.add({
        severity: "info",
        summary: "Product Expanded",
        detail: event.data.name,
        life: 3000
      });
    },
    onRowCollapse(event) {
      this.$toast.add({
        severity: "success",
        summary: "Product Collapsed",
        detail: event.data.name,
        life: 3000
      });
    }
  },
  computed: {
    parents() {
      return this.$store.getters["category/getParentCategories"];
    }
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
