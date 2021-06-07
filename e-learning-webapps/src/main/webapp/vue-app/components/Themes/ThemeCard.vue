<template>
  <v-app
    id="theme_card"
    flat>
    <template>
      <v-card
        class="theme_items"
        :id="`theme-${theme.id}`">
        <v-card-title>
          <span
            class="headline" 
            @click="showTutos(theme.id)">{{ theme.name }}</span>              
          <v-spacer />
          <v-icon @click="displayActionMenu = true">mdi-dots-vertical</v-icon>
          <v-menu
            v-model="displayActionMenu"
            :attach="`#theme-${theme.id}`"
            transition="slide-x-reverse-transition"
            offset-y>
            <v-list class="pa-0" dense>
              <v-list-item class="menu-list" @click="update(theme.id)">
                <v-list-item-title class="subtitle-2">
                  <v-icon>mdi-pencil</v-icon>
                  <span>{{ $t('addon.elearning.tutorial.update') }}</span>
                </v-list-item-title>
              </v-list-item>
              <v-list-item class="draftButton" @click="deleteTheme(theme.id)">
                <v-list-item-title class="subtitle-2">
                  <v-icon>mdi-delete</v-icon>
                  <span>{{ $t('addon.elearning.tutorial.delete') }}</span>
                </v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-card-title>
      </v-card>
    </template>   
  </v-app>
</template>

<script>
export default {

  props: {
    theme: {
      type: Object,
      default: null
    }
  },
  data () {
    return {
      displayActionMenu: false
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
    deleteTheme(id) {
      this.$root.$emit('deleteTheme', id);
    },
    update(id){
      this.$root.$emit('makeUpdateTheme', id);
    },
    showTutos(id){
      this.$root.$emit('makeShowTutos', id);
    }
  }  
};
</script>