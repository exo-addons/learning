<template>
  <div id="themes_dashboard">
    <theme-tutorial-mix-dashboard-toolbar
      :keyword="keyword"
      @keyword-changed="keyword = $event" />
    <theme-card-list 
      :space-name="spaceName"
      :keyword="keyword" />
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
    };
  },
  created() {
    this.$on('keyword-changed', (keyword) => {
      this.keyword = keyword;
    });
  },
};
</script>