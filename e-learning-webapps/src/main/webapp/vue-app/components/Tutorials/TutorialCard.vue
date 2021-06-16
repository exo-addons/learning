<template>
  <v-app
    id="tutorial_card"
    flat>
    <template>
      <v-card
        class="tuto_items"
        :id="`tuto-${tuto.id}`">
        <div class="theme_card_toolbar d-flex px-3 align-center font-weight-bold">
          <span
            class="d-flex align-center"
            id="tuto_card_title">{{ tuto.title }}</span>
          <v-spacer />
          <v-icon @click="displayActionMenu = true">mdi-dots-vertical</v-icon>
          <v-menu
            v-model="displayActionMenu"
            :attach="`#tuto-${tuto.id}`"
            transition="slide-x-reverse-transition"
            offset-y>
            <v-list class="pa-0" dense>
              <v-list-item class="menu-list" @click="updateTuto(tuto.id)">
                <v-list-item-title class="subtitle-2">
                  <v-icon>mdi-pencil</v-icon>
                  <span>{{ $t('addon.elearning.tutorial.update') }}</span>
                </v-list-item-title>
              </v-list-item>
              <v-list-item class="menu-list" @click="showTuto(tuto.id)">
                <v-list-item-title class="subtitle-2">
                  <v-icon>mdi-eye</v-icon>
                  <span>{{ $t('addon.elearning.tutorial.display') }}</span>
                </v-list-item-title>
              </v-list-item>
              <v-list-item class="draftButton" @click="deleteTuto(tuto.id)">
                <v-list-item-title class="subtitle-2">
                  <v-icon>mdi-delete</v-icon>
                  <span>{{ $t('addon.elearning.tutorial.delete') }}</span>
                </v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </div>
        <v-img 
          class="tuto_list_img"
          src="https://cdn.vuetifyjs.com/images/parallax/material2.jpg" />
        <v-list>
          <v-list-item>
            <v-icon>mdi-account</v-icon>
            <div class="mx-1"></div>
            <v-list-item-content>
              <v-list-item-title>{{ tuto.author }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-divider inset />                  
          <v-list-item>
            <v-icon>mdi-calendar</v-icon>
            <div class="mx-1"></div>
            <v-list-item-content>
              <v-list-item-title>
                <div>
                  <date-format :value="tuto.createdDate.time" :format="dateTimeFormat" />
                </div>
              </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
        <div id="tuto_card_footer"></div>
      </v-card>
    </template>
  </v-app>
</template>

<script>
export default {
  props: {
    tuto: {
      type: Object,
      default: null
    }
  },
  data () {
    return {
      displayActionMenu: false,
      dateTimeFormat: {
        day: 'numeric',
        month: 'numeric',
        year: 'numeric',       
      },
    };
  },
  created() {      
    $(document).on('mousedown', () => {
      if (this.displayActionMenu) {
        window.setTimeout(() => {
          this.displayActionMenu = false;
        }, 200);
      }
    });
  },

  methods: {
    deleteTuto(id) {
      this.$root.$emit('deleteTuto', id);
    },
    updateTuto(id){
      this.$root.$emit('makeUpdateTuto', id);
    },
    showTuto(id){
      this.$root.$emit('makeShowTTuto', id);
    }
  } 
};
</script>