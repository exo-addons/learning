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
          <label for="description">AuthorID</label>
          <input
            id="description" 
            type="number" 
            name="authorId"
            v-model.number="tuto.authorId">
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
import { EventBus } from '../main';
const querystring = require('querystring');
export default {
  
  name: 'PostTuto',
  data() {
    return {
      isHidden: false,
      tutoId: null,
      errors: [],
      tuto: {
        title: null,
        description: null,
        authorId: 0,
        status: null
      }
    };
  },

  created (){
    EventBus.$on('updateTuto', (id) => {
      this.tutoId=id;
      this.isHidden=true;
    });
  },


  methods: {

    
    hideForm() {
      this.isHidden = false;
    },

    tutoPatch(ep) {
      fetch(`/portal/rest/tuto/updateTuto/${this.tutoId}`,{
        method: 'PUT',
        body: querystring.stringify({
          title: this.tuto.title, 
          description: this.tuto.description,
          authorId: parseInt(this.tuto.authorId),
          status: this.tuto.status
        }), 
        headers: { 
          'Content-Type': 'application/x-www-form-urlencoded'
        }}
      )
        .then((response) => {EventBus.$emit('updateTuto', ep, response);
          this.isHidden=false;})
        .catch((e) => this.errors.push(e));
      ep.preventDefault();
      this.tuto.title = '';
      this.tuto.description = '';
      this.tuto.authorId = 0;
      this.tuto.status = '';
      
    },

    clearForm(){
      this.tuto.title = '';
      this.tuto.description = '';
      this.tuto.authorId = 0;
      this.tuto.status = '';
    }
  },
};
</script>
