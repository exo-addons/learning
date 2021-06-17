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
            @click="showTutos(theme.id)">{{ theme.name }} </span>
          
          <v-icon class="theme_card_menu_icon" @click="displayActionMenu = true">mdi-dots-vertical</v-icon>
          
          <v-menu
            content-class="theme_card_menu"
            v-model="displayActionMenu"
            :attach="`#theme-${theme.id}`"
            transition="slide-x-reverse-transition"
            offset-y
            offset-x>
            <v-list class="card_menu_list" dense>
              <v-list-item @click="update(theme.id)">
                <v-list-item-title class="menu_list_items">
                  <v-icon class="theme_menu_icon">mdi-pencil</v-icon>
                  <span class="theme_menu_text">{{ $t('addon.elearning.theme.update') }}</span>
                </v-list-item-title>
              </v-list-item>
              <v-list-item class="clickable">
                <v-list-item-title class="menu_list_items">
                  <v-icon class="theme_menu_icon">mdi-package-down</v-icon>
                  <span class="theme_menu_text">{{ $t('addon.elearning.theme.archive') }}</span>
                </v-list-item-title>
              </v-list-item>
              <v-list-item @click="deleteTheme(theme.id)">
                <v-list-item-title class="menu_list_items">
                  <v-icon class="theme_menu_icon">mdi-delete</v-icon>
                  <span class="theme_menu_text">{{ $t('addon.elearning.theme.delete') }}</span>
                </v-list-item-title>
              </v-list-item>              
            </v-list>
          </v-menu>
        </div>
        <v-card-text>
          <div class="theme_card_content">
            <v-avatar size="35" class="theme_card_space_avatar">
              <img
                src="https://cdn.pixabay.com/photo/2020/06/24/19/12/cabbage-5337431_1280.jpg"> 
            </v-avatar>
            <span class="theme_card_space_name">
              Space Name
            </span>
          </div>
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