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
            <v-hover v-slot="{ hover }">
              <v-card
                class="theme_items">
                <v-expand-transition>
                  <div
                    v-if="hover"
                    class="d-flex transition-fast-in-fast-out v-card--reveal display-3 white--text"
                    style="height: 100%;">
                    <v-list
                      color="rgba(0, 0, 0, 0.7)"
                      width="100%"
                      nav
                      dense>
                      <v-list-item link>
                        <v-btn
                          text
                          color="blue"
                          @click="update(theme.id)">
                          <v-icon>mdi-pencil</v-icon>
                          <div class="mx-1"></div>
                          {{ $t('addon.elearning.tutorial.update') }}
                        </v-btn>
                      </v-list-item>
                      <v-list-item link>
                        <v-btn
                          text
                          color="red"
                          @click="prepareDelete(theme.id)">
                          <v-icon>mdi-delete</v-icon>
                          <div class="mx-1"></div>
                          {{ $t('addon.elearning.tutorial.delete') }}
                        </v-btn>
                      </v-list-item>
                    </v-list>
                  </div>
                </v-expand-transition>

                <v-card-title>
                  <span class="headline">{{ theme.name }}</span>
                </v-card-title>
              </v-card>
            </v-hover>
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
    createTheme(){
      this.$root.$emit('addTheme');
    }
  }  
};
</script>