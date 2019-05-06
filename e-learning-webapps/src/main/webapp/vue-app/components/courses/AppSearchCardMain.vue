<template>
  <v-layout wrap>
    <v-flex xs12>
      <v-combobox
        v-model="searchWord"
        multiple
        append-icon
        chips
        deletable-chips
        class="tag-input"
        :search-input.sync="search"
        @click="searchCourse"
        @keyup.tab="updateTags"
        @paste="updateTags" />
    </v-flex>
  </v-layout>
</template>
<script>
    import axios from 'axios'

    export default {
        data() {
            return {
                searchWord:['seach'],
                courses: [],
                search: "" //sync search
            };
        },
        methods: {
            updateTags() {
                this.$nextTick(() => {
                    this.searchWord.push(...this.search.split(","));
                this.$nextTick(() => {
                    this.search = "";
            });
            });
            },
            searchCourse(){
                var x=this.searchWord;
                for(var i= 0; i < x.length; i++)
                {
                    var searching=x[x.length-1]
                }

                axios.get(`/portal/rest/cours/getCourse/`+searching).then((response) => {
                    this.courses = response.data;
                    console.log('ok',this.courses)
                }).catch(error => {
                    console.log(error)
                })

            }
        },
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
