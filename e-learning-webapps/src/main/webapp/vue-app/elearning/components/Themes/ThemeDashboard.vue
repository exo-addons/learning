<template>
  <div id="themes_dashboard">
    <theme-dashboard-toolbar
      :keyword="keyword"
      @keyword-changed="keyword = $event" />
    <theme-card-list 
      :space-name="spaceName"
      :keyword="keyword" />
    <v-alert
      v-model="alert"
      :type="type"
      dismissible>
      {{ message }}
    </v-alert>
  </div>
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
      keyword: '',
      alert: false,
      type: '',
      message: '',
    };
  },
  created() {
    this.$on('keyword-changed', (keyword) => {
      this.keyword = keyword;
    });
    this.$root.$on('show-alert', message => {
      this.displayMessage(message);
    });
  },
  methods: {
    displayMessage(message) {
      this.message = message.message;
      this.type = message.type;
      this.alert = true;
      window.setTimeout(() => this.alert = false, 5000);
    },
  }
};
</script>