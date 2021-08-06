<template>
  <v-app
    id="tutos_card_list"
    flat>
    <template>
      <v-container> 
        <v-row class="tuto_cards_row border-box-sizing"> 
          <v-col
            class="tuto_cards"
            cols="12"
            md="6"
            lg="4"
            xl="3"
            v-for="tuto in tutoListByTheme"
            :key="tuto.id"
            :id="'tuto-'+tuto.id">
            <tutorial-card :tuto="tuto" :user="user" />
          </v-col>
        </v-row>
      </v-container>  
    </template> 
  </v-app>
</template>

<script>
export default {
  data () {
    return {
      user: null,
      themeId: null,
      tutoListByTheme: [],
      errors: [],
      dupTuto: null,
      archTurto: null,
      tutoD: {
        title: null,
        description: null,
        themeIds: [],
        status: null,
        author: null
      },
      tutoA: {
        id: 0,
        title: null,
        description: null,
        themeIds: [],
        status: null,
        author: null
      },
      newTitle: null
    };
  },

  props: {
    keyword: {
      type: String,
      default: null,
    }
  },

  watch: {
    keyword() {
      if (!this.keyword) {
        this.getTutosByTheme();
      }
      if (this.keyword) {
        this.getTutosByName();
      }

    }
  },

  created () {
    this.user=eXo.env.portal.userName;
    this.$root.$on('showTutos', (id) => {
      this.themeId=id;  
      this.getTutosByTheme(this.themeId);
      this.$root.$emit('getThemeId', id);
    });
    this.$root.$on('tutoCreated', () => {
      this.getTutosByTheme(this.themeId);
    });
    this.$root.$on('tutoUpdated', () => {
      this.getTutosByTheme(this.themeId);
    });
    this.$root.$on('tutoMoved', () => {
      this.getTutosByTheme(this.themeId);
    });
    this.$root.$on('tutoDeleted', () => {
      this.getTutosByTheme(this.themeId);
    });
    this.$root.$on('makeDupTuto', (id) => {
      this.getTutoDup(id);
    });
    this.$root.$on('makeArchTuto', (id) => {
      this.getTutoArch(id);
    });
    this.$root.$on('makeUnarchTuto', (id) => {
      this.getTutoUnarch(id);
    });
  },

  methods: {
    getTutosByTheme() {
      return this.$tutoService.getAllTutosByTheme(this.themeId)
        .then((data) => {
          this.tutoListByTheme.splice(0);
          this.tutoListByTheme = data;})
        .catch((e) => this.errors.push(e));
    },
    getTutosByName() {
      return this.$tutoService.getTutosByName(this.themeId,this.keyword)
        .then((data) => {
          this.tutoListByTheme.splice(0);
          this.tutoListByTheme = data;})
        .catch((e) => this.errors.push(e));
    },
    getTutoDup(id) {
      return this.$tutoService.getTutoById(id)
        .then((data) => {this.dupTuto = data;
          this.newTitle= this.$t('addon.elearning.tutorial.duplicate.text') ;
          this.tutoD.title = this.newTitle+this.dupTuto.title;
          this.tutoD.description = this.dupTuto.description;
          this.tutoD.status= 'DRAFT';
          this.tutoD.themeIds=this.dupTuto.themeIds;
          this.tutoD.author=this.dupTuto.author;
          this.duplicateTuto();
        })
        .catch((e) => this.errors.push(e));
    },
    duplicateTuto(){
      return this.$tutoService.tutoPost(this.tutoD)
        .then(() => {this.$root.$emit('tutoDuplicated');
          this.getTutosByTheme(this.themeId);})
        .catch((e) => this.errors.push(e));
    },
    getTutoArch(id) {
      return this.$tutoService.getTutoById(id)
        .then((data) => {this.archTurto = data;
          this.tutoA.id=this.archTurto.id;
          this.tutoA.title=this.archTurto.title;
          this.tutoA.description = this.archTurto.description;
          this.tutoA.status= 'ARCHIVED';
          this.tutoA.themeIds=this.archTurto.themeIds;
          this.tutoA.author=this.archTurto.author;
          this.archiveTuto();
        })
        .catch((e) => this.errors.push(e));
    },
    archiveTuto() {
      return this.$tutoService.tutoUpdate(this.tutoA)
        .then(() => {this.$root.$emit('tutoArchived');})
        .then(() => {
          this.getTutosByTheme(this.themeId);})
        .catch((e) => this.errors.push(e));      
    },
    getTutoUnarch(id) {
      return this.$tutoService.getTutoById(id)
        .then((data) => {this.archTurto = data;
          this.tutoA.id=this.archTurto.id;
          this.tutoA.title=this.archTurto.title;
          this.tutoA.description = this.archTurto.description;
          this.tutoA.status= 'PUBLISHED';
          this.tutoA.themeIds=this.archTurto.themeIds;
          this.tutoA.author=this.archTurto.author;
          this.unarchiveTuto();
        })
        .catch((e) => this.errors.push(e));
    },
    unarchiveTuto() {
      return this.$tutoService.tutoUpdate(this.tutoA)
        .then(() => {this.$root.$emit('tutoUnarchived');})
        .then(() => {
          this.getTutosByTheme(this.themeId);})
        .catch((e) => this.errors.push(e));      
    },
  }
};
</script>    