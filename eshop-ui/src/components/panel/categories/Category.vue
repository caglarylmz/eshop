<template>
  <tr>
    <td>{{pCategory.name}}</td>
    <td class="text-center text-success" v-if="pCategory.status">Aktif</td>
    <td class="text-center text-danger" v-else>Pasif</td>

    <td class="text-center">
      <a
        tag="button"
        class="btn btn-sm btn-outline-info"
        @click="$router.push({name:'subcategory', params:{categoryId:pCategory.id}})"
      >
        Alt Kategoriler
        <span class="badge badge-info">{{pCategory.subCategories.length}}</span>
      </a>
    </td>
    <td class="text-center">
      <button
        class="btn btn-sm btn-warning mx-1"
        data-toggle="modal"
        data-target="#editCategory"
        @click="openEditDialog"
      >Düzenle</button>
      <button @click="openDeleteDialog" class="btn btn-sm btn-danger mx-1">Sil</button>
    </td>
    <!-- Edit Parent Category Modal -->
    <Dialog :header="editCategory.name +'-'+ editCategory.id" :visible.sync="editDialog">
      <div class="card-body">
        <div class="form-group row">
          <label for="name" class="col-sm-5 col-form-label">Kategori Adı</label>
          <div class="col-sm-7">
            <input v-model="editCategory.name" type="text" class="form-control" id="name" />
          </div>
        </div>
        <div class="form-group row">
          <div class="col-sm-5">Kategori Durumu</div>
          <div class="col-sm-7">
            <div class="form-check">
              <input
                v-model="editCategory.status"
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
          @click="closeEditDialog"
          class="p-button-secondary"
        />
        <Button label="Kaydet" icon="pi pi-check" @click="updateParentCategory" />
      </template>
    </Dialog>
    <!-- Edit Parent Category Modal -->
    <!-- Delete Parent Category Modal -->
    <Dialog :header="pCategory.name" :visible.sync="deleteDialog">
      <div class="card-body">
        <p>
          <span class="text-danger">
            <b>{{pCategory.name}}</b>
          </span> kategorisini silmek istediğinize eminmisiniz? Bu kategori tüm alt kategorileri ile birlikte silinecek
        </p>
      </div>
      <template #footer>
        <Button
          label="İptal"
          icon="pi pi-times"
          @click="closeDeleteDialog"
          class="p-button-secondary"
        />
        <Button label="Sil" icon="pi pi-check" class="bg-danger" @click="deleteParentCategory" />
      </template>
    </Dialog>
    <!-- Delte Parent Category Modal -->
  </tr>
</template>

<script>
import Dialog from "primevue/dialog";
import Button from "primevue/button";

export default {
  name: "category",
  components: {
    Dialog,
    Button
  },
  /* props: {
    pCategory: {
      type: Object,
      required: true
    }
  },*/
  props: ["pCategory"],
  data() {
    return {
      editCategory: [],
      editDialog: false,
      deleteDialog: false
    };
  },
  methods: {
    updateParentCategory() {
      this.$store.dispatch("category/updateParentCategory", this.editCategory);
      this.closeEditDialog();
    },
    openEditDialog() {
      this.editDialog = true;
    },
    closeEditDialog() {
      this.editDialog = false;
    },
    deleteParentCategory() {
      this.$store.dispatch("category/deleteParentCategory", this.pCategory.id);
      this.closeDeleteDialog();
    },
    openDeleteDialog() {
      this.deleteDialog = true;
    },
    closeDeleteDialog() {
      this.deleteDialog = false;
    }
  },
  created() {
    /*eğer pCategory kullanırsak düzenelemeler anında yansır. İptasl işe yaramaz. Bu yüzden edit verilerini yeni bir nesnede tutuyoruz */
    this.editCategory = {
      ...this.pCategory
    };
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>
