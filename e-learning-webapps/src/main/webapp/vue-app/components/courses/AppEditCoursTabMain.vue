<template>
  <div class="elevation-2">
    <v-toolbar
      color="#5CBBF6"
      dark
      tabs>
      <template v-slot:extension>
        <v-tabs
          v-model="tab"
          color="#5CBBF6"
          align-with-title>
          <v-tabs-slider color="yellow" />

          <v-tab
            v-for="item in links"
            :key="item"
            router
            :to="item.route">
            {{ item.text }}
          </v-tab>
        </v-tabs>
      </template>
    </v-toolbar>

    <v-tabs-items v-model="tab">
      <v-tab-item
        v-for="link in links"
        :key="link.text">
        <v-card flat>
          <v-card-text>{{ link.text }}</v-card-text>
        </v-card>
      </v-tab-item>
    </v-tabs-items>
  </div>
</template>
<script>
    export default {
        data () {
            return {
                tab: null,
                links: [
                    { icon: 'dashboard', text: 'Cours Disponibles', route: '/EditCoursPublished' },
                    { icon: 'folder', text: 'Mes Cours Crée', route: '/EditMyDrafetCours' },
                    { icon: 'person', text: 'Mes Cours Disponibles', route: '/EditCoursCard' },
                    { icon: 'person', text: 'Mes Cours Completés', route: '/CoursTabform' },
                    { icon: 'dashboard', text: 'Créer Examen', route: '/createExam' },


                ]
            }
        }
    }
</script>

