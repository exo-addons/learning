<template>
  <v-app
    id="vuetify_webpack_sample"
    flat>
    <exo-drawer ref="elearningManagementDrawer" right>
      <template v-if="this.title" slot="title">
        {{ $t('addon.elearning.tutorial.creating') }}     
      </template>
      <template v-else slot="title">
        {{ $t('addon.elearning.tutorial.updateF') }}     
      </template>
      <template slot="content">
        <div>
          <h2> {{ $t('addon.elearning.tutorial.details') }}  </h2>
        </div>
        <div>
          <v-text-field
            v-if="this.title"
            outlined
            clearable
            label="Tutorial Title"
            name="title"
            v-model="tutoA.title" />
          <v-text-field
            v-else
            outlined
            clearable
            label="Tutorial Title"
            name="title"
            v-model="tutoUp.title" />
          <v-textarea
            v-if="this.title"
            outlined
            clearable
            auto-grow
            rows="3"
            label="Description"
            name="description"          
            v-model="tutoA.description" />
          <v-textarea
            v-else
            outlined
            clearable
            auto-grow
            rows="3"
            label="Description"
            name="description"          
            v-model="tutoUp.description" />
          <v-text-field
            v-if="this.title"
            outlined
            clearable
            label="Author"
            name="author"
            v-model="tutoA.author" />
          <v-text-field
            v-else
            outlined
            clearable
            label="Author"
            name="author"
            v-model="tutoUp.author" />
          <v-select
            v-if="this.title"
            outlined
            :items="status"
            v-model="tutoA.status"
            label="Pick Status"
            name="status" />
          <v-select
            v-else
            outlined
            :items="status"
            v-model="tutoUp.status"
            label="Pick Status"
            name="status" />
        </div>
      </template>
      <template slot="footer">
        <v-btn
          class="btn btn-primary"
          v-if="this.title"
          @click="tutoPost">
          {{ $t('addon.elearning.tutorial.confirm') }}
        </v-btn>
        <v-btn
          class="btn btn-primary"
          v-else
          @click="tutoUpdate">
          {{ $t('addon.elearning.tutorial.confirm') }}
        </v-btn>
        <v-btn class="btn mr-2" @click="clearForm">{{ $t('addon.elearning.tutorial.clear') }}</v-btn>
      </template>
    </exo-drawer>
  </v-app>
</template>
<script>
import { tutorialsApp } from '../main';
export default {
  
  data () {
    return {
      title: false,
      tutoId: null,
      tutoU: null,
      errors: [],
      status: ['DRAFT','PUBLISHED','ARCHIVED'],
      tutoA: {
        title: null,
        description: null,
        author: null,
        status: null
      },
      tutoUp: {
        id: 0,
        title: null,
        description: null,
        author: null,
        status: null
      },
    };
  },
  
  created() {
    tutorialsApp.$on('addTuto', () => {
      this.title = true;
      this.$refs.elearningManagementDrawer.open();
    });
    tutorialsApp.$on('updateTuto', (id) => {
      this.title = false;
      this.tutoId=id;
      this.$refs.elearningManagementDrawer.open();
      this.getTutoU();
    });
  },

  methods: {
    tutoPost() {
      return this.$tutoService.tutoPost(this.tutoA)
        .then(() => {tutorialsApp.$emit('createTuto');})
        .then(() =>{ this.$refs.elearningManagementDrawer.close();
          this.tutoA.title = '';
          this.tutoA.description = '';
          this.tutoA.author = '';
          this.tutoA.status= ''; })
        .catch((e) => this.errors.push(e));
      
    },

    tutoUpdate() {
      this.tutoUp.id=this.tutoId;
      return this.$tutoService.tutoUpdate(this.tutoUp)
        .then(() => {tutorialsApp.$emit('tutoUpdated');})
        .then(() => {this.$refs.elearningManagementDrawer.close();
          this.tutoUp.id = 0;
          this.tutoUp.title = '';
          this.tutoUp.description = '';
          this.tutoUp.author = '';
          this.tutoUp.status = '';})
        .catch((e) => this.errors.push(e));      
    },

    getTutoU() {
      return this.$tutoService.getTuto(this.tutoId)
        .then((data) => {this.tutoU = data;
          this.tutoUp.title = this.tutoU.title;
          this.tutoUp.description = this.tutoU.description;
          this.tutoUp.author = this.tutoU.author;
          this.tutoUp.status = this.tutoU.status;})
        .catch((e) => this.errors.push(e));
    },

    clearForm(){
      if (this.title) {
        this.tutoA.title = '';
        this.tutoA.description = '';
        this.tutoA.author = '';
        this.tutoA.status= '';
      }
      else { this.tutoUp.title = '';
        this.tutoUp.description = '';
        this.tutoUp.author = '';
        this.tutoUp.status= '';}
    }
  }
};

</script>