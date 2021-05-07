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
        <v-row>
          <v-col
            cols="12"
            sm="3"
            md="2"
            v-for="tuto in tutoList"
            :key="tuto.id">
            <v-hover v-slot="{ hover }">
              <v-card
                class="tuto_items">
                <v-card-title>
                  <v-list-item-avatar v-if="tuto.status== 'DRAFT'" color="orange" />
                  <v-list-item-avatar v-else-if="tuto.status== 'PUBLISHED'" color="green" />
                  <v-list-item-avatar v-else color="grey" />
                  <span class="headline">{{ tuto.title }}</span>
                </v-card-title>
              
                <v-img 
                  class="tuto_list_img"
                  src="https://cdn.vuetifyjs.com/images/parallax/material2.jpg">
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
                            @click="update(tuto.id)">
                            <v-icon>mdi-pencil</v-icon>
                            <div class="mx-1"></div>
                            {{ $t('addon.elearning.tutorial.update') }}
                          </v-btn>
                        </v-list-item>
                        <v-list-item link>
                          <v-btn
                            text
                            color="blue"
                            @click="display(tuto.id)">
                            <v-icon>mdi-eye</v-icon>
                            <div class="mx-1"></div>
                            {{ $t('addon.elearning.tutorial.display') }}
                          </v-btn>
                        </v-list-item>
                        <v-list-item link>
                          <v-btn
                            text
                            color="red"
                            @click="prepareDelete(tuto.id)">
                            <v-icon>mdi-delete</v-icon>
                            <div class="mx-1"></div>
                            {{ $t('addon.elearning.tutorial.delete') }}
                          </v-btn>
                        </v-list-item>
                      </v-list>
                    </div>
                  </v-expand-transition>
                </v-img>
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
            </v-hover>
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
      text: '',
      color: 'success',
      deleteId: null,
      tutoList: [],
      createdate: [],
      errors: [],
      dateTimeFormat: {
        day: 'numeric',
        month: 'numeric',
        year: 'numeric',       
      },
    };
  },

  mounted() {
    this.getTutos();
    this.$root.$on('createTuto', () => {
      this.successBar=true;
      this.text=this.$t('addon.elearning.tutorial.created');
      this.getTutos();
    });
    this.$root.$on('tutoUpdated', () => {
      this.successBar=true;
      this.text=this.$t('addon.elearning.tutorial.updated');
      this.getTutos();
    });

  },

  methods: {
    getTutos() {
      return this.$tutoService.getTutos()
        .then((data) => {(this.tutoList = data);})
        .catch((e) => this.errors.push(e));
    },
    prepareDelete(id){
      this.deleteId=id;
      this.$refs.confirmDialog.open();
    },
    deleteTuto() {
      return this.$tutoService.deleteTuto(this.deleteId)
        .then(() => {this.getTutos();
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
    createTuto(){
      this.$root.$emit('addTuto');
    }
  }  
};
</script>