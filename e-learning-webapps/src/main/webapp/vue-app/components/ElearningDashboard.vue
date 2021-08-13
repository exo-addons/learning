<template>
  <v-app
      id="vuetify_webpack_sample_Portlet"
      color="transaprent"
      class="VuetifyApp"
      flat>
    <main>
      <v-row>
        <v-col
            align-self="stretch"
            cols="12"
            sm="3"
            md="3"
            v-for="tuto in tutoList"
            :key="tuto.id">
          <v-card
              id="vuetify_webpack_sample"
              max-width="344"
              @click="display(tuto.id)">
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

            <v-card-actions>
              <v-btn
                  text
                  color="deep-purple accent-4"
                  @click="update(tuto.id)">
                Update
              </v-btn>
              <v-btn
                  text
                  color="deep-purple accent-4"
                  @click="deleteTuto(tuto.id)">
                Delete
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </main>
  </v-app>
</template>

<script>
  import {tutorialsApp} from '../main';

  export default {
    name: 'TutoList',

    data() {
      return {
        tutoList: [],
        errors: [],
      };
    },
    mounted() {
      this.getTutos();
      tutorialsApp.$on('createTuto', () => {
        this.getTutos();
      });
      tutorialsApp.$on('updateTuto', () => {
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
      deleteTuto(id) {
        return fetch(`/portal/rest/tuto/deleteTuto/${id}`, {
          method: 'DELETE'
        })
          .then(() => {
            this.getTutos();
          })
          .catch((e) => this.errors.push(e));
      },
      update(id) {
        tutorialsApp.$emit('updateTuto', id);
      },
      display(id) {
        tutorialsApp.$emit('displayTuto', id);
      }
    }
  };
</script>