<template>
  <div class="category">
    <div class="container-fluid">
      <Accordion>
        <AccordionTab :header="'Kategori ' + category.name" :active="true">
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
                  <th>Ana Kategorisi</th>
                  <td>
                    <a
                      type="button"
                      class="btn-info"
                      @click="$router.push({name:'maincategory', params:{categoryId:parent.id}})"
                    >{{parent.name}}</a>
                  </td>
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
          :header="'Kategoriye Ait Tipler (' + category.tips.length + ')'"
          :active="true"
        >
          <DataTable :value="category.tips" data-key="id">
            <Column field="name" header="Tip"></Column>
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
            <Column headerStyle="width: 12em" bodyStyle="text-align: center">
              <template #header>
                <Button
                  type="button"
                  icon="pi pi-plus-circle"
                  class="p-button-success p-button-rounded"
                  label="Tip Ekle"
                ></Button>
              </template>
              <template #body>
                <Button
                  type="button"
                  icon="pi pi-pencil"
                  class="p-button-warning"
                  style="margin-right: .5em"
                ></Button>
                <Button type="button" icon="pi pi-trash" class="p-button-danger"></Button>
              </template>
            </Column>
          </DataTable>
        </AccordionTab>
        <AccordionTab :header="'Kategoriye Ait İlanlar (' + category.adverts.length + ')'">
          <DataTable :value="category.adverts" data-key="id">
            <Column field="id" header="ID"></Column>
            <Column field="baslik" header="Başlık"></Column>
            <Column field="status" header="Durum" bodyStyle="text-align: center">
              <template #body="slotProps">
                <InputSwitch v-model="slotProps.data.status" />
              </template>
            </Column>
            <Column field="createdAt" header="Oluşturulma Zamanı" bodyStyle="text-align: center"></Column>
            <Column headerStyle="width: 12em" bodyStyle="text-align: center">
              <template #header></template>
              <template #body>
                <Button
                  type="button"
                  icon="pi pi-pencil"
                  class="p-button-warning"
                  style="margin-right: .5em"
                ></Button>
                <Button type="button" icon="pi pi-trash" class="p-button-danger"></Button>
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
  name: "category",
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
  props: ["categoryId"],
  data() {
    return {
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
    getParentCategory() {
      this.$store.dispatch(
        "category/getParentCategory",
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
    }
  },
  computed: {
    category() {
      return this.$store.getters["category/getCategory"];
    },
    parent() {
      return this.$store.getters["category/getParent"];
    }
  },
  created() {},
  mounted() {
    this.getCategory();
    this.getParentCategory();
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
