<template>
  <div>
    <div>
      <v-btn @click="showForm">{{ $t('addon.elearning.tutorial.create') }}</v-btn>
      <v-btn @click="hideForm" v-if="isHidden">Collapse</v-btn>
    </div>
    <div v-if="isHidden">
      <div>
        <h1>Creating Tuto</h1>
      </div>
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
          <label for="author">Author</label>
          <input
            id="author"
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
          <v-btn class="btn_exo" @click="tutoPost">Submit</v-btn>
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
      errors: [],
      tuto: {
        title: null,
        description: null,
        author: null,
        status: null
      }
    };
  },

  

  methods: {

    showForm() {
      this.isHidden = true;
    },
    
    hideForm() {
      this.isHidden = false;
    },

    tutoPost(ep) {
      fetch('/portal/rest/tuto/addTuto', {
        method: 'POST',
        body: JSON.stringify({
          title: this.tuto.title, 
          description: this.tuto.description,
          author: this.tuto.author,
          status: this.tuto.status
        }),     
        headers: { 
          'Content-Type': 'application/json'
        }
      })
        .then((response) => {tutorialsApp.$emit('createTuto', ep, response);})
        .catch((e) => this.errors.push(e));
      ep.preventDefault();
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
