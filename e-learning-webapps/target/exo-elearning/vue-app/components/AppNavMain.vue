<template>
  <div class="team">
    <v-container class="my-5 backgroundTop" elevation-20>
      <v-layout>
        <v-flex md3 lg3>
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
        <v-flex md6 lg6>
          <app-drop-main />
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>
<script>


    import AppDropMain from './AppDropMain.vue'


    export default {
        name: 'App',
        components:{AppDropMain},
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
    }
</style>


