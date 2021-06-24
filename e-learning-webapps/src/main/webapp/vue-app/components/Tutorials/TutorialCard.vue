<template>
  <v-app
    id="tutorial_card"
    flat>
    <template>
      <v-card
        :class="getClass()"
        :id="`tuto-${tuto.id}`">
        <div class="tuto_card_toolbar d-flex px-3 align-center font-weight-bold">
          <v-icon class="tuto_card_menu_info_icon" @click="showTuto(tuto.id)">mdi-information-outline</v-icon>

          <span
            class="d-flex align-center"
            id="tuto_card_title">  <v-icon v-if="tuto.status === 'DRAFT'" id="tuto_card_title_draft_icon"> mdi-file-outline </v-icon> {{ tuto.title }}</span>

          <v-icon class="tuto_card_menu_icon" @click="displayActionMenu = true">mdi-dots-vertical</v-icon>

          <v-menu
            content-class="theme_card_menu"
            v-model="displayActionMenu"
            :attach="`#tuto-${tuto.id}`"
            transition="slide-x-reverse-transition"
            offset-y
            offset-x>
            <v-list class="card_menu_list" dense>
              <v-list-item @click="updateTuto(tuto.id)">
                <v-list-item-title class="menu_list_items">
                  <v-icon class="tuto_menu_icon">mdi-pencil</v-icon>
                  <span class="tuto_menu_text" v-if="tuto.status === 'DRAFT'">{{ $t('addon.elearning.tutorial.update.draft') }}</span>
                  <span class="tuto_menu_text" v-else>{{ $t('addon.elearning.tutorial.update') }}</span>
                </v-list-item-title>
              </v-list-item>
              <v-list-item @click="dupTuto(tuto.id)" v-if="tuto.status != 'DRAFT'">
                <v-list-item-title class="menu_list_items">
                  <v-icon class="tuto_menu_icon">mdi-content-copy</v-icon>
                  <span class="tuto_menu_text">{{ $t('addon.elearning.tutorial.duplicate') }}</span>
                </v-list-item-title>
              </v-list-item>
              <v-list-item @click="moveTuto(tuto.id)" v-if="tuto.status != 'DRAFT'">
                <v-list-item-title class="menu_list_items">
                  <v-icon class="tuto_menu_icon">mdi-move-resize</v-icon>
                  <span class="tuto_menu_text">{{ $t('addon.elearning.tutorial.move') }}</span>
                </v-list-item-title>
              </v-list-item>
              <v-list-item @click="archiveTuto(tuto.id)" v-if="tuto.status != 'DRAFT' && tuto.status != 'ARCHIVED'">
                <v-list-item-title class="menu_list_items">
                  <v-icon class="tuto_menu_icon">mdi-package-down</v-icon>
                  <span class="tuto_menu_text">{{ $t('addon.elearning.tutorial.archive') }}</span>
                </v-list-item-title>
              </v-list-item>
              <v-list-item @click="unarchiveTuto(tuto.id)" v-if="tuto.status === 'ARCHIVED'">
                <v-list-item-title class="menu_list_items">
                  <v-icon class="tuto_menu_icon">mdi-package-down</v-icon>
                  <span class="tuto_menu_text">{{ $t('addon.elearning.tutorial.unarchive') }}</span>
                </v-list-item-title>
              </v-list-item>
              <v-list-item @click="deleteTuto(tuto.id)">
                <v-list-item-title class="menu_list_items">
                  <v-icon class="tuto_menu_icon">mdi-delete</v-icon>
                  <span class="tuto_menu_text" v-if="tuto.status === 'DRAFT'">{{ $t('addon.elearning.tutorial.delete.draft') }}</span>
                  <span class="tuto_menu_text" v-else>{{ $t('addon.elearning.tutorial.delete') }}</span>
                </v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </div>
        <v-card-text>
          <v-card-subtitle> {{ tuto.description }} </v-card-subtitle>
          <v-divider />
          <div class="tuto_card_content">
            <v-avatar size="35" class="tuto_card_space_avatar">
              <img
                src="https://cdn.pixabay.com/photo/2020/06/24/19/12/cabbage-5337431_1280.jpg"> 
            </v-avatar>
            <span class="tuto_card_space_name">
              Space Name
            </span>
          </div>
        </v-card-text>
        <div id="tuto_card_footer_draft" v-if="tuto.status === 'DRAFT'"></div>
        <div id="tuto_card_footer" v-else></div>
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
    },
    moveTuto(id){
      this.$root.$emit('makeMoveTuto', id);
    },
    dupTuto(id){
      this.$root.$emit('makeDupTuto', id);
    },
    archiveTuto(id){
      this.$root.$emit('makeArchTuto', id);
    },
    unarchiveTuto(id){
      this.$root.$emit('makeUnarchTuto', id);
    },
    getClass(){
      return {
        'tuto_items': this.tuto.status !== 'ARCHIVED',  
        'tuto_items_archived': this.tuto.status === 'ARCHIVED'};
    },
  } 
};
</script>