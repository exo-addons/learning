<template>
  <v-app
    id="vuetify_webpack_sample_Portlet"
    color="transaprent"
    class="VuetifyApp"
    flat>
    <v-btn @click="createTuto">{{ $t('addon.elearning.tutorial.create') }}</v-btn>
    <main>
      <v-row>
        <v-col
          align-self="stretch"
          cols="12"
          sm="3"
          md="2"
          v-for="tuto in tutoList"
          :key="tuto.id">
          <v-card
            id="vuetify_webpack_sample"
            max-width="344">
            <v-list-item 
              @click="display(tuto.id)">
              <v-list-item-avatar v-if="tuto.status== 'DRAFT'" color="orange" />
              <v-list-item-avatar v-else-if="tuto.status== 'PUBLISHED'" color="green" />
              <v-list-item-avatar v-else color="grey" />
              <v-list-item-content>
                <v-list-item-title class="headline">{{ tuto.title }}</v-list-item-title>
                <v-list-item-subtitle>{{ tuto.status }}</v-list-item-subtitle>
                <v-list-item-subtitle>By {{ tuto.author }}</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <v-card-text>
              <div>
                {{ tuto.description }}
              </div>              
            </v-card-text>
          
            <v-card-actions>
              <v-btn
                text
                color="deep-purple accent-4"
                @click="update(tuto.id)">
                {{ $t('addon.elearning.tutorial.update') }}
              </v-btn>
              <v-btn
                text
                color="deep-purple accent-4"
                @click="prepareDelete(tuto.id)">
                {{ $t('addon.elearning.tutorial.delete') }}
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </main>
    <template>
      <v-row justify="center">
        <v-dialog
          v-model="confirmDialog"
          persistent
          max-width="290">
          <v-card>
            <v-card-title class="headline">
              Confirm Delete
            </v-card-title>
            <v-card-text><h4>Are you sure you want to delete this Tutorial? </h4></v-card-text>
            <v-card-text><h4>This action <b>Cannot</b> be <b>Undone!</b></h4></v-card-text>
            <v-card-text><h5>Press <i>"Cancel"</i> to go back or <i>"Confirm"</i> to proceed</h5></v-card-text>
            <v-card-actions>
              <v-spacer />
              <v-btn
                color="red darken-1"
                text
                @click="confirmDialog = false">
                Cancel
              </v-btn>
              <v-btn
                color="green darken-1"
                text
                @click="deleteTuto()">
                Confirm
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>
    </template>
    <template>
      <div class="text-center">
        <v-snackbar
          v-model="successBar"
          timeout="2000"
          :color="this.color">
          {{ text }}
          <template v-slot:action="{ attrs }">
            <v-btn
              color="black"
              text
              v-bind="attrs"
              @click="successBar = false">
              Dismiss
            </v-btn>
          </template>
        </v-snackbar>
      </div>
    </template>   
  </v-app>
</template>

<script>
import { tutorialsApp } from '../main';
export default {
  
  data() {
    return {
      confirmDialog: false,
      successBar: false,
      text: '',
      color: 'success',
      deleteId: null,
      tutoList: [],
      errors: [],
    };
  },

  mounted() {
    this.getTutos();
    tutorialsApp.$on('createTuto', () => {
      this.successBar=true;
      this.text='Tutorial successfully added!';
      this.getTutos();
    });
    tutorialsApp.$on('tutoUpdated', () => {
      this.successBar=true;
      this.text='Tutorial successfully updated!';
      this.getTutos();
    });

  },

  methods: {
    getTutos() {
      fetch('/portal/rest/tuto/getAllTutos')
        .then((response) => response.json())
        .then((data) => (this.tutoList = data))
        .catch((e) => this.errors.push(e));
    },
    prepareDelete(id){
      this.deleteId=id;
      this.confirmDialog=true;
    },
    deleteTuto() {
      return fetch(`/portal/rest/tuto/deleteTuto/${this.deleteId}`, {
        method: 'DELETE'
      })
        .then(() => {this.getTutos();
          this.confirmDialog=false;
          this.successBar=true;
          this.text='Tutorial successfully deleted!';})
        .catch((e) => this.errors.push(e));
    },
    update(id){
      tutorialsApp.$emit('updateTuto', id);
    },
    display(id){
      tutorialsApp.$emit('displayTuto', id);
    },
    createTuto(){
      tutorialsApp.$emit('addTuto');
    }
  }  
};
</script>