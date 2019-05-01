<template>
  <v-layout wrap>
    <v-flex xs12>
      <v-combobox
        v-model="select"
        multiple
        append-icon
        chips
        deletable-chips
        class="tag-input"
        :search-input.sync="search"
        @keyup.tab="updateTags"
        @paste="updateTags" />
    </v-flex>
  </v-layout>
</template>
<script>
    export default {
        data() {
            return {
                select: ['add-tags-with', 'enter', 'tab', 'paste'],
                items: [],
                search: "" //sync search
            };
        },
        methods: {
            updateTags() {
                this.$nextTick(() => {
                    this.select.push(...this.search.split(","));
                this.$nextTick(() => {
                    this.search = "";
            });
            });
            }
        }
    }
</script>
<style>

  .tag-input span.v-chip {
    background-color: #1867c0;
    color: #fff;
    font-size:1em;
    padding-left:7px;
  }

  .tag-input span.v-chip::before {
    content: "label";
    font-family: 'Material Icons';
    font-weight: normal;
    font-style: normal;
    font-size: 20px;
    line-height: 1;
    letter-spacing: normal;
    text-transform: none;
    display: inline-block;
    white-space: nowrap;
    word-wrap: normal;
    direction: ltr;
    -webkit-font-feature-settings: 'liga';
    -webkit-font-smoothing: antialiased;
  }
</style>
