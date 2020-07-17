<template>
  <div class="maincategory">
    <div class="container-fluid">
      <Accordion>
        <AccordionTab :header="'Ana Kategori ' + category.name" :active="true">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">{{category.name}}</h5>
              <table class="table">
                <tr>
                  <th>Kategori Adı</th>
                  <td>{{category.name}}</td>
                </tr>
                <tr>
                  <th>Kategori Durumu</th>
                  <td>{{category.status ?"Aktif" : "Pasif"}}</td>
                </tr>
                <tr>
                  <th>Alt Kategori Sayısı</th>
                  <td>{{category.subCategories.length}}</td>
                </tr>
              </table>
            </div>
            <div class="card-footer text-right">
              <Button
                type="button"
                icon="pi pi-pencil"
                class="p-button-warning"
                label="Kategori Düzenle"
                @click="openEditModal"
              ></Button>
            </div>
          </div>
        </AccordionTab>
        <AccordionTab
          :header="'Alt Kategoriler (' + category.subCategories.length + ')'"
          :active="true"
        >
          <DataTable :value="category.subCategories" data-key="id">
            <Column field="name" header="Kategori"></Column>
            <Column field="status" header="Durum" bodyStyle="text-align: center">
              <template #body="slotProps">
                <InputSwitch v-model="slotProps.data.status" />
              </template>
            </Column>
            <Column bodyStyle="text-align: center">
              <template #body>
                <Button type="button" icon="pi pi-pencil" class="p-button-info" label="İlanlar"></Button>
              </template>
            </Column>
            <Column headerStyle="width: 15em" bodyStyle="text-align: center">
              <template #header>
                <Button
                  type="button"
                  icon="pi pi-plus-circle"
                  class="p-button-success p-button-rounded"
                  label="Alt Kategori Ekle"
                  @click="openAddSubCatModal"
                ></Button>
              </template>
              <template #body="slotProps">
                <Button
                  type="button"
                  icon="pi pi-pencil"
                  class="p-button-warning"
                  style="margin-right: .5em"
                  @click="$router.push({name:'subcategory', params:{categoryId:slotProps.data.id}})"
                ></Button>
                <Button
                  type="button"
                  icon="pi pi-trash"
                  class="p-button-danger"
                  @click="openDeleteCategoryModal(slotProps.data.id)"
                ></Button>
              </template>
            </Column>
          </DataTable>
        </AccordionTab>
      </Accordion>
      <Dialog header="Kategori Düzenle " :visible.sync="edit">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">{{category.name}}</h5>
            <table class="table">
              <tr>
                <th>Kategori Adı</th>
                <td>
                  <InputText type="text" v-model="category.name" />
                </td>
              </tr>
              <tr>
                <th>Durumu</th>
                <td>
                  <InputSwitch v-model="category.status" />
                </td>
              </tr>
            </table>
          </div>
        </div>
        <template #footer>
          <Button label="Kaydet" icon="pi pi-check" @click="updateCategory" />
          <Button
            label="Vazgeç"
            icon="pi pi-times"
            class="p-button-secondary"
            @click="closeEditModal"
          />
        </template>
      </Dialog>
      <!--Alt Kategori Ekle-->
      <Dialog header="Alt Kategori Ekle " :visible.sync="controladdSubModal">
        <div class="card">
          <div class="card-body">
            <table class="table">
              <tr>
                <th>Kategori Adı</th>
                <td>
                  <InputText type="text" v-model="subCategory.name" />
                </td>
              </tr>
            </table>
          </div>
        </div>
        <template #footer>
          <Button label="Kaydet" icon="pi pi-check" @click="addSubCategory" />
          <Button
            type="submit"
            label="Vazgeç"
            icon="pi pi-times"
            class="p-button-secondary"
            @click="closeAddSubCatModal"
          />
        </template>
      </Dialog>
      <!--Alt Kategori Ekle-->
      <!--Delete Category Dialog-->
      <Dialog header="Kategori Sil" :visible.sync="controlDeleteCategoryModal">
        {{subId}} id'li kategoriyi silmek istyorsunuz. Emin misiniz?
        <template #footer>
          <Button label="Sil" icon="pi pi-check" @click="deleteCategory()" />
          <Button
            label="Vazgeç"
            icon="pi pi-times"
            class="p-button-secondary"
            @click="closeDeleteCategoryModal"
          />
        </template>
      </Dialog>
      <!--Delete Category Dialog-->
    </div>
  </div>
</template>

<script>
import CategoryService from "@/services/category.service";
import Accordion from "primevue/accordion";
import AccordionTab from "primevue/accordiontab";
import InputSwitch from "primevue/inputswitch";
import DataTable from "primevue/datatable";
import Column from "primevue/column";
import Button from "primevue/button";
import Dialog from "primevue/dialog";
import InputText from "primevue/inputtext";
export default {
  name: "maincategory",
  components: {
    Accordion,
    AccordionTab,
    InputSwitch,
    DataTable,
    Column,
    Button,
    Dialog,
    InputText
  },
  props: {
    categoryId: { required: true }
  },
  data() {
    return {
      subCategory: [],
      subId: "",
      controladdSubModal: false,
      controlDeleteCategoryModal: false,
      edit: false,
      loading: false,
      message: ""
    };
  },
  methods: {
    getCategory() {
      this.$store.dispatch(
        "category/getCategory",
        this.$route.params.categoryId
      );
    },
    updateCategory() {
      CategoryService.updateCategory(
        this.category,
        this.$route.params.categoryId
      ).then(res => {
        if (res.data.success) {
          this.$toast.add({
            severity: "success",
            summary: "Update",
            detail: res.data.message,
            life: 3000
          });
        } else {
          this.$toast.add({
            severity: "error",
            summary: "Update",
            detail: res.data.message,
            life: 3000
          });
        }
        this.getCategory();
      });
      this.edit = false;
    },
    openEditModal() {
      this.edit = true;
    },
    closeEditModal() {
      this.edit = false;
    },
    addSubCategory() {
      CategoryService.addSubCategory(this.subCategory, this.category.id).then(
        res => {
          if (res.data.success) {
            this.$toast.add({
              severity: "success",
              summary: "Ekle",
              detail: res.data.message,
              life: 3000
            });
          } else {
            this.$toast.add({
              severity: "error",
              summary: "Ekle",
              detail: res.data.message,
              life: 3000
            });
          }
        }
      );
      this.getCategory();
      this.controladdSubModal = false;
    },
    openAddSubCatModal() {
      this.controladdSubModal = true;
    },
    closeAddSubCatModal() {
      this.getSubCategories;
      this.controladdSubModal = false;
    },
    deleteCategory() {
      CategoryService.deleteCategory(this.subId).then(res => {
        if (res.data.success) {
          this.$toast.add({
            severity: "success",
            summary: "Sil",
            detail: res.data.message,
            life: 3000
          });
        } else {
          this.$toast.add({
            severity: "error",
            summary: "Sil",
            detail: res.data.message,
            life: 3000
          });
        }
      });
      this.getCategory();
      this.$forceUpdate();
      this.closeDeleteCategoryModal();
    },
    openDeleteCategoryModal(subId) {
      this.subId = subId;
      this.controlDeleteCategoryModal = true;
    },
    closeDeleteCategoryModal() {
      this.controlDeleteCategoryModal = false;
    }
  },
  computed: {
    category() {
      return this.$store.state.category.cat;
    }
  },
  watch: {
    
  },
  created() {
    this.getCategory();
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
