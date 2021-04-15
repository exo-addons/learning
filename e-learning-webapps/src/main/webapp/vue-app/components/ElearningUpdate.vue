<template>
  <div v-if="isHidden">
    <div>
      <v-btn @click="hideForm" v-if="isHidden">Collapse </v-btn>
    </div>
    <div>
      <h1>Updating Tuto</h1>
    </div>
    <div>
      <v-form @submit="tutoPost" method="post">
        <div class="form-group">
          <label for="title">Title</label>
          <input
            id="title" 
            type="text" 
            name="title"
            v-model="tuto.title">
        </div>
        <div class="form-group">
          <label for="description">Description</label>
          <input
            id="description" 
            type="text" 
            name="description"
            v-model="tuto.description">
        </div>
        <div class="form-group">
          <label for="description">Author</label>
          <input
            id="description" 
            type="text" 
            name="author"
            v-model="tuto.author">
        </div>
        <div class="form-group">
          <label for="status">Status</label>
          <input
            id="status" 
            type="text" 
            name="status"
            v-model="tuto.status">
        </div>
        <div class="form-group">
          <v-btn class="btn_exo" @click="tutoPatch">Udpate</v-btn>
          <v-btn class="btn_exo" @click="clearForm">Clear</v-btn>
        </div>
      </v-form>
    </div>
  </div>
</template>


<script>
import { tutorialsApp } from '../main';
export default {
  
  name: 'PostTuto',
  data() {
    return {
      isHidden: false,
      tutoId: null,
      errors: [],
      tuto: {
        id: 0,
        title: null,
        description: null,
        author: null,
        status: null
      }
    };
  },

  created (){
    tutorialsApp.$on('updateTuto', (id) => {
      this.tutoId=id;
      this.isHidden=true;
    });
  },


  methods: {

    
    hideForm() {
      this.isHidden = false;
    },

    tutoPatch(ep) {
      fetch('/portal/rest/tuto/updateTuto',{
        method: 'PUT',
        body: JSON.stringify({
          id: this.tutoId,
          title: this.tuto.title, 
          description: this.tuto.description,
          author: this.tuto.author,
          status: this.tuto.status
        }), 
        headers: { 
          'Content-Type': 'application/json'
        }}
      )
        .then((response) => {tutorialsApp.$emit('updateTuto', ep, response);
          this.isHidden=false;})
        .catch((e) => this.errors.push(e));
      ep.preventDefault();
      this.tuto.id = 0;
      this.tuto.title = '';
      this.tuto.description = '';
      this.tuto.author = '';
      this.tuto.status = '';
      
    },

    clearForm(){
      this.tuto.title = '';
      this.tuto.description = '';
      this.tuto.author = '';
      this.tuto.status = '';
    }
  },
};
</script>
