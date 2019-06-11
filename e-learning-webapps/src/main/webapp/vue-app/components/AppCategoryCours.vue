
<template>
  <v-dialog width="600px" v-model="dialog">
    <v-btn
      slot="activator"
      fab
      dark
      color="blue darken-3"
      small>
      <v-icon dark>add</v-icon>
    </v-btn>
            <notification v-bind:notifications="notifications"></notification>
    <v-card>
      <v-card-title>
        <h4>Add New Category</h4>
      </v-card-title>
      <v-card-text>
        <v-form ref="form" class="px-3">
          <div>
            <v-text-field
              v-model="category.nameCategory"
              label="libellé Categorie"
              prepend-icon="folder"
              :rules="inputRules" />
            <v-spacer />
            <v-btn
              small
              dark
              color="blue darken-3"
              class="mx-0 mt-3"
              @click="saveCategory">
              Ajouter
            </v-btn>
            <v-btn
              outline
              small
              color="blue darken-3"
              class="mx-0 mt-3"
              @click="cancel">
              Quitter
            </v-btn>
          </div>
        </v-form>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
    import axios from 'axios'
    import Notification from './notifications.vue';
    export default {
        data() {
            return{
               components:{
            'notification' : Notification
          },
                category:{
                  nameCategory:''
                },
                notifications:[],
                            dialog: false,
                                inputRules: [
                v => !!v || 'This field is required',
            v => v.length >= 3 || 'Minimum length is 3 characters'
        ]

                
                }
        },
        methods:{
            saveCategory() {
               if(!this.$refs.form.validate()) {
                  return false;
                     this.notifications.push({
                        type: 'error',
                        message: 'Category not created'
                    })
                }
                else{
                      axios.post('http://127.0.0.1:8080/portal/rest/category/add', this.category, {
                    headers : {
                        'Content-Type' : 'application/json'
                    }
              
                }).then((response) => {
                  this.notifications.push({
                        type: 'success',
                        message: 'Category created successfully'
                    });

                }, (response) => {
                    this.notifications.push({
                        type: 'error',
                        message: 'Category not created'
                    });

                });
                                    this.dialog=false
                }
                
            },
               cancel(){
      this.dialog=false;
     
      console.log(this.dialog);
    }
        },
      
            }
</script>

