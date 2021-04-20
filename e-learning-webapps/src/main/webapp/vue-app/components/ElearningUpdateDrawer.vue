<template>
  <exo-drawer ref="elearningUpdateDrawer" right>
    <template slot="title">
      {{ $t('addon.elearning.tutorial.updateF') }}     
    </template>
    <template slot="content">
      <div>
        <h2> {{ $t('addon.elearning.tutorial.details') }}  </h2>
      </div>
      <div>
        <v-text-field
          outlined
          clearable
          label="Tutorial Title"
          name="title"
          v-model="tuto.title" />
        <v-textarea
          outlined
          clearable
          auto-grow
          label="Description"
          name="description"          
          v-model="tuto.description" />
        <v-text-field
          outlined
          clearable
          label="Author"
          name="author"
          v-model="tuto.author" />
        <v-select
          outlined
          :items="status"
          label="Pick Status"
          name="status"
          v-model="tuto.status" />
      </div>
    </template>
    <template slot="footer">
      <v-btn class="btn btn-primary" @click="tutoUpdate">Confirm</v-btn>
      <v-btn class="btn mr-2" @click="clearForm">Clear</v-btn>
    </template>
  </exo-drawer>
</template>
<script>
import { tutorialsApp } from '../main';
export default {
  
  data() {
    return {
      tutoId: null,
      tutoU: null,
      status: ['DRAFT','PUBLISHED','ARCHIVED'],
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
  created() {
    tutorialsApp.$on('updateTuto', (id) => {
      this.tutoId=id;
      this.$refs.elearningUpdateDrawer.open();
      this.getTutoU();
    });
  },

  methods: {

    tutoUpdate(ep) {
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
        .then((response) => {tutorialsApp.$emit('tutoUpdated', ep, response);})
        .catch((e) => this.errors.push(e));
      this.$refs.elearningUpdateDrawer.close();
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
    },
    getTutoU() {
      fetch(`/portal/rest/tuto/getTutoById/${this.tutoId}`)
        .then((response) => response.json())
        .then((data) => {this.tutoU = data;
          this.tuto.title = this.tutoU.title;
          this.tuto.description = this.tutoU.description;
          this.tuto.author = this.tutoU.author;
          this.tuto.status = this.tutoU.status;
          this.titleU=this.tutoU.title;
          this.authorU=this.tutoU.author;})
        .catch((e) => this.errors.push(e));
    }
  }
};

</script>