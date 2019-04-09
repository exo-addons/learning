<template>
  <div elevation-20>
    <md-chips
      v-model="courses"
      class="md-accent"
      md-placeholder="Add Cours..."
      :md-format="formatName">
      <label>Cours</label>
      <div class="md-helper-text">Cherchez les course disponibles </div>
    </md-chips>
  </div>
</template>

<script>

    import VueMaterial from 'vue-material'
    import 'vue-material/dist/vue-material.min.css'
    import 'vue-material/dist/theme/default.css'


    Vue.use(VueMaterial)
    export default {
        name: 'Format',
        data: () => ({
        courses: [
            'Dev',
            'C++',
            'Design',
            'Finance',
            'GRH'
        ]
    }),
    methods: {

        formatName (str) {
            let words = str.split(' ').filter(str => str !== '')
            // remove accents / diacritics
            words = words.map(str => str.normalize('NFD').replace(/[\u0300-\u036f]/g, ''))
            // capitalize
            words = words.map(str => str[0].toUpperCase() + str.slice(1))
            return words.join(' ')
        }
    }
    }
</script>

