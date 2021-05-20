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
        <div>
          <h2> {{ $t('addon.elearning.theme.details') }}  </h2>
        </div>
        <v-form ref="form">
          <v-text-field
            v-if="this.title"
            class="primary_theme_input"
            outlined
            clearable
            :label="$t('addon.elearning.theme.label.title')"
            name="title"
            v-model="themeA.name" />
          <v-text-field
            v-else
            class="primary_theme_input"
            outlined
            clearable
            :label="$t('addon.elearning.theme.label.title')"
            name="title"
            v-model="themeUp.name" />
        </v-form>
      </template>
      <template slot="footer">
        <v-btn
          class="exo_primary_btn"
          v-if="this.title"
          @click="themePost">
          {{ $t('addon.elearning.tutorial.confirm') }}
        </v-btn>
        <v-btn
          class="exo_primary_btn"
          v-else
          @click="themeUpdate">
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
      },
      themeUp: {
        id: 0,
        name: null,
      }
    };
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
      return this.$themeService.themePost(this.themeA)
        .then(() => {this.$root.$emit('createTheme');})
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
      return this.$themeService.getTheme(id)
        .then((data) => {this.themeU = data;
          this.themeUp.name = this.themeU.name;})
        .catch((e) => this.errors.push(e));
    },
  }
};

</script>