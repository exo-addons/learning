<template>
  <div v-if="tuto">
    <div>
      <h1>Displaying Tuto</h1>
    </div>
    <v-card
        id="vuetify_webpack_sample"
        max-width="344">
      <v-list-item>
        <v-list-item-avatar v-if="tuto.status== 'Draft'" color="orange"/>
        <v-list-item-avatar v-else-if="tuto.status== 'PUBLISHED'" color="green"/>
        <v-list-item-avatar v-else color="grey"/>
        <v-list-item-content>
          <v-list-item-title class="headline">{{ tuto.title }}</v-list-item-title>
          <v-list-item-subtitle>{{ tuto.status }}</v-list-item-subtitle>
          <v-list-item-subtitle>By {{ tuto.author }}</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-card-text>
        {{ tuto.description }}
      </v-card-text>
    </v-card>
    <div class="form-group">
      <v-btn class="btn_exo" @click="hideTuto">Hide</v-btn>
    </div>
  </div>
</template>

<script>
  import {tutorialsApp} from '../main';

  export default {
    name: 'TutoDisplay',

    data() {
      return {
        tutoId: null,
        errors: [],
        tuto: null
      };
    },
    created() {
      tutorialsApp.$on('displayTuto', (id) => {
        this.tutoId = id;
        this.getTuto();
      });
      tutorialsApp.$on('updateTuto', () => {
        this.getTuto();
      });


    },
    methods: {
      hideTuto() {
        this.tuto = null;
      },
      getTuto() {
        fetch(`/portal/rest/tuto/getTutoById/${this.tutoId}`)
          .then((response) => response.json())
          .then((data) => (this.tuto = data))
          .catch((e) => this.errors.push(e));
      }
    }

  };
</script>