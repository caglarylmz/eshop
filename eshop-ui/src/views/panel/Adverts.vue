<template>
  <div class="adverts">
    <div class="container">
      <Card style="margin-bottom: 2em;">
        <template slot="title">Yeni İlan</template>
        <template slot="content">
          <div class="form-group">
            <input
              type="text"
              class="form-control"
              placeholder="İlan Başlığı"
              id="title"
              v-model="advert.title"
            />
            <small
              id="titleHelp"
              class="form-text text-muted"
            >İlanınızı tanımlayacak bir başlık girin.</small>
          </div>
          <div class="form-group">
            <input
              type="text"
              class="form-control"
              placeholder="İlanınız için açıklama girin"
              id="description"
              v-model="advert.description"
            />
          </div>
          <div class="form-group">
            <input
              type="number"
              class="form-control"
              id="price"
              placeholder="Fiyat (₺)"
              v-model="advert.price"
            />
          </div>
        </template>
        <template slot="footer">
          <button @click="saveData" class="btn btn-primary">Save Advert</button>
        </template>
      </Card>
      <!--/form-->

      <DataTable :value="advertsData">
        <Column field="title" header="Title"></Column>
        <Column field="description" header="Description"></Column>
        <Column field="price" header="Price"></Column>
        <Column field="" header="Modify"></Column>
      </DataTable>
      <!--table-->
      <h3>Advert Table</h3>
      <div class="card">
        <div class="car-body">
          <table class="table table-bordered">
            <thead class="thead-dark">
              <tr>
                <th scope="col">Titile</th>
                <th scope="col">Description</th>
                <th scope="col">Price</th>
                <th scope="col">Modify</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="advert in adverts" :key="advert.id">
                <td>{{advert.data().title}}</td>
                <td>{{advert.data().description}}</td>
                <td>{{advert.data().price}}</td>
                <td class="text-left">
                  <button @click="openEditDialog(advert)" class="btn btn-info btn-sm mx-1">Edit</button>
                  <button
                    @click="openDeleteDialog(advert.id)"
                    class="btn btn-danger btn-sm mx-1"
                  >Delete</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <!--Edit Dialog-->
    <Dialog
      position="top"
      header="Düzenle"
      :visible.sync="editDialogDisplay"
      :style="{width: '50vw'}"
    >
      <Card style="margin-bottom: 2em;">
        <template slot="content">
          <div class="form-group">
            <input
              type="text"
              class="form-control"
              placeholder="İlan Başlığı"
              id="title"
              v-model="advert.title"
            />
            <small
              id="titleHelp"
              class="form-text text-muted"
            >İlanınızı tanımlayacak bir başlık girin.</small>
          </div>
          <div class="form-group">
            <input
              type="text"
              class="form-control"
              placeholder="İlanınız için açıklama girin"
              id="description"
              v-model="advert.description"
            />
          </div>
          <div class="form-group">
            <input
              type="number"
              class="form-control"
              id="price"
              placeholder="Fiyat (₺)"
              v-model="advert.price"
            />
          </div>
        </template>
      </Card>
      <template #footer>
        <Button label="Sakla" icon="pi pi-check" @click="updateAdvert" />
        <Button
          label="Vazgeç"
          icon="pi pi-times"
          @click="closeEditDialog"
          class="p-button-secondary"
        />
      </template>
    </Dialog>
    <!--Delete Dialog-->
    <Dialog
      position="top"
      header="Delete"
      :visible.sync="deleteDialogDisplay"
      :style="{width: '20vw'}"
    >
      Emin misiniz?
      <template #footer>
        <Button label="Evet" icon="pi pi-check" @click="deleteAdvert" />
        <Button
          label="Hayır"
          icon="pi pi-times"
          @click="closeDeleteDialog"
          class="p-button-secondary"
        />
      </template>
    </Dialog>
  </div>
</template>

<script>
import Dialog from "primevue/dialog";
import Button from "primevue/button";
import Card from "primevue/card";
import DataTable from "primevue/datatable";
import Column from "primevue/column";

export default {
  name: "adverts",
  components: {
    Dialog,
    Button,
    Card,
    DataTable,
    Column
  },
  props: {},
  data() {
    return {
      editDialogDisplay: false,
      deleteDialogDisplay: false,
      activeAdvertId: null,
      adverts: [],
      advertsData:[],
      editAdvert: {
        title: null,
        description: null,
        price: null
      },
      advert: {
        title: null,
        description: null,
        price: null
      }
    };
  },
  methods: {
    openEditDialog(advert) {
      this.editDialogDisplay = true;
      this.advert = advert.data();
      this.activeAdvertId = advert.id;
    },
    closeEditDialog() {
      this.editDialogDisplay = false;
      this.advert = {
        title: null,
        description: null,
        price: null
      };
    },
    updateAdvert() {
     
    },
    openDeleteDialog(advertId) {
      this.deleteDialogDisplay = true;
      this.activeAdvertId = advertId;
    },
    closeDeleteDialog() {
      this.activeAdvertId = false;
    },
    deleteAdvert() {
     
    
    },
    saveData() {
     
    },
    readData() {
     
    },
    resetData() {
      Object.assign(this.$data, this.$options.data.apply(this));
    }
  },
  created() {
 
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>
