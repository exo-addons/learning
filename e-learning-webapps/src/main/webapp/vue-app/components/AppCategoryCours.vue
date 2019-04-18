
<template>
  <v-dialog width="600px">
    <v-btn
      slot="activator"
      fab
      dark
      color="blue darken-3"
      small>
      <v-icon dark>add</v-icon>
    </v-btn>
    <v-card>
      <v-card-title>
        <h4>Add New Category</h4>
      </v-card-title>
      <v-card-text>
        <v-form ref="form" class="px-3">
          <div v-if="!submitted">
            <v-text-field
              v-model="libelle"
              label="libellé Categorie"
              prepend-icon="folder"
              :rules="inputRules" />
            <v-spacer />
            <v-btn
              small
              dark
              color="blue darken-3"
              class="mx-0 mt-3"
              @click="submit">
              Ajouter
            </v-btn>
            <v-btn
              outline
              small
              color="blue darken-3"
              class="mx-0 mt-3"
              @click="cl">
              Quitter
            </v-btn>
          </div>
          <div v-else>
            <h4>You submitted successfully!</h4>
            <button class="btn btn-success" @click="newCategory">Add</button>
          </div>
        </v-form>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
    import http from "../connexionaxios/http-common.js"

    export default {
        data() {
            return{
                category:{
                    libelle:""
                },
            submitted:false
            }
        },
        methods:{
            saveCategory() {
                const data = {
                    nameCategory: this.category.libelle,
                };
                http
                    .post("/category/addCategory", data)
                    .then(response => {
                console.log(response.data);
            })
            .catch(e => {
                    console.log(e);
            });

                this.submitted = true;
            },
            newCustomer() {
                this.submitted = false;
                this.customer = {};
            }
        }
    };
</script>

