<template>
  <v-app
    id="tutorials_dashboard"
    flat>
    <tutorial-dashboard-toolbar :theme-name="this.themeName" />
    <tutorial-card-list />
    <exo-confirm-dialog
      ref="confirmDialog"
      :message="$t('addon.elearning.tutorial.deleteConf')"
      :title="$t('addon.elearning.tutorial.confirmD')"
      :ok-label="$t('addon.elearning.tutorial.confirm')"
      :cancel-label="$t('addon.elearning.tutorial.cancel')"
      @ok="deleteTuto()" />
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
      themeId: null,
      themeName: null,
      errors: []
    };
  },

  created() {
    this.$root.$on('tutoCreated', () => {
      this.successBar=true;
      this.text=this.$t('addon.elearning.tutorial.created');
    });
    this.$root.$on('tutoUpdated', () => {
      this.successBar=true;
      this.text=this.$t('addon.elearning.tutorial.updated');
    });
    this.$root.$on('setId', (id) => {
      this.themeId=id;
      this.getThemeName();
    });
    this.$root.$on('deleteTuto', (id) => {
      this.prepareDelete(id);
    });
    this.$root.$on('makeTuto', () => {
      this.$root.$emit('addTuto');
    });
    this.$root.$on('backtoThemes', () => {
      this.$root.$emit('backThemes');
    });

    $(document).on('mousedown', () => {
      if (this.displayActionMenu) {
        window.setTimeout(() => {
          this.displayActionMenu = false;
        }, 200);
      }
    });


  },

  methods: {
    prepareDelete(id){
      this.deleteId=id;
      this.$refs.confirmDialog.open();
    },
    deleteTuto() {
      return this.$tutoService.deleteTuto(this.deleteId)
        .then(() => {
          this.confirmDialog=false;
          
          this.successBar=true;
          this.text=this.$t('addon.elearning.tutorial.deleted');
          this.$root.$emit('tutoDeleted');})
        .catch((e) => this.errors.push(e));
    },
    getThemeName() {
      return this.$themeService.getThemeById(this.themeId)
        .then((data) => {this.themeName= data.name;})
        .catch((e) => this.errors.push(e));
    }
  }  
};
</script>