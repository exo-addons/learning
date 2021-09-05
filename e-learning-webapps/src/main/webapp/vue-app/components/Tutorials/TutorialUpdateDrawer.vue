<template>
  <v-app
    id="tutorial_update"
    flat>
    <exo-drawer 
      id="tutorialUpdateDrawer"
      ref="tutorialUpdateDrawer"
      right>
      <template slot="title">
        {{ $t('addon.elearning.tutorial.updateF') }}     
      </template>
      <template slot="content">
        <v-form ref="form">
          <div id="id_update_form">
            <label class="tuto_title_label" :for="tutoUp.title">
              {{ $t('addon.elearning.theme.label.title') }}  
            </label>
            <v-text-field
              class="primary_tutorial_input"
              clearable
              :placeholder="$t('addon.elearning.tutorial.label.title')"
              name="title"
              v-model="tutoUp.title" />
            <label class="tuto_description_label" :for="tutoUp.description">
              {{ $t('addon.elearning.tutorial.label.description') }}  
            </label>
            <extended-textarea
              class="primary_text_area_input"
              :placeholder="$t('addon.elearning.tutorial.label.description')"
              :max-length="255"
              name="description"
              v-model="tutoUp.description" />
          </div>
        </v-form>
      </template>
      <template slot="footer">
        <v-btn
          class="tuto_drawer_btn_update"
          @click="tutoUpdate"
          :disabled="!isFormComplete">
          {{ $t('addon.elearning.tutorial.confirm') }}
        </v-btn>
        <v-btn class="exo_cancel_btn" @click="$refs.form.reset()">{{ $t('addon.elearning.tutorial.clear') }}</v-btn>
      </template>
    </exo-drawer>
  </v-app>
</template>
<script>
export default {
  data() {
    return {
      tutoId: null,
      tutoU: null,
      errors: [],
      themes: [],
      tutoUp: {
        id: 0,
        title: null,
        description: null,
        status: null,
        themeIds: [],
        author: null
      }
    };
  },
  computed: {
    isFormComplete() {
      return this.tutoUp.title;
    }
  },

  created() {
    this.$root.$on('makeUpdateTuto', (id) => {
      this.$refs.tutorialUpdateDrawer.open();
      this.tutoId = id;
      this.getTutoU(id);
    });
  },

  methods: {
    tutoUpdate() {
      this.tutoUp.id = this.tutoId;
      return this.$tutoService.tutoUpdate(this.tutoUp)
        .then(() => {
          this.$root.$emit('tutoUpdated');
        })
        .then(() => {
          this.tutoUp.id = 0;
          this.$refs.tutorialUpdateDrawer.close();
        })
        .catch((e) => this.errors.push(e));
    },

    getTutoU(id) {
      return this.$tutoService.getTutoById(id)
        .then((data) => {
          this.tutoU = data;
          this.tutoUp.title = this.tutoU.title;
          this.tutoUp.description = this.tutoU.description;
          this.tutoUp.status = this.tutoU.status;
          this.tutoUp.themeIds = this.tutoU.themeIds;
          this.tutoUp.author = this.tutoU.author;
        })
        .catch((e) => this.errors.push(e));
    },
  }
};
</script>