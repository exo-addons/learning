<template>
  <v-app
    id="theme_card"
    flat>
    <template>
      <v-card
        class="theme_items"
        :id="`theme-${theme.id}`">
        <div class="theme_card_toolbar d-flex px-3 align-center font-weight-bold">
          <span
            class="d-flex align-center"
            id="theme_card_title" 
            @click="showTutos(theme.id)">{{ theme.name }}</span>
          <div class="spacer d-none d-sm-inline"></div>
          
          <v-icon @click="displayActionMenu = true">mdi-dots-vertical</v-icon>
          
          <v-menu
             content-class="theme_card_menu"
            v-model="displayActionMenu"
            :attach="`#theme-${theme.id}`"
            transition="slide-x-reverse-transition"
            offset-y
            offset-x>
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
        </div>
        <v-card-text>
          <v-avatar size="35">
            <img
              src="https://cdn.pixabay.com/photo/2020/06/24/19/12/cabbage-5337431_1280.jpg"> 
          </v-avatar>
          Space Name
        </v-card-text>
        <div id="theme_card_footer"></div>
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