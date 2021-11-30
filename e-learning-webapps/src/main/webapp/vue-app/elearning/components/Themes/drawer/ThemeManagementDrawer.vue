<template>
  <div id="themes_management">
    <exo-drawer ref="themeManagementDrawer" right>
      <template v-if="!this.themeToUpdate" slot="title">
        {{ $t('addon.elearning.theme.create.form.title') }}
      </template>
      <template v-else slot="title">
        {{ $t('addon.elearning.theme.update.form.title') }}
      </template>
      <template slot="content">
        <v-form ref="form">
          <label class="theme_title_label" :for="title">
            {{ `${$t('addon.elearning.theme.form.title.label')}*` }}
          </label>
          <v-text-field
            v-if="themeToUpdate"
            class="primary_theme_input"
            outlined
            clearable
            :placeholder="$t('addon.elearning.theme.form.title.placeholder')"
            name="title"
            v-model="titleToUpdate" />
          <v-text-field
            v-else
            class="primary_theme_input"
            outlined
            clearable
            :placeholder="$t('addon.elearning.theme.form.title.placeholder')"
            name="title"
            v-model="title" />
        </v-form>
      </template>
      <template slot="footer">
        <v-btn
          class="theme_drawer_btn_add"
          @click="createOrUpdateTheme"
          :disabled="!canCreateThemeOrUpdate">
          {{ $t('addon.elearning.theme.form.confirm') }}
        </v-btn>
        <v-btn class="exo_cancel_btn" @click="$refs.form.reset()">{{ $t('addon.elearning.theme.form.clear') }}</v-btn>
      </template>
    </exo-drawer>
  </div>
</template>
<script>
export default {
  props: {
    themeToUpdate: {
      type: Object,
      default: null
    },
    parentTheme: {
      type: Object,
      default: null
    },
    spaceName: {
      type: String,
      default: ''
    },
  },
  
  data() {
    return {
      title: null,
      titleToUpdate: null,
    };
  },
  
  computed: {
    canCreateThemeOrUpdate() {
      return this.themeToUpdate && this.themeToUpdate.name !== this.titleToUpdate && this.titleToUpdate || this.title;
    }
  },
  
  watch: {
    themeToUpdate() {
      if (this.themeToUpdate) {
        this.titleToUpdate = this.themeToUpdate.name;
      }
    }
  },

  methods: {
    createOrUpdateTheme() {
      if (!this.themeToUpdate) {
        const theme = {
          name: this.title,
          spaceName: this.spaceName,
          parentId: this.parentTheme ? this.parentTheme.id: null,
        };
        return this.$themeService.createTheme(theme).then(addedTheme => {
          this.$emit('themeAdded', addedTheme);
          const themeIds = this.parentTheme.childrenIds;
          themeIds.push(theme.id);
          const updatedParent = {
            childrenIds: themeIds,
          };
          Object.assign(this.parentTheme, updatedParent);
          this.$root.$emit('parent-theme-updated', this.parentTheme);
        }).catch((e) => {
          this.$root.$emit('show-alert', {
            message: this.$t('addon.elearning.theme.message.error'),
            type: 'error',
          });
          console.error('Error when creating theme', e);
        });
        
      } else {
        this.themeToUpdate.name = this.titleToUpdate;
        return this.$themeService.updateTheme(this.themeToUpdate).then(updatedTheme => {
          this.$emit('themeUpdated', updatedTheme);
        }).catch((e) => {
          this.$root.$emit('show-alert', {
            message: this.$t('addon.elearning.theme.message.error'),
            type: 'error',
          });
          console.error('Error when updating theme', e);
        });
        
      }
    },
    close() {
      this.$refs.form.reset();
      this.$refs.themeManagementDrawer.close();
    },
    open() {
      this.$refs.themeManagementDrawer.open();
    },
  }
};

</script>