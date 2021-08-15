<template>
  <v-app
    id="themes_management"
    flat>
    <exo-drawer ref="themeManagementDrawer" right>
      <template v-if="this.title" slot="title">
        {{ $t('addon.elearning.theme.creating') }}     
      </template>
      <template v-else slot="title">
        {{ $t('addon.elearning.theme.updateF') }}     
      </template>
      <template slot="content">
        <v-form ref="form">
          <label class="theme_title_label" for="title">
            {{ $t('addon.elearning.theme.label.title') }}  
          </label>
          <v-text-field
            v-if="this.title"
            class="primary_theme_input"
            outlined
            clearable
            :placeholder="$t('addon.elearning.theme.label.placeholder')"
            name="title"
            v-model="themeA.name" />
          <v-text-field
            v-else
            class="primary_theme_input"
            outlined
            clearable
            :placeholder="$t('addon.elearning.theme.label.placeholder')"
            name="title"
            v-model="themeUp.name" />
        </v-form>
      </template>
      <template slot="footer">
        <v-btn
          class="theme_drawer_btn_add"
          v-if="this.title"
          @click="themePost"
          :disabled="!isAddComplete">
          {{ $t('addon.elearning.tutorial.confirm') }}
        </v-btn>
        <v-btn
          class="theme_drawer_btn_add"
          v-else
          @click="themeUpdate"
          :disabled="!isUpComplete">
          {{ $t('addon.elearning.tutorial.confirm') }}
        </v-btn>
        <v-btn class="exo_cancel_btn" @click="$refs.form.reset()">{{ $t('addon.elearning.tutorial.clear') }}</v-btn>
      </template>
    </exo-drawer>
  </v-app>
</template>
<script>
export default {
  
  data () {
    return {
      title: false,
      themeId: null,
      themeU: null,
      errors: [],
      themeA: {
        name: null,
        spaceName: null
      },
      themeUp: {
        id: 0,
        name: null,
        spaceName: null
      }
    };
  },
  
  computed: {
    isAddComplete () {
      return this.themeA.name;
    },
    isUpComplete () {
      return this.themeUp.name;
    }
  },

  created() {
    this.$root.$on('addTheme', () => {
      this.title = true;
      this.$refs.themeManagementDrawer.open();
    });
    this.$root.$on('updateTheme', (id) => {
      this.title = false;
      this.$refs.themeManagementDrawer.open();
      this.themeId=id;
      this.getThemeU(id);
    });
  },

  methods: {
    themePost() {
      this.themeA.spaceName=eXo.env.portal.spaceName;
      return this.$themeService.themePost(this.themeA)
        .then(() => {this.$root.$emit('themeCreated');})
        .then(() =>{ 
          this.$refs.form.reset();
          this.$refs.themeManagementDrawer.close(); })
        .catch((e) => this.errors.push(e));
      
    },

    themeUpdate() {
      this.themeUp.id=this.themeId;
      return this.$themeService.themeUpdate(this.themeUp)
        .then(() => {this.$root.$emit('themeUpdated');})
        .then(() => {
          this.themeUp.id = 0;
          this.$refs.form.reset();
          this.$refs.themeManagementDrawer.close();})
        .catch((e) => this.errors.push(e));      
    },

    getThemeU(id) {
      return this.$themeService.getThemeById(id)
        .then((data) => {this.themeU = data;
          this.themeUp.name = this.themeU.name;
          this.themeUp.spaceName = this.themeU.spaceName;})
        .catch((e) => this.errors.push(e));
    },
  }
};

</script>