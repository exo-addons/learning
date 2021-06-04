<template>
  <v-app
    id="tutorials_display"
    flat>
    <template>
      <v-container class="tuto_items_grid">
        <v-row>
          <v-col
            cols="12"
            sm="3"
            md="2">
            <v-btn
              class="exo_primary_btn"
              @click="createTuto">
              {{ $t('addon.elearning.tutorial.create') }}
            </v-btn>
          </v-col>
        </v-row>
        <v-row v-if="tutoListByTheme">
          <v-col
            cols="12"
            sm="3"
            md="2"
            v-for="tuto in tutoListByTheme"
            :key="tuto.id">
            <v-card
              class="tuto_items"
              :id="`tuto-${tuto.id}`">
              <v-card-title>
                <v-list-item-avatar v-if="tuto.status== 'DRAFT'" color="orange" />
                <v-list-item-avatar v-else-if="tuto.status== 'PUBLISHED'" color="green" />
                <v-list-item-avatar v-else color="grey" />
                <span class="headline">{{ tuto.title }}</span>
                <v-spacer />
                <i
                  icon
                  small
                  class="uiIconVerticalDots d-flex"
                  @click="displayActionMenu = true">
                </i>
                <v-menu
                  v-model="displayActionMenu"
                  :attach="`#tuto-${tuto.id}`"
                  transition="slide-x-reverse-transition"
                  offset-y>
                  <v-list class="pa-0" dense>
                    <v-list-item class="menu-list" @click="update(tuto.id)">
                      <v-list-item-title class="subtitle-2">
                        <v-icon>mdi-pencil</v-icon>
                        <span>{{ $t('addon.elearning.tutorial.update') }}</span>
                      </v-list-item-title>
                    </v-list-item>
                    <v-list-item class="menu-list" @click="display(tuto.id)">
                      <v-list-item-title class="subtitle-2">
                        <v-icon>mdi-eye</v-icon>
                        <span>{{ $t('addon.elearning.tutorial.display') }}</span>
                      </v-list-item-title>
                    </v-list-item>
                    <v-list-item class="draftButton" @click="prepareDelete(tuto.id)">
                      <v-list-item-title class="subtitle-2">
                        <v-icon>mdi-delete</v-icon>
                        <span>{{ $t('addon.elearning.tutorial.delete') }}</span>
                      </v-list-item-title>
                    </v-list-item>
                  </v-list>
                </v-menu>
              </v-card-title>
              <v-img 
                class="tuto_list_img"
                src="https://cdn.vuetifyjs.com/images/parallax/material2.jpg" />
              <v-list>
                <v-list-item>
                  <v-icon>mdi-account</v-icon>
                  <div class="mx-1"></div>
                  <v-list-item-content>
                    <v-list-item-title>{{ tuto.author }}</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
                <v-divider inset />                  
                <v-list-item>
                  <v-icon>mdi-calendar</v-icon>
                  <div class="mx-1"></div>
                  <v-list-item-content>
                    <v-list-item-title>
                      <div>
                        <date-format :value="tuto.createdDate.time" :format="dateTimeFormat" />
                      </div>
                    </v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
            </v-card>
          </v-col>
        </v-row>
        <v-row>
          <v-col
            cols="12"
            sm="3"
            md="2">
            <v-btn
              class="exo_primary_btn"
              @click="backThemes">
              {{ $t('addon.elearning.theme.back') }}
            </v-btn>
          </v-col>
        </v-row>
      </v-container>
    </template>
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
      displayActionMenu: false,
      text: '',
      color: 'success',
      deleteId: null,
      themeId: null,
      tutoList: [],
      tutoListByTheme: [],
      errors: [],
      dateTimeFormat: {
        day: 'numeric',
        month: 'numeric',
        year: 'numeric',       
      },
    };
  },

  mounted() {
    this.$root.$on('createTuto', () => {
      this.successBar=true;
      this.text=this.$t('addon.elearning.tutorial.created');
      this.getTutosByTheme(this.themeId);
    });
    this.$root.$on('tutoUpdated', () => {
      this.successBar=true;
      this.text=this.$t('addon.elearning.tutorial.updated');
      this.getTutosByTheme(this.themeId);
    });
    this.$root.$on('setId', (id) => {
      this.themeId=id;
      this.getTutosByTheme(this.themeId);
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
    getTutos() {
      return this.$tutoService.getTutos()
        .then((data) => {(this.tutoList = data);})
        .catch((e) => this.errors.push(e));
    },
    getTutosByTheme(id) {
      return this.$tutoService.getAllTutosByTheme(id)
        .then((data) => {(this.tutoListByTheme = data);})
        .catch((e) => this.errors.push(e));
    },
    prepareDelete(id){
      this.deleteId=id;
      this.$refs.confirmDialog.open();
    },
    deleteTuto() {
      return this.$tutoService.deleteTuto(this.deleteId)
        .then(() => {this.getTutosByTheme(this.themeId);
          this.confirmDialog=false;
          
          this.successBar=true;
          this.text=this.$t('addon.elearning.tutorial.deleted');})
        .catch((e) => this.errors.push(e));
    },
    update(id){
      this.$root.$emit('updateTuto', id);
    },
    display(id){
      this.$root.$emit('displayTuto', id);
    },
    backThemes(){
      this.$root.$emit('backThemes');
    },
    createTuto(){
      this.$root.$emit('addTuto');
    },
  }  
};
</script>