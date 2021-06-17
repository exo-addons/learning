<template>
  <v-app
    id="tutorials_management"
    flat>
    <exo-drawer ref="tutorialManagementDrawer" right>
      <template v-if="this.title" slot="title">
        {{ $t('addon.elearning.tutorial.creating') }}     
      </template>
      <template v-else slot="title">
        {{ $t('addon.elearning.tutorial.updateF') }}     
      </template>
      <template slot="content">
        <template>
          <div v-show="this.title">
            <v-stepper
              v-model="add_steps"
              vertical
              class="tutorial_steps_wrapper">
              <v-stepper-step
                :complete="add_steps > 1"
                step="1">
                {{ $t('addon.elearning.tutorial.details') }}
                <small v-show="add_steps === 2 && tutoA.title != null && tutoA.description != null" class="stepper_label">{{ tutoA.title }} : {{ tutoA.description }}</small>
              </v-stepper-step>

              <v-stepper-content step="1">
                <label class="tuto_title_label" for="title">
                  {{ $t('addon.elearning.theme.label.title') }}  
                </label>
                <v-text-field
                  v-if="this.title"
                  class="primary_tutorial_input"
                  clearable
                  :placeholder="$t('addon.elearning.tutorial.label.title')"
                  name="title"
                  v-model="tutoA.title" />
                <label class="tuto_description_label" for="description">
                  {{ $t('addon.elearning.tutorial.label.description') }}  
                </label>
                <extended-textarea
                  class="primary_text_area_input"
                  v-if="this.title"
                  :placeholder="$t('addon.elearning.tutorial.label.description')"
                  :max-length="255"
                  name="description"
                  v-model="tutoA.description" />
                <v-btn
                  class="tutorial_stepper_next_btn"
                  @click="add_steps = 2"
                  :disabled="!isFirstStepComplete">
                  {{ $t('addon.elearning.tutorial.stepper.continue') }}
                  <v-icon class="tutorial_stepper_next_btn_icon">mdi-arrow-right</v-icon>
                </v-btn>
              </v-stepper-content>

              <v-stepper-step
                :complete="add_steps > 2"
                step="2">
                {{ $t('addon.elearning.tutorial.stepper.themes') }}
              </v-stepper-step>

              <v-stepper-content step="2">
                <template>
                  <v-container fluid id="themes_checkbox_container">
                    <v-checkbox
                      v-for="theme in themes"
                      v-model="tutoA.themeIds"
                      :key="theme.id"
                      :id="'theme-'+theme.id"
                      :label="theme.name"
                      :value="theme.id" />
                  </v-container>
                </template>
                <v-btn
                  class="tutorial_stepper_back_btn"
                  @click="add_steps = 1">
                  <v-icon class="tutorial_stepper_back_btn_icon">mdi-arrow-left</v-icon>
                  {{ $t('addon.elearning.tutorial.stepper.back') }}
                </v-btn>
              </v-stepper-content>
            </v-stepper>
          </div>
          <!--
          <div v-show="!this.title">
            <v-stepper
              v-model="up_steps"
              vertical
              class="tutorial_steps_wrapper">
              <v-stepper-step
                :complete="up_steps > 1"
                step="1">
                {{ $t('addon.elearning.tutorial.details') }}
                <small v-show="up_steps === 2 && tutoUp.title != null && tutoUp.description != null" class="stepper_label">{{ tutoUp.title }} : {{ tutoUp.description }}</small>
              </v-stepper-step>

              <v-stepper-content step="1">
                <label class="tuto_title_label" for="title">
                  {{ $t('addon.elearning.theme.label.title') }}  
                </label>
                <v-text-field
                  v-if="this.title"
                  class="primary_tutorial_input"
                  clearable
                  :placeholder="$t('addon.elearning.tutorial.label.title')"
                  name="title"
                  v-model="tutoUp.title" />
                <label class="tuto_description_label" for="description">
                  {{ $t('addon.elearning.tutorial.label.description') }}  
                </label>
                <extended-textarea
                  class="primary_text_area_input"
                  v-if="this.title"
                  :placeholder="$t('addon.elearning.tutorial.label.description')"
                  :max-length="255"
                  name="description"
                  v-model="tutoUp.description" />
                <v-btn
                  class="tutorial_stepper_next_btn"
                  @click="up_steps = 2"
                  :disabled="!isFirstStepComplete">
                  {{ $t('addon.elearning.tutorial.stepper.continue') }}
                  <v-icon class="tutorial_stepper_next_btn_icon">mdi-arrow-right</v-icon>
                </v-btn>
              </v-stepper-content>

              <v-stepper-step
                :complete="up_steps > 2"
                step="2">
                {{ $t('addon.elearning.tutorial.stepper.themes') }}
              </v-stepper-step>

              <v-stepper-content step="2">
                <template>
                  <v-container fluid id="themes_checkbox_container">
                    <v-checkbox
                      v-for="theme in themes"
                      v-model="tutoUp.themeIds"
                      :key="theme.id"
                      :id="'theme-'+theme.id"
                      :label="theme.name"
                      :value="theme.id" />
                  </v-container>
                </template>
                <v-btn
                  class="tutorial_stepper_back_btn"
                  @click="up_steps = 1">
                  <v-icon class="tutorial_stepper_back_btn_icon">mdi-arrow-left</v-icon>
                  {{ $t('addon.elearning.tutorial.stepper.back') }}
                </v-btn>
              </v-stepper-content>
            </v-stepper>
          </div>
          -->
        </template>
      </template>
      <template slot="footer">
        <v-btn
          class="tuto_drawer_btn_add"
          v-if="this.title"
          @click="tutoUpdate"
          :disabled="!isStepsComplete">
          {{ $t('addon.elearning.tutorial.confirm') }}
        </v-btn>
        <v-btn class="exo_cancel_btn" @click="clear">{{ $t('addon.elearning.tutorial.clear') }}</v-btn>
      </template>
    </exo-drawer>
  </v-app>
</template>
<script>
export default {
  
  data () {
    return {
      add_steps: 1,
      up_steps: 1,
      title: false,
      tutoId: null,
      tutoU: null,
      errors: [],
      status: ['DRAFT','PUBLISHED','ARCHIVED'],
      themes: [],
      tutoA: {
        title: null,
        description: null,
        themeIds: []
      },
      tutoUp: {
        id: 0,
        title: null,
        description: null,
        themeIds: []
      }
    };
  },

  computed: {
    isStepsComplete () {
      return this.tutoA.title && this.tutoA.description && Object.keys(this.tutoA.themeIds).length > 0;
    },
    isFirstStepComplete () {
      return this.tutoA.title;
    }
  },
  
  created() {
    this.getThemes();
    this.$root.$on('addTuto', () => {
      this.title = true;
      this.$refs.tutorialManagementDrawer.open();
    });
    this.$root.$on('makeUpdateTuto', (id) => {
      this.$refs.tutorialManagementDrawer.open();
      this.title = false; 
      this.tutoId=id;
      this.getTutoU(id);
    });
  },

  methods: {
    tutoPost() {
      return this.$tutoService.tutoPost(this.tutoA)
        .then(() => {this.$root.$emit('tutoCreated');})
        .then(() =>{
          this.clear();
          this.$refs.tutorialManagementDrawer.close(); })
        .catch((e) => this.errors.push(e));
      
    },

    tutoUpdate() {
      this.tutoUp.id=this.tutoId;
      return this.$tutoService.tutoUpdate(this.tutoUp)
        .then(() => {this.$root.$emit('tutoUpdated');})
        .then(() => {
          this.tutoUp.id = 0;
          this.clear();
          this.$refs.tutorialManagementDrawer.close();})
        .catch((e) => this.errors.push(e));      
    },

    getTutoU(id) {
      return this.$tutoService.getTutoById(id)
        .then((data) => {this.tutoU = data;
          this.tutoUp.title = this.tutoU.title;
          this.tutoUp.description = this.tutoU.description;
          this.tutoUp.themeIds=this.tutoU.themeIds;})
        .catch((e) => this.errors.push(e));
    },
    
    getThemes() {
      return this.$themeService.getThemes()
        .then((data) => {(this.themes = data);})
        .catch((e) => this.errors.push(e));
    },
    clear(){
      this.tutoA.title=null;
      this.tutoA.description=null;
      this.tutoA.themeIds=null;
    }
  }
};

</script>