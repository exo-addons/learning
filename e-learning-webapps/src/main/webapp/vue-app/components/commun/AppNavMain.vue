<!-- it is the First interface: it contains logo app + button create course-->
<template>
  <div class="team">
    <v-container class="my-5 backgroundTop" elevation-10>
      <v-layout>
        <v-flex md6 lg6>
          <a href="http://127.0.0.1:8080/portal/intranet/elearning#/">
            <v-img

              src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-300.png"
              aspect-ratio="1"
              width="150px"
              height="150px"
              class="img_logo" />
          </a>
        </v-flex>
        <v-flex md6 lg6>
          <h1 class=".font-weight-medium mb-4 .display-3 text-sm-left white--text">Elearning eXo</h1>
          <br>
          <h3 class=".font-weight-medium mb-4  .display-3 text-sm-left white--text">"We need to bring learning to people instead of people to learning." <br><p class=" text-md-right">Elliot Masie</p></h3>
        </v-flex>
        <v-flex>
          <v-btn
            flat
            class="depressed white large blue--text"
            @click="toCreateCourse">
            <i class="fas fa-plus"></i>
            <span class="title text-sm-left">Créer Cours</span>
          </v-btn>
        </v-flex>
        <v-flex>
          <v-btn
                  flat
                  class="depressed white large blue--text"
                  @click="toCreateExam">
            <i class="fas fa-plus"></i>
            <span class="title text-sm-left">Créer Examen</span>
          </v-btn>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>
<script>

    export default {
        name: 'App',
        data() {
            return {
           }
        },
        methods:{
            toCreateCourse:function() {
                this.$router.push('/createCours')
            },
            toCreateExam:function() {
                this.$router.push('/createExam')
            }
        }
    }

</script>
<style>
    .img_logo{
        border-radius: 50%;
        border: 10px solid transparent;
        padding: 15px;
    }

    .backgroundTop{
        background:linear-gradient(to bottom, #1867c0, #5CBBF6);
      font-family: 'Avenir', Helvetica, Arial, sans-serif;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
      text-align: center;
      color: #2c3e50;
    }
</style>


