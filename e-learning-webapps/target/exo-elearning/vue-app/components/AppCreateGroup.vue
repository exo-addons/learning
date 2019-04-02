<template>
  <v-container class="my-5 backgroundTop" elevation-20>
    <v-stepper
      v-model="e1"
      flat
      class="text-xs-center ma-3 elevation-20"
      height="650px">
      <v-stepper-header>
        <v-stepper-step
          :complete="e1 > 1"
          step="1"
          color="blue">
          <h5 class="blue--text .headline">Ajouter Groupe</h5>
        </v-stepper-step>
        <v-stepper-step step="2" color="blue"><h5 class="blue--text .headline">Validation</h5></v-stepper-step>
      </v-stepper-header>

      <v-stepper-items>
        <v-stepper-content step="1">
          <v-card
            class="mb-5"
            height="350px">
            <v-card-text>
              <v-form ref="form" class="px-3">
                <v-container>
                  <v-flex md12 lg12>
                    <v-text-field
                      v-model="libelle"
                      label="Libellé de groupe"
                      prepend-icon="folder"
                      :rules="inputRules"
                      ma-5
                      pa-5 />
                  </v-flex>
                  <v-flex md12 lg12>
                    <div elevation-20>
                      <md-chips
                        v-model="apprenant"
                        class="md-accent"
                        md-placeholder="Ajouter apprenant..."
                        :md-format="formatName"
                        ma-5
                        pa-5>
                        <label>Nom Apprenant</label>
                        <div class="md-helper-text">Cherchez les apprenants</div>
                      </md-chips>
                    </div>
                  </v-flex>
                  <v-flex md12 lg12>
                    <app-search-card-main ma-5 pa-5 />
                  </v-flex>
                  <v-flex md12 lg12>
                    <v-text-field
                      v-model="nbre_pers"
                      label="Nbre des personnes"
                      prepend-icon="folder"
                      ma-5
                      pa-5 />
                  </v-flex>
                  <!--  @click="submit"  -->
                </v-container>
              </v-form>
            </v-card-text>
          </v-card>

          <v-btn
            depressed
            large
            color="#1867c0"
            class="white--text"
            @click="e1 = 2">
            Continue
          </v-btn>

          <v-btn
            v-btn
            outline
            large
            color="blue darken-3"
            class="indigo--text">
            Cancel
          </v-btn>
        </v-stepper-content>
        <v-stepper-content step="2">
          <v-card
            class="mb-5"
            height="350px" />

          <v-btn
            depressed
            large
            color="#1867c0"
            class="white--text"
            @click="e1 = 1">
            Valider
          </v-btn>

          <v-btn
            v-btn
            outline
            large
            color="blue darken-3"
            class="indigo--text">
            Cancel
          </v-btn>
        </v-stepper-content>
      </v-stepper-items>
    </v-stepper>
  </v-container>
</template>


<script>
    import AppSearchCardMain from './AppSearchCardMain.vue'
    import 'vue-material/dist/vue-material.min.css'
    import 'vue-material/dist/theme/default.css'
    import vSelect from 'vue-select'
    import UploadButton from 'vuetify-upload-button';
    import TuiEditor from 'vue-tui-editor';

    Vue.use(TuiEditor)

    Vue.component('v-select', vSelect)
    Vue.component('upload-btn',UploadButton)
    export default {
        components:{AppSearchCardMain},
        data () {
            return {
                apprenant:'',
                options: [
                    {id: 1, label: 'foo'},
                    {id: 3, label: 'bar'},
                    {id: 2, label: 'baz'},
                ],
                apprenant: [
                    'Hassen',
                    'Ahmed',
                    'Wael',
                    'Sarra',
                    'Safa'
                ],
                nbre_pers:'',
                question:'',
                libelleChapitre:'',
                TitreLecon:'',
                contenu_lecon:'',
                switch1: true,
                selected: {id: 3, label: 'Dev'},
                selectedDateDeb: new Date('2018/03/26'),
                selectedDateFin:new Date('2018/03/26'),
                e1: 0,
                libelle: '',
                nbre_pers: '',
                content: '',
                due: null,
                menu: false,
                inputRules: [
                    v => !!v || 'This field is required',
                v => v.length >= 3 || 'Minimum length is 3 characters'
        ],
        }
        },

        computed: {

        },
        methods: {
            submit() {
                if(this.$refs.form.validate()) {
                    console.log(this.title, this.content)
                }
            },
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
<style>
    .para_border{
        border: 4px dotted lightslategrey;
    }
</style>
