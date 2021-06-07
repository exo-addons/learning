<template>
  <v-app
    id="themes_dashboard"
    flat>
    <theme-dashboard-toolbar />
    <theme-card-list />    
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
          timeout="2000"
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
      deleteId: null,
      errors: [],
    };
  },

  created() {
    this.$root.$on('themeCreated', () => {
      this.successBar=true;
      this.text=this.$t('addon.elearning.theme.created');
    });
    this.$root.$on('themeUpdated', () => {
      this.successBar=true;
      this.text=this.$t('addon.elearning.theme.updated');
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

  },

  methods: {
    prepareDelete(id){
      this.deleteId=id;
      this.$refs.confirmDialog.open();
    },
    deleteTheme() {
      return this.$themeService.deleteTheme(this.deleteId)
        .then(() => {
          this.confirmDialog=false;
          
          this.successBar=true;
          this.text=this.$t('addon.elearning.theme.deleted');
          this.$root.$emit('themeDeleted');})
        .catch((e) => this.errors.push(e));
    }
  }  
};
</script>