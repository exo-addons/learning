<template>
  <v-app
    id="themes_display"
    flat>
    <template>
      <v-container class="theme_items_grid">
        <v-row>
          <v-col
            cols="12"
            sm="3"
            md="2">
            <v-btn
              class="exo_primary_btn"
              @click="createTheme">
              {{ $t('addon.elearning.theme.create') }}
            </v-btn>
          </v-col>
        </v-row>
        <v-row>
          <v-col
            cols="12"
            sm="3"
            md="2"
            v-for="theme in themeList"
            :key="theme.id">
            <v-card
              class="theme_items"
              :id="`theme-${theme.id}`">
              <v-card-title>
                <span
                  class="headline" 
                  @click="showTutos(theme.id)">{{ theme.name }}</span>              
                <v-spacer />
               
                <i
                  icon
                  small
                  class="uiIconVerticalDots d-flex"
                  @click="displayActionMenu = true">
                </i>
              
                <v-menu
                  v-model="displayActionMenu"
                  :attach="`#theme-${theme.id}`"
                  transition="slide-x-reverse-transition"
                  offset-y>
                  <v-list class="pa-0" dense>
                    <v-list-item class="menu-list" @click="update(theme.id)">
                      <v-list-item-title class="subtitle-2">
                        <v-icon>mdi-pencil</v-icon>
                        <span>{{ $t('addon.elearning.tutorial.update') }}</span>
                      </v-list-item-title>
                    </v-list-item>
                    <v-list-item class="draftButton" @click="prepareDelete(theme.id)">
                      <v-list-item-title class="subtitle-2">
                        <v-icon>mdi-delete</v-icon>
                        <span>{{ $t('addon.elearning.tutorial.delete') }}</span>
                      </v-list-item-title>
                    </v-list-item>
                  </v-list>
                </v-menu>
              </v-card-title>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </template>
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
      displayActionMenu: false,
      successBar: false,
      text: '',
      color: 'success',
      deleteId: null,
      themeList: [],
      errors: [],
    };
  },

  mounted() {
    this.getThemes();
    this.$root.$on('createTheme', () => {
      this.successBar=true;
      this.text=this.$t('addon.elearning.theme.created');
      this.getThemes();
    });
    this.$root.$on('themeUpdated', () => {
      this.successBar=true;
      this.text=this.$t('addon.elearning.theme.updated');
      this.getThemes();
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
    getThemes() {
      return this.$themeService.getThemes()
        .then((data) => {(this.themeList = data);})
        .catch((e) => this.errors.push(e));
    },
    prepareDelete(id){
      this.deleteId=id;
      this.$refs.confirmDialog.open();
    },
    deleteTheme() {
      return this.$themeService.deleteTheme(this.deleteId)
        .then(() => {this.getThemes();
          this.confirmDialog=false;
          
          this.successBar=true;
          this.text=this.$t('addon.elearning.theme.deleted');})
        .catch((e) => this.errors.push(e));
    },
    update(id){
      this.$root.$emit('updateTheme', id);
    },
    showTutos(id){
      this.$root.$emit('showTutos', id);
    },
    createTheme(){
      this.$root.$emit('addTheme');
    }
  }  
};
</script>