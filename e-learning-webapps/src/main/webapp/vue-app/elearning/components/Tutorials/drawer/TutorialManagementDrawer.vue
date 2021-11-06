<template>
  <div id="tutorials_management">
    <exo-drawer 
      id="tutorialManagementDrawer"
      ref="tutorialManagementDrawer"
      right>
      <template slot="title">
        {{ $t('addon.elearning.tutorial.creating') }}     
      </template>
      <template slot="content">
        <template>
          <v-stepper
            v-model="add_steps"
            vertical
            class="tutorial_steps_wrapper">
            <v-stepper-step
              :complete="add_steps > 1"
              step="1">
              {{ $t('addon.elearning.tutorial.details') }}
              <small v-show="add_steps === 2 && tutorial.title != null && tutorial.description != null" class="stepper_label">{{ tutorial.title }} : {{ tutorial.description }}</small>
            </v-stepper-step>

            <v-stepper-content step="1">
              <label class="tuto_title_label" :for="tutorial.title">
                {{ $t('addon.elearning.theme.label.title') }}  
              </label>
              <v-text-field
                class="primary_tutorial_input"
                clearable
                :placeholder="$t('addon.elearning.tutorial.label.title')"
                name="title"
                v-model="tutorial.title" />
              <label class="tuto_description_label" :for="tutorial.description">
                {{ $t('addon.elearning.tutorial.label.description') }}  
              </label>
              <extended-textarea
                class="primary_text_area_input"
                :placeholder="$t('addon.elearning.tutorial.label.description')"
                :max-length="255"
                name="description"
                v-model="tutorial.description" />
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
                    v-model="tutorial.themeIds"
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
        </template>
      </template>
      <template slot="footer">
        <v-btn
          class="tuto_drawer_btn_add"
          @click="createOrUpdateTutorial"
          :disabled="!isStepsComplete">
          {{ $t('addon.elearning.tutorial.confirm') }}
        </v-btn>
        <v-btn class="exo_cancel_btn" @click="clear">{{ $t('addon.elearning.tutorial.clear') }}</v-btn>
      </template>
    </exo-drawer>
  </div>
</template>
<script>
export default {
  props: {
    tutorial: {
      type: Object,
      default: null
    },
    parentTheme: {
      type: Object,
      default: null
    },
    spaceName: {
      type: String,
      default: ''
    },
    space: {
      type: Object,
      default: null
    },
  },  
  data() {
    return {
      add_steps: 1,
      errors: [],
      status: ['DRAFT', 'PUBLISHED', 'ARCHIVED'],
      themes: [],
    };
  },
  computed: {
    isStepsComplete() {
      return this.tutorial && this.tutorial.title && this.tutorial.themeIds && this.tutorial.themeIds.length > 0;
    },
    isFirstStepComplete() {
      return this.tutorial && this.tutorial.title;
    }
  },
  methods: {
    createOrUpdateTutorial() {
      if (!this.tutorial.id) {
        let tutorialId;
        return this.$tutoService.addTutorial(this.tutorial).then(addedTutorial => {
          tutorialId = addedTutorial.id;
          this.$emit('tutorialAdded', addedTutorial);
          const tutorialIds = this.parentTheme.tutorialIds;
          tutorialIds.push(addedTutorial.id);
          const updatedParent = {
            id: this.parentTheme.id,
            name: this.parentTheme.name,
            spaceName: this.parentTheme.spaceName,
            managers: this.parentTheme.managers,
            participators: this.parentTheme.participators,
            parentId: this.parentTheme.parentId,
            childrenIds: this.parentTheme.childrenIds,
            lastModifiedDate: this.parentTheme.lastModifiedDate,
            tutorialIds: tutorialIds,
            creator: this.parentTheme.creator,
          };
          this.$root.$emit('parent-theme-updated', updatedParent);
        }).catch((e) => this.errors.push(e)).finally(() => window.open(`${eXo.env.portal.context}/${eXo.env.portal.portalName}/elearning-editor?spaceId=${eXo.env.portal.spaceId}&tutorialId=${tutorialId}`, '_blank'));
      } else {
        return this.$tutoService.updateTutorial(this.tutorial).then(updatedTutorial => {
          this.$emit('tutorialUpdated', updatedTutorial);
        }).catch((e) => this.errors.push(e));
      }
    },
    getThemes() {
      const isRoot = !this.parentTheme;
      return this.$themeService.getThemes(this.spaceName, isRoot, '', 0, 0).then((data) => {
        this.themes = data.themeList;
        this.tutorial.themeIds.push(this.parentTheme.id);
      }).catch((e) => this.errors.push(e));
    },
    open() {
      this.getThemes();
      this.$refs.tutorialManagementDrawer.open();
    },
    close() {
      this.clear();
      this.$refs.tutorialManagementDrawer.close();
    },
    clear() {
      this.tutorial.title = null;
      this.tutorial.description = null;
      this.tutorial.themes = [];
      this.add_steps = 1;
    },
  }
};

</script>