<template>
  <exo-drawer ref="elearningManagementDrawer" right>
    <template slot="title">
      {{ $t('addon.elearning.tutorial.creating') }}     
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
          v-model="tuto.status"
          label="Pick Status"
          name="status" />
      </div>
    </template>
    <template slot="footer">
      <v-btn class="btn btn-primary" @click="tutoPost">Confirm</v-btn>
      <v-btn class="btn mr-2" @click="clearForm">Clear</v-btn>
    </template>
  </exo-drawer>
</template>
<script>
import { tutorialsApp } from '../main';
export default {
  
  data () {
    return {
      errors: [],
      status: ['DRAFT','PUBLISHED','ARCHIVED'],
      tuto: {
        title: null,
        description: null,
        author: null,
        status: null
      }
    };
  },
  
  created() {
    tutorialsApp.$on('addTuto', () => {
      this.$refs.elearningManagementDrawer.open();
    });
  },

  methods: {
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
      this.$refs.elearningManagementDrawer.close();
      this.tuto.title = '';
      this.tuto.description = '';
      this.tuto.author = '';
      this.tuto.status= '';
    },

    clearForm(){
      this.tuto.title = '';
      this.tuto.description = '';
      this.tuto.author = '';
      this.tuto.status= '';
    }
  }
};

</script>