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
        <div>
          <h2> {{ $t('addon.elearning.tutorial.details') }}  </h2>
        </div>
        <v-form ref="form">
          <v-text-field
            v-if="this.title"
            class="primary_tutorial_input"
            outlined
            clearable
            :label="$t('addon.elearning.tutorial.label.title')"
            name="title"
            v-model="tutoA.title" />
          <v-text-field
            v-else
            class="primary_tutorial_input"
            outlined
            clearable
            :label="$t('addon.elearning.tutorial.label.title')"
            name="title"
            v-model="tutoUp.title" />
          <extended-textarea
            v-if="this.title"
            :placeholder="$t('addon.elearning.tutorial.label.description')"
            :max-length="2000"
            name="description"
            v-model="tutoA.description" />
          <extended-textarea
            v-else
            :placeholder="$t('addon.elearning.tutorial.label.description')"
            :max-length="2000"
            name="description"
            v-model="tutoUp.description" />
          <v-text-field
            v-if="this.title"
            outlined
            clearable
            :label="$t('addon.elearning.tutorial.label.author')"
            name="author"
            v-model="tutoA.author" />
          <v-text-field
            v-else
            outlined
            clearable
            :label="$t('addon.elearning.tutorial.label.author')"
            name="author"
            v-model="tutoUp.author" />
          <v-select
            v-if="this.title"
            outlined
            :items="status"
            v-model="tutoA.status"
            :label="$t('addon.elearning.tutorial.label.status')"
            name="status" />
          <v-select
            v-else
            outlined
            :items="status"
            v-model="tutoUp.status"
            :label="$t('addon.elearning.tutorial.label.status')"
            name="status" />
        </v-form>
      </template>
      <template slot="footer">
        <v-btn
          class="exo_primary_btn"
          v-if="this.title"
          @click="tutoPost">
          {{ $t('addon.elearning.tutorial.confirm') }}
        </v-btn>
        <v-btn
          class="exo_primary_btn"
          v-else
          @click="tutoUpdate">
          {{ $t('addon.elearning.tutorial.confirm') }}
        </v-btn>
        <v-btn class="exo_cancel_btn" @click="$refs.form.reset()">{{ $t('addon.elearning.tutorial.clear') }}</v-btn>
      </template>
    </exo-drawer>
  </v-app>
</template>
<script>
export default {
  
  data () {
    return {
      title: false,
      tutoId: null,
      tutoU: null,
      errors: [],
      status: ['DRAFT','PUBLISHED','ARCHIVED'],
      tutoA: {
        title: null,
        description: null,
        author: null,
        status: null
      },
      tutoUp: {
        id: 0,
        title: null,
        description: null,
        author: null,
        status: null
      }
    };
  },
  
  created() {
    this.$root.$on('addTuto', () => {
      this.title = true;
      this.$refs.tutorialManagementDrawer.open();
    });
    this.$root.$on('updateTuto', (id) => {
      this.title = false;
      this.$refs.tutorialManagementDrawer.open();
      this.tutoId=id;
      this.getTutoU(id);
    });
  },

  methods: {
    tutoPost() {
      return this.$tutoService.tutoPost(this.tutoA)
        .then(() => {this.$root.$emit('createTuto');})
        .then(() =>{ 
          this.$refs.form.reset();
          this.$refs.tutorialManagementDrawer.close(); })
        .catch((e) => this.errors.push(e));
      
    },

    tutoUpdate() {
      this.tutoUp.id=this.tutoId;
      return this.$tutoService.tutoUpdate(this.tutoUp)
        .then(() => {this.$root.$emit('tutoUpdated');})
        .then(() => {
          this.tutoUp.id = 0;
          this.$refs.form.reset();
          this.$refs.tutorialManagementDrawer.close();})
        .catch((e) => this.errors.push(e));      
    },

    getTutoU(id) {
      return this.$tutoService.getTuto(id)
        .then((data) => {this.tutoU = data;
          this.tutoUp.title = this.tutoU.title;
          this.tutoUp.description = this.tutoU.description;
          this.tutoUp.author = this.tutoU.author;
          this.tutoUp.status = this.tutoU.status;})
        .catch((e) => this.errors.push(e));
    },
  }
};

</script>