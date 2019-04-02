<template>
  <div>
    <v-container>
      <v-layout>
        <v-flex>
          <v-menu offset-y>
            <v-btn
              slot="activator"
              flat
              class="depressed white large blue--text">
              <v-icon left>expand_more</v-icon>
              <span>Editeur</span>
            </v-btn>
            <v-list>
              <v-list-tile
                v-for="link in links1"
                :key="link.text"
                router
                :to="link.route">
                <v-list-tile-title>{{ link.text }}</v-list-tile-title>
              </v-list-tile>
            </v-list>
          </v-menu>
        </v-flex>

        <v-flex>
          <v-menu offset-y>
            <v-btn
              slot="activator"
              flat
              outline
              color="white">
              <v-icon left>expand_more</v-icon>
              <span>Apprenant</span>
            </v-btn>
            <v-list>
              <v-list-tile
                v-for="link in links2"
                :key="link.text"
                router
                :to="link.route">
                <v-list-tile-title>{{ link.text }}</v-list-tile-title>
              </v-list-tile>
            </v-list>
          </v-menu>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>
<script>

    export default {
        data: () => ({
        links1: [
            { icon: 'dashboard', text: 'Accueil', route: '/' },
            { icon: 'folder', text: 'Espace Cours', route: '/createCours' },
            { icon: 'person', text: 'Espace Group', route: '/createGroup' },
        ],
        links2: [
            { icon: 'dashboard', text: 'Dashboard', route: '/' },
            { icon: 'folder', text: 'Espace ', route: '/projects' },
            { icon: 'person', text: 'Team', route: '/team' },
        ]
    }),
    }
</script>
