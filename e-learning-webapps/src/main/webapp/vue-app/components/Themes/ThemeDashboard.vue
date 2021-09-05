<template>
  <v-app
    id="themes_dashboard"
    flat>
    <theme-dashboard-toolbar
      :keyword="keyword"
      @keyword-changed="keyword = $event" />
    <theme-card-list 
      :keyword="keyword" />    
    <exo-confirm-dialog
      ref="confirmDialog"
      :message="$t('addon.elearning.theme.deleteConf')"
      :title="$t('addon.elearning.tutorial.confirmD')"
      :ok-label="$t('addon.elearning.tutorial.confirm')"
      :cancel-label="$t('addon.elearning.tutorial.cancel')"
      @ok="deleteTheme()" />
    <template>
      <div class="text-center">
        <v-snackbar
          v-model="successBar"
          :timeout="timeout"
          :color="this.color">
          {{ text }}
          <template v-slot:action="{ attrs }">
            <v-btn 
              class="snackbar_btn"
              text
              v-bind="attrs"
              @click="successBar = false">
              {{ $t('addon.elearning.tutorial.dismiss') }}
            </v-btn>
          </template>
        </v-snackbar>
      </div>
    </template>   
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      successBar: false,
      text: '',
      color: 'success',
      timeout: 0,
      deleteId: null,
      errors: [],
      keyword: null
    };
  },

  created() {
    this.$root.$on('themeCreated', () => {
      this.timeout = 3000;
      this.successBar = true;
      this.text = this.$t('addon.elearning.theme.created');
    });
    this.$root.$on('themeUpdated', () => {
      this.timeout = 3000;
      this.successBar = true;
      this.text = this.$t('addon.elearning.theme.updated');
    });
    this.$root.$on('deleteTheme', (id) => {
      this.prepareDelete(id);
    });
    this.$root.$on('makeTheme', () => {
      this.$root.$emit('addTheme');
    });
    this.$root.$on('makeUpdateTheme', (id) => {
      this.$root.$emit('updateTheme', id);
    });
    this.$root.$on('makeShowTutos', (id) => {
      this.$root.$emit('showTutos', id);
    });
    this.$on('keyword-changed', (keyword) => {
      this.keyword = keyword;
    });

  },

  methods: {
    prepareDelete(id) {
      this.deleteId = id;
      this.$refs.confirmDialog.open();
    },
    deleteTheme() {
      return this.$themeService.deleteTheme(this.deleteId)
        .then(() => {
          this.confirmDialog = false;
          this.successBar = true;
          this.color = 'success';
          this.timeout = 3000;
          this.text = this.$t('addon.elearning.theme.deleted');
          this.$root.$emit('themeDeleted');
        })
        .catch((e) => {
          console.error('Error deleting theme', e);
          this.confirmDialog = false;
          this.successBar = true;
          this.color = 'error';
          this.timeout = 5000;
          this.text = this.$t('addon.elearning.theme.delete.fail');
        });
    }
  }
};
</script>