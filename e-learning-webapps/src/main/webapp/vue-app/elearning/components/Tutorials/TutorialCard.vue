<template>
  <div id="tutorial_card">
    <v-card
      :id="`tuto-${tutorial.id}`"
      outlined>
      <v-list-item>
        <v-list-item-avatar>
          <v-icon 
            class="primary--text"
            @click="showTutorial()">
            mdi-play-box-outline
          </v-icon>          
        </v-list-item-avatar>
        <v-list-item-content>
          <span class="align-center font-weight-bold">{{ tutorial.title }}</span>
        </v-list-item-content>
        <v-list-item-action>
          <v-icon
            v-if="canUpdate"
            class="text-sub-title d-block"
            @click="displayActionMenu = true">
            mdi-dots-vertical
          </v-icon>  
          <v-menu
            v-model="displayActionMenu"
            :attach="`#tuto-${tutorial.id}`"
            content-class="tutorial_card_menu"
            transition="slide-x-reverse-transition"
            offset-y
            offset-x>
            <v-list class="card_menu_list" dense>
              <v-list-item class="px-2 py-1" @click="updateTutorial()">
                <v-list-item-title class="menu_list_items">
                  <v-icon class="primary--text" size="20">mdi-pencil</v-icon>
                  <span class="tuto_menu_text">{{ tutorial.status === 'DRAFT' ? $t('addon.elearning.tutorial.update.draft.label') : $t('addon.elearning.tutorial.update.label') }}</span>
                </v-list-item-title>
              </v-list-item>
              <v-list-item class="px-2 py-1" @click="updateTutorialContent()">
                <v-list-item-title class="menu_list_items">
                  <v-icon class="primary--text" size="20">mdi-pencil-box</v-icon>
                  <span class="tuto_menu_text">{{ tutorial.status === 'DRAFT' ? $t('addon.elearning.tutorial.update.draft.content.label') : $t('addon.elearning.tutorial.update.content.label') }}</span>
                </v-list-item-title>
              </v-list-item>
              <v-list-item 
                v-if="tutorial.status != 'DRAFT'" 
                class="px-2 py-1" 
                @click="duplicateTutorial()">
                <v-list-item-title class="menu_list_items">
                  <v-icon class="primary--text" size="20">mdi-content-copy</v-icon>
                  <span class="tuto_menu_text">{{ $t('addon.elearning.tutorial.duplicate.label') }}</span>
                </v-list-item-title>
              </v-list-item>
              <v-list-item 
                v-if="tutorial.status != 'DRAFT'"
                class="px-2 py-1"
                @click="moveTutorial()">
                <v-list-item-title class="menu_list_items">
                  <v-icon class="primary--text" size="20">mdi-move-resize</v-icon>
                  <span class="tuto_menu_text">{{ $t('addon.elearning.tutorial.move.label') }}</span>
                </v-list-item-title>
              </v-list-item>
              <v-list-item 
                v-if="tutorial.status != 'DRAFT' && tutorial.status != 'ARCHIVED'"
                class="px-2 py-1"
                @click="archiveTutorial()">
                <v-list-item-title class="menu_list_items">
                  <v-icon class="primary--text" size="20">mdi-package-down</v-icon>
                  <span class="tuto_menu_text">{{ $t('addon.elearning.tutorial.archive.label') }}</span>
                </v-list-item-title>
              </v-list-item>
              <v-list-item 
                v-if="tutorial.status === 'ARCHIVED'"
                class="px-2 py-1"
                @click="unarchiveTutorial()">
                <v-list-item-title class="menu_list_items">
                  <v-icon class="primary--text" size="20">mdi-package-down</v-icon>
                  <span class="tuto_menu_text">{{ $t('addon.elearning.tutorial.unarchive.label') }}</span>
                </v-list-item-title>
              </v-list-item>
              <v-list-item class="px-2 py-1" @click="deleteTutorial()">
                <v-list-item-title class="menu_list_items">
                  <v-icon class="primary--text" size="20">mdi-delete</v-icon>
                  <span class="tuto_menu_text" v-if="tutorial.status === 'DRAFT'">{{ $t('addon.elearning.tutorial.delete.draft.label') }}</span>
                  <span class="tuto_menu_text" v-else>{{ $t('addon.elearning.tutorial.delete.label') }}</span>
                </v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-list-item-action>
      </v-list-item>
      <v-card-text class="pb-1">
        <v-card-subtitle> {{ tutorial.description }}</v-card-subtitle>
        <v-divider />
        <div class="mt-1">
          <v-list-item>
            <v-list-item-avatar>
              <img
                :src="space.avatarUrl">
            </v-list-item-avatar>
            <v-list-item-content>
              <a
                :href="spaceUrl"
                :title="space.displayName">
                {{ space.displayName }}
              </a>
            </v-list-item-content>
          </v-list-item>
        </div>
      </v-card-text>
      <div id="tuto_card_footer" :class="parentTheme.color ? `${parentTheme.color}_border` : 'no_border_color'"></div>
    </v-card>
  </div>
</template>

<script>
export default {
  props: {
    parentTheme: {
      type: Object,
      default: null
    },
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
    deleteTutorial() {
      this.$root.$emit('deleteTutorial', this.tutorial.id);
    },
    updateTutorial() {
      this.$root.$emit('makeUpdateTutorial', this.tutorial.id);
    },
    updateTutorialContent() {
      window.open(`${eXo.env.portal.context}/${eXo.env.portal.portalName}/elearning-editor?spaceId=${eXo.env.portal.spaceId}&themeId=${this.parentTheme.id}&tutorialId=${this.tutorial.id}`, '_blank');
    },
    showTutorial() {
      window.open(`${eXo.env.portal.context}/${eXo.env.portal.portalName}/elearning-preview?spaceId=${eXo.env.portal.spaceId}&themeId=${this.parentTheme.id}&tutorialId=${this.tutorial.id}`, '_blank');
    },
    moveTutorial() {
      this.$root.$emit('makeMoveTuto', this.tutorial.id);
    },
    duplicateTutorial() {
      this.$root.$emit('makeDupTuto', this.tutorial.id);
    },
    archiveTutorial() {
      this.$root.$emit('makeArchTuto', this.tutorial.id);
    },
    unarchiveTutorial() {
      this.$root.$emit('makeUnarchTuto', this.tutorial.id);
    },
  }
};
</script>