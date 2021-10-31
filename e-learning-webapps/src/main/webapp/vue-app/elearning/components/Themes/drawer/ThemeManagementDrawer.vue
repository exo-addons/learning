<template>
  <div id="themes_management">
    <exo-drawer ref="themeManagementDrawer" right>
      <template v-if="!this.themeToUpdate" slot="title">
        {{ $t('addon.elearning.theme.creating') }}
      </template>
      <template v-else slot="title">
        {{ $t('addon.elearning.theme.updateF') }}
      </template>
      <template slot="content">
        <v-form ref="form">
          <label class="theme_title_label" :for="title">
            {{ $t('addon.elearning.theme.label.title') }}
          </label>
          <v-text-field
            v-if="themeToUpdate"
            class="primary_theme_input"
            outlined
            clearable
            :placeholder="$t('addon.elearning.theme.label.placeholder')"
            name="title"
            v-model="titleToUpdate" />
          <v-text-field
            v-else
            class="primary_theme_input"
            outlined
            clearable
            :placeholder="$t('addon.elearning.theme.label.placeholder')"
            name="title"
            v-model="title" />
        </v-form>
      </template>
      <template slot="footer">
        <v-btn
          class="theme_drawer_btn_add"
          @click="createOrUpdateTheme"
          :disabled="!canCreateThemeOrUpdate">
          {{ $t('addon.elearning.tutorial.confirm') }}
        </v-btn>
        <v-btn class="exo_cancel_btn" @click="$refs.form.reset()">{{ $t('addon.elearning.tutorial.clear') }}</v-btn>
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
      errors: [],
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
          parentId: this.parentTheme.id
        };
        return this.$themeService.createTheme(theme).then(addedTheme => {
          this.$emit('themeAdded', addedTheme);
        }).catch((e) => this.errors.push(e));
        
      } else {
        this.themeToUpdate.name = this.titleToUpdate;
        return this.$themeService.updateTheme(this.themeToUpdate).then(updatedTheme => {
          this.$emit('themeUpdated', updatedTheme);
        }).catch((e) => this.errors.push(e));
        
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