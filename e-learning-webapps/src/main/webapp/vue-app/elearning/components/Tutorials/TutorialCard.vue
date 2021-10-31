<template>
  <div id="tutorial_card">
    <v-card
      :id="`tuto-${tutorial.id}`">
      <div class="tuto_card_toolbar d-flex px-3 justify-space-between font-weight-bold">
        <v-icon class="tuto_card_menu_info_icon" @click="showTuto(tutorial.id)">mdi-information-outline</v-icon>

        <span
          class="d-flex align-center"
          id="tuto_card_title">{{ tutorial.title }}</span>

        <v-icon
          class="tuto_card_menu_icon"
          @click="displayActionMenu = true"
          v-if="canUpdate">
          mdi-dots-vertical
        </v-icon>

        <v-menu
          content-class="theme_card_menu"
          v-model="displayActionMenu"
          :attach="`#tuto-${tutorial.id}`"
          transition="slide-x-reverse-transition"
          offset-y
          offset-x>
          <v-list class="card_menu_list" dense>
            <v-list-item @click="updateTuto(tutorial.id)">
              <v-list-item-title class="menu_list_items">
                <v-icon class="tuto_menu_icon">mdi-pencil</v-icon>
                <span class="tuto_menu_text" v-if="tutorial.status === 'DRAFT'">{{ $t('addon.elearning.tutorial.update.draft') }}</span>
                <span class="tuto_menu_text" v-else>{{ $t('addon.elearning.tutorial.update') }}</span>
              </v-list-item-title>
            </v-list-item>
            <v-list-item @click="dupTuto(tutorial.id)" v-if="tutorial.status != 'DRAFT'">
              <v-list-item-title class="menu_list_items">
                <v-icon class="tuto_menu_icon">mdi-content-copy</v-icon>
                <span class="tuto_menu_text">{{ $t('addon.elearning.tutorial.duplicate') }}</span>
              </v-list-item-title>
            </v-list-item>
            <v-list-item @click="moveTuto(tutorial.id)" v-if="tutorial.status != 'DRAFT'">
              <v-list-item-title class="menu_list_items">
                <v-icon class="tuto_menu_icon">mdi-move-resize</v-icon>
                <span class="tuto_menu_text">{{ $t('addon.elearning.tutorial.move') }}</span>
              </v-list-item-title>
            </v-list-item>
            <v-list-item @click="archiveTuto(tutorial.id)" v-if="tutorial.status != 'DRAFT' && tutorial.status != 'ARCHIVED'">
              <v-list-item-title class="menu_list_items">
                <v-icon class="tuto_menu_icon">mdi-package-down</v-icon>
                <span class="tuto_menu_text">{{ $t('addon.elearning.tutorial.archive') }}</span>
              </v-list-item-title>
            </v-list-item>
            <v-list-item @click="unarchiveTuto(tutorial.id)" v-if="tutorial.status === 'ARCHIVED'">
              <v-list-item-title class="menu_list_items">
                <v-icon class="tuto_menu_icon">mdi-package-down</v-icon>
                <span class="tuto_menu_text">{{ $t('addon.elearning.tutorial.unarchive') }}</span>
              </v-list-item-title>
            </v-list-item>
            <v-list-item @click="deleteTuto(tutorial.id)">
              <v-list-item-title class="menu_list_items">
                <v-icon class="tuto_menu_icon">mdi-delete</v-icon>
                <span class="tuto_menu_text" v-if="tutorial.status === 'DRAFT'">{{ $t('addon.elearning.tutorial.delete.draft') }}</span>
                <span class="tuto_menu_text" v-else>{{ $t('addon.elearning.tutorial.delete') }}</span>
              </v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </div>
      <v-card-text>
        <v-card-subtitle> {{ tutorial.description }}</v-card-subtitle>
        <v-divider />
        <div class="tuto_card_content">
          <v-avatar size="35" class="tuto_card_space_avatar">
            <img
              :src="space.avatarUrl">
          </v-avatar>
          <a
            :href="spaceUrl"
            class="tuto_card_space_name"
            :title="space.displayName">
            {{ space.displayName }}
          </a>
        </div>
      </v-card-text>
      <div id="tuto_card_footer_draft" v-if="tutorial.status === 'DRAFT'"></div>
      <div id="tuto_card_footer" v-else></div>
    </v-card>
  </div>
</template>

<script>
export default {
  props: {
    canUpdate: {
      type: Boolean,
      default: false
    },
    space: {
      type: Object,
      default: null
    },
    tutorial: {
      type: Object,
      default: null
    }
  },
  data() {
    return {
      displayActionMenu: false,
    };
  },
  computed: {
    spaceUrl() {
      if (this.space && this.space.groupId) {
        const uriPart = this.space.groupId.replace(/\//g, ':');
        return `${eXo.env.portal.context}/g/${uriPart}/`;
      } else {
        return '#';
      }
    },
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
    updateTuto(id) {
      this.$root.$emit('makeUpdateTuto', id);
    },
    showTuto(id) {
      this.$root.$emit('makeShowTTuto', id);
    },
    moveTuto(id) {
      this.$root.$emit('makeMoveTuto', id);
    },
    dupTuto(id) {
      this.$root.$emit('makeDupTuto', id);
    },
    archiveTuto(id) {
      this.$root.$emit('makeArchTuto', id);
    },
    unarchiveTuto(id) {
      this.$root.$emit('makeUnarchTuto', id);
    },
  }
};
</script>