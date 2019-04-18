<!-- it is the First interface: it contains logo app + button create course + other components App card main-->
<template>
  <div class="team">
    <v-container class="my-5 backgroundTop" elevation-20>
      <v-layout>
        <v-flex md6 lg6>
          <v-img

            src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-300.png"
            aspect-ratio="1"
            width="250px"
            height="250px"
            class="img_logo" />
        </v-flex>
        <v-flex md6 lg6>
          <h1 class=".font-weight-medium mb-4 .display-3 text-sm-left white--text">Elearning eXo</h1>
          <br>
          <h3 class="font-weight-medium mb-4 .display-3 text-sm-left white--text">"We need to bring learning to people instead of people to learning." <br><p class=" text-md-right">Elliot Masie</p></h3>
        </v-flex>
        <v-flex md12 lg12>
          <v-btn flat class="depressed white large blue--text">
            <i class="fas fa-plus"></i>
            <span class="title text-sm-left">Create Cours</span></v-btn>
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


