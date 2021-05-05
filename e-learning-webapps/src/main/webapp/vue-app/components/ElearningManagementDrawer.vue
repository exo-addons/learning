<template>
  <v-app
    id="elearning_app"
    flat>
    <exo-drawer ref="elearningManagementDrawer" right>
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
        <v-form ref="form" v-model="form">
          <v-text-field
            v-if="this.title"
            outlined
            clearable
            :label="$t('addon.elearning.tutorial.label.title')"
            name="title"
            :rules="[validators.required, validators.length(10)]"
            v-model="tutoA.title" />
          <v-text-field
            v-else
            outlined
            clearable
            :label="$t('addon.elearning.tutorial.label.title')"
            name="title"
            :rules="[validators.required, validators.length(10)]"
            v-model="tutoUp.title" />
          <v-textarea
            v-if="this.title"
            outlined
            clearable
            auto-grow
            rows="3"
            :label="$t('addon.elearning.tutorial.label.description')"
            name="description"
            :rules="[validators.required, validators.length(25)]"          
            v-model="tutoA.description" />
          <v-textarea
            v-else
            outlined
            clearable
            auto-grow
            rows="3"
            :label="$t('addon.elearning.tutorial.label.description')"
            name="description"
            :rules="[validators.required, validators.length(25)]"          
            v-model="tutoUp.description" />
          <v-text-field
            v-if="this.title"
            outlined
            clearable
            :label="$t('addon.elearning.tutorial.label.author')"
            name="author"
            :rules="[validators.required]"
            v-model="tutoA.author" />
          <v-text-field
            v-else
            outlined
            clearable
            :label="$t('addon.elearning.tutorial.label.author')"
            name="author"
            :rules="[validators.required]"
            v-model="tutoUp.author" />
          <v-select
            v-if="this.title"
            outlined
            :items="status"
            :rules="[validators.required]"
            v-model="tutoA.status"
            :label="$t('addon.elearning.tutorial.label.status')"
            name="status" />
          <v-select
            v-else
            outlined
            :items="status"
            :rules="[validators.required]"
            v-model="tutoUp.status"
            :label="$t('addon.elearning.tutorial.label.status')"
            name="status" />
        </v-form>
      </template>
      <template slot="footer">
        <v-btn
          :disabled="!form"
          class="btn btn-primary"
          v-if="this.title"
          @click="tutoPost">
          {{ $t('addon.elearning.tutorial.confirm') }}
        </v-btn>
        <v-btn
          :disabled="!form"
          class="btn btn-primary"
          v-else
          @click="tutoUpdate">
          {{ $t('addon.elearning.tutorial.confirm') }}
        </v-btn>
        <v-btn class="btn mr-2" @click="$refs.form.reset()">{{ $t('addon.elearning.tutorial.clear') }}</v-btn>
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
      },
      validators: {
        required: v => !!v || 'This field is required',
        length: len => v => (v || '').length >= len || `Invalid length, required ${len}`,
      },
      form: false,
    };
  },
  
  created() {
    this.$root.$on('addTuto', () => {
      this.title = true;
      this.$refs.elearningManagementDrawer.open();
      this.$refs.form.reset();
    });
    this.$root.$on('updateTuto', (id) => {
      this.title = false;
      this.$refs.elearningManagementDrawer.open();
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
          this.$refs.elearningManagementDrawer.close(); })
        .catch((e) => this.errors.push(e));
      
    },

    tutoUpdate() {
      this.tutoUp.id=this.tutoId;
      return this.$tutoService.tutoUpdate(this.tutoUp)
        .then(() => {this.$root.$emit('tutoUpdated');})
        .then(() => {
          this.tutoUp.id = 0;
          this.$refs.form.reset();
          this.$refs.elearningManagementDrawer.close();})
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