<template>
  <v-app
    id="themes_dashboard"
    flat>
    <theme-dashboard-toolbar
      :keyword="keyword"
      @keyword-changed="keyword = $event" />
    <theme-card-list 
      :space-name="spaceName"
      :keyword="keyword" />
    <template>
      <div class="text-center">
        <v-snackbar
          v-model="successBar"
          :timeout="timeout"
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
  props: {
    spaceName: {
      type: String,
      default: ''
    },
  },
  
  data() {
    return {
      successBar: false,
      text: '',
      color: 'success',
      timeout: 0,
      errors: [],
      keyword: '',
    };
  },

  created() {
    this.$root.$on('makeShowTutos', (id) => {
      this.$root.$emit('showTutos', id);
    });
    this.$on('keyword-changed', (keyword) => {
      this.keyword = keyword;
    });
  },
};
</script>