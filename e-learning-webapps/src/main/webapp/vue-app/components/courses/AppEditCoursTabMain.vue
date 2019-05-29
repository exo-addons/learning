<template>
  <div class="dd">
    <ul class="nav nav-tabs userNavigation" style="visibility: visible;">
      <li class="item">
        <router-link to="/EditCoursPublished">
          <div class="link-text">Cours Disponibles</div>
        </router-link>
      </li>
      <li class="item">
        <router-link to="/EditMyDrafetCours">
          <div class="link-text">Mes Cours Crée</div>
        </router-link>
      </li>
      <li class="item">
        <router-link to="/EditCoursCard">
          <div class="link-text">Mes Cours Disponibles</div>
        </router-link>
      </li>
      <li class="item">
        <router-link to="/CoursTabform">
          <div class="link-text">Mes Cours Completés</div>
        </router-link>
      </li>
      <li class="item">
        <router-link to="/createExam">
          <div class="link-text">Créer Examen</div>
        </router-link>
      </li>
      <li class="item">
            <router-link to="/GestionQcm">
              <div class="link-text">
                <i class="fas fa-cog" slot="activator"></i>
                Configuration Examen</div>
            </router-link>
      </li>
      <li class="item">
        <router-link to="/gestionLesson">
          <div class="link-text">
            <i class="fas fa-cog" slot="activator"></i>
            Configuration Lesson</div>
        </router-link>
      </li>
    </ul>
  </div>
</template>
<script>
    import AppSearchQcm from '../exam/AppSearchQcm.vue'

    export default {

        components: {AppSearchQcm}
    }
</script>


<style>
  .dd{
    background-color: #476a9c;

  }
  .dd .nav-tabs > li > a:hover, .nav-tabs > li > a:focus {
    border-bottom: 4px solid #eeeeee;
    background: transparent;
    margin-bottom: -2px;
    height: 16px;
    border-right: none;
    border-left: none;
  }
  .conf-pop .v-dialog__container:hover {
    border-bottom: 4px solid #eeeeee;
    background: transparent;
    margin-bottom: 2px;
    height: 24px;
    border-right: none;
    border-left: none;
  }
  .link-text{
    color: #ffffff;
    font-family: Helvetica, arial, sans-serif;
    font-size: 14px;
    font-weight:bold;
  }
</style>

