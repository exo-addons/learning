<template>
  <div id="tutorial_display">
    <exo-drawer
      id="tutorialDisplayDrawer"
      ref="tutorialDisplayDrawer"
      right>
      <template slot="title">
        {{ $t('addon.elearning.tutorial.details') }}  
      </template>
      <template slot="content">
        <div v-if="tuto">
          <v-card
            class="tuto_items">
            <v-list-item>
              <v-list-item-avatar v-if="tuto.status== 'DRAFT'" color="orange" />
              <v-list-item-avatar v-else-if="tuto.status== 'PUBLISHED'" color="green" />
              <v-list-item-avatar v-else color="grey" />
              <v-list-item-content>
                <v-list-item-title class="headline">{{ tuto.title }}</v-list-item-title>
                <v-list-item-subtitle>{{ tuto.status }}</v-list-item-subtitle>
                <v-list-item-subtitle>By {{ tuto.author }}</v-list-item-subtitle>
                <v-list-item-subtitle>Created on  <date-format :value="tuto.createdDate.time" :format="dateTimeFormat" /></v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <v-img
              class="tuto_img"
              src="https://cdn.vuetifyjs.com/images/parallax/material2.jpg" />

            <v-card-text>
              {{ tuto.description }}
            </v-card-text>
          </v-card>
        </div>
      </template>
    </exo-drawer>
  </div>
</template>
<script>
export default {
  data() {
    return {
      errors: [],
      tuto: null,
      dateTimeFormat: {
        day: 'numeric',
        month: 'numeric',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
      },
    };
  },
  created() {
    this.$root.$on('makeShowTTuto', (id) => {
      this.getTutoById(id);
      this.$refs.tutorialDisplayDrawer.open();
    });
  },
  methods: {

    getTutoById(id) {
      return this.$tutoService.getTutoById(id)
        .then((data) => {
          this.tuto = data;
        })
        .catch((e) => this.errors.push(e));
    }
  }
};
</script>