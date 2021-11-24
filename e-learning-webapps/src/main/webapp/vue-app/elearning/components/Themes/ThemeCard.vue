<template>
  <div id="theme_card">
    <v-card
      :id="`theme-${theme.id}`"
      outlined>
      <v-list-item>
        <v-list-item-avatar />
        <v-list-item-content>
          <span
            class="align-center font-weight-bold"
            id="theme_card_title" 
            @click="displayThemeContent">{{ theme.name }} </span>
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
            :attach="`#theme-${theme.id}`"
            content-class="theme_card_menu"
            transition="slide-x-reverse-transition"
            offset-y
            offset-x>
            <v-list class="card_menu_list" dense>
              <v-list-item @click="update">
                <v-list-item-title class="menu_list_items">
                  <v-icon class="theme_menu_icon">mdi-pencil</v-icon>
                  <span class="theme_menu_text">{{ $t('addon.elearning.theme.update.label') }}</span>
                </v-list-item-title>
              </v-list-item>
              <v-list-item class="clickable">
                <v-list-item-title class="menu_list_items">
                  <v-icon class="theme_menu_icon">mdi-package-down</v-icon>
                  <span class="theme_menu_text">{{ $t('addon.elearning.theme.archive.label') }}</span>
                </v-list-item-title>
              </v-list-item>
              <v-list-item @click="deleteTheme(theme.id)">
                <v-list-item-title class="menu_list_items">
                  <v-icon class="theme_menu_icon">mdi-delete</v-icon>
                  <span class="theme_menu_text">{{ $t('addon.elearning.theme.delete.label') }}</span>
                </v-list-item-title>
              </v-list-item>
              <v-list-item class="menu_list_items px-2 noColorLabel">
                <v-list-item-title class="noColorLabel caption text-center text&#45;&#45;secondary">
                  <span @click="changeColorTheme(theme,'')">{{ $t('label.noColor') }}</span>
                </v-list-item-title>
              </v-list-item>
              <v-list-item>
                <v-list-item-title class="subtitle-2 row themeColorPicker mx-auto my-2">
                  <span
                    v-for="(color, i) in themeColors"
                    :key="i"
                    :class="[ color.class , color.class === theme.color ? 'isSelected' : '']"
                    class="themeColorCell"
                    @click="changeColorTheme(theme,color.class)"></span>
                </v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-list-item-action>
      </v-list-item>
      <v-card-text class="pb-1">
        <v-card-subtitle> {{ theme.description }}</v-card-subtitle>
        <v-divider />
        <div class="mt-1">
          <v-list-item>
            <v-list-item-avatar>
              <img
                :src="space ? space.avatarUrl : ''">
            </v-list-item-avatar>
            <v-list-item-content>
              <a 
                :href="spaceUrl" 
                :title="space ? space.displayName : ''">
                {{ space ? space.displayName : '' }}
              </a>
            </v-list-item-content>
          </v-list-item>
        </div>
      </v-card-text>
      <div class="theme_card_footer" :class="theme.color ? `${theme.color}_border` : 'no_border_color'"></div>
    </v-card>
  </div>
</template>

<script>
export default {
  props: {
    theme: {
      type: Object,
      default: null
    },
    space: {
      type: Object,
      default: null
    },
    canUpdate: {
      type: Boolean,
      default: false
    },
  },
  
  data() {
    return {
      displayActionMenu: false,
      themeColors: [
        { class: 'asparagus' },
        { class: 'munsell_blue' },
        { class: 'navy_blue' },
        { class: 'purple' },
        { class: 'red' },
        { class: 'brown' },
        { class: 'laurel_green' },
        { class: 'sky_blue' },
        { class: 'blue_gray' },
        { class: 'light_purple' },
        { class: 'hot_pink' },
        { class: 'light_brown' },
        { class: 'moss_green' },
        { class: 'powder_blue' },
        { class: 'light_blue' },
        { class: 'pink' },
        { class: 'Orange' },
        { class: 'gray' },
        { class: 'green' },
        { class: 'baby_blue' },
        { class: 'light_gray' },
        { class: 'beige' },
        { class: 'yellow' },
        { class: 'plum' },
      ],
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
    deleteTheme(id) {
      this.$emit('deleteTheme', id);
    },
    update() {
      this.$emit('updateTheme', this.theme);
    },
    displayThemeContent() {
      this.$root.$emit('displayThemeContent', this.theme);
    },
    changeColorTheme(theme, color) {
      this.$themeService.updateThemeColor(theme, color)
        .then(this.theme.color = color);
    },
  }
};
</script>