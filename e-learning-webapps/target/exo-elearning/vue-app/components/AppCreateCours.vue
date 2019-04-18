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
          <h5 class="blue--text .headline">Liste des Cours</h5>
        </v-stepper-step>

        <v-divider />
        <v-stepper-step
          :complete="e1 > 2"
          step="2"
          color="blue">
          <h5 class="blue--text .headline">Ajouter des Cours</h5>
        </v-stepper-step>

        <v-divider />

        <v-stepper-step step="3" color="blue"><h5 class="blue--text .headline">Ajouter des Leçons</h5></v-stepper-step>
      </v-stepper-header>

      <v-stepper-items>
        <v-stepper-content step="1">
          <v-card
            class="mb-5"
            height="750px">
            <AppEditCours />
          </v-card>

          <v-btn
            depressed
            large
            dark
            color="#1867c0"
            class="white--text"
            @click="e1 = 2">
            Suivant
            <v-icon dark>navigate_next</v-icon>
          </v-btn>

          <!-- <v-btn
                    v-btn
                    outline
                    large
                    color="blue darken-3"
                    class="indigo--text">
                Précédent
            </v-btn> -->
        </v-stepper-content>

        <v-stepper-content step="2">
          <v-card
            class="mb-5"
            height="850px">
            <v-card-text>
              <v-form ref="form" class="px-3">
                <v-container>
                  <v-flex md12>
                    <v-text-field
                      v-model="libelle"
                      label="Libellé"
                      prepend-icon="folder"
                      :rules="inputRules" />
                  </v-flex>
                  <v-layout>
                    <v-flex md6>
                      <p class=" text-md-left subheading  font-weight-light blue-grey--text text--darken-1">Catégorie de course</p>
                    </v-flex>
                  </v-layout>
                  <v-layout>
                    <v-flex md10>
                      <i class="uiIconMiniArrowDown uiIconLightGray"></i>
                      <select
                        class="select_style"
                        :options="option"
                        placeholder="Categories">
                        <option v-for="option in options" :value="option.value">
                          {{ option.nameCategory }}
                        </option>
                      </select>
                      <!--

                                                        <v-select
                                                                :options="option"
                                                                >
                                                            <option v-for="option in options" v-bind:value="option.value">
                                                                {{ option.nameCategory }}
                                                            </option>
                                                        </v-select>
                                                        -->
                      <!--
                      <div class="btn-toolbar">
                        <div class="btn-group">
                          <button
                            class="btn dropdown-toggle"
                            data-toggle="dropdown"
                            :options="option">
                            {{ selectedCategory }}
                            <span><i class="uiIconMiniArrowDown uiIconLightGray"></i></span>
                          </button>
                          <ul class="dropdown-menu">
                            <li v-for="option in options">{{ option.nameCategory }}</li>
                          </ul>
                        </div>
                      </div>
                      -->
                    </v-flex>
                    <v-flex md1>
                      <app-category-cours />
                    </v-flex>
                  </v-layout>
                  <v-layout>
                    <v-flex md12>
                      <v-switch
                        v-model="switch1"
                        color="blue"
                        :label="`Visibilité`" />
                    </v-flex>
                  </v-layout>

                  <v-flex md12>
                    <v-text-field
                      v-model="nbre_pers"
                      label="Nbre des personnes"
                      prepend-icon="folder" />
                  </v-flex>
                  <!--
                                          <v-flex md12>
                                              <!--${switch1.toString()}--
                                              <v-textarea v-model="content" label="Information" prepend-icon="edit" :rules="inputRules"></v-textarea>
                                          </v-flex>
                                          -->

                  <v-layout>
                    <v-flex md6>
                      <p class=" text-md-left subheading  font-weight-light blue-grey--text text--darken-1">Date de début</p>
                      <md-datepicker v-model="selectedDateDeb" :md-disabled-dates="disabledDatesDeb" />
                    </v-flex>
                    <v-flex md6>
                      <p class=" text-md-left subheading  font-weight-light blue-grey--text text--darken-1">Date de Fin</p>
                      <md-datepicker v-model="selectedDateFin" :md-disabled-dates="disabledDates" />
                    </v-flex>
                  </v-layout>
                  <v-layout>
                    <v-flex md4>
                      <p class=" text-sm-left subheading  font-weight-light blue-grey--text text--darken-1">Récompense</p>
                    </v-flex>
                    <v-layout>
                      <v-flex md4>
                        <v-checkbox
                          v-model="selected"
                          label="Badge"
                          value="Badge" />
                      </v-flex>
                      <v-flex>
                        <v-checkbox
                          v-model="selected"
                          label="Certification"
                          value="Certification" />
                      </v-flex>
                    </v-layout>
                  </v-layout>

                  <div class="para_border">
                    <v-layout>
                      <v-flex md6>
                        <p class=" text-md-left subheading  font-weight-light blue-grey--text text--darken-1 pa-2">Upload image de course</p>
                      </v-flex>
                      <v-flex md6>
                        <!--
                        <upload-btn icon>
                          <template slot="icon">
                            <v-icon size="60px">attachment</v-icon>
                          </template>
                        </upload-btn>
                        -->
                      </v-flex>
                    </v-layout>
                  </div>

                  <v-spacer />
                  <!--  @click="submit"  -->
                </v-container>
              </v-form>
            </v-card-text>
          </v-card>

          <v-btn
            outline
            large
            color="blue darken-3"
            class="indigo--text"
            @click="e1 = 1">
            <v-icon dark>navigate_before</v-icon>
            Précédent
          </v-btn>

          <v-btn
            depressed
            large
            dark
            color="#1867c0"
            class="white--text"
            @click="e1 = 3">
            Suivant
            <v-icon dark>navigate_next</v-icon>
          </v-btn>
        </v-stepper-content>

        <v-stepper-content step="3">
          <v-card
            class="mb-5"
            height="900px">
            <v-form ref="form" class="px-3">
              <v-container>
                <v-flex md12>
                  <p class=" text-md-left headline font-weight-bold blue-grey--text text--darken-1 ">Général</p>
                </v-flex>
                <v-flex md12>
                  <v-textarea
                    v-model="content"
                    label="Ce qui vous apprendre"
                    prepend-icon="edit"
                    :rules="inputRules" />
                </v-flex>
                <v-flex md12>
                  <p class=" text-md-left headline font-weight-bold blue-grey--text text--darken-1 pa-2">Contenu du course</p>
                </v-flex>
                <v-flex md12>
                  <v-text-field
                    v-model="TitreLecon"
                    label="Titre de Leçon"
                    prepend-icon="folder"
                    :rules="inputRules" />
                </v-flex>

                <v-flex md12>
                  <v-text-field
                    v-model="contenu_lecon"
                    label="Contenu de Leçon"
                    prepend-icon="folder"
                    :rules="inputRules" />
                </v-flex>
                <!--
                        <v-flex md12>
                            <tui-editor :options="options" />
                        </v-flex>
                        -->

                <v-flex md12>
                  <p class=" text-md-left headline font-weight-bold blue-grey--text text--darken-1 pa-2">Exercices</p>
                </v-flex>
                <v-flex md12>
                  <v-text-field
                    v-model="question"
                    label="Question"
                    prepend-icon="folder"
                    :rules="inputRules" />
                </v-flex>
              </v-container>
            </v-form>
          </v-card>

          <v-btn
            outline
            large
            color="blue darken-3"
            class="indigo--text"
            @click="e1 = 2">
            <v-icon dark>navigate_before</v-icon>
            Précédent
          </v-btn>

          <v-btn
            depressed
            large
            color="#1867c0"
            class="white--text"
            @click="quitter">
            Valider
          </v-btn>
        </v-stepper-content>
      </v-stepper-items>
    </v-stepper>
  </v-container>
</template>


<script>
    import axios from 'axios'
  //  import vSelect from 'vue-select'
import AppProgressAdd from './AppProgressAdd.vue'
import AppEditCours from './AppEditCours.vue'
import AppCategoryCours from './AppCategoryCours.vue'



export default {
    components:{AppProgressAdd,AppEditCours,AppCategoryCours},
    data () {
        return {
            options: [],
            selectedOption:{},
            selectedCategory:'',
            question:'',
            libelleChapitre:'',
            TitreLecon:'',
            contenu_lecon:'',
            switch1: true,
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
            selectedDateDeb: null,
            selectedDateFin:null,
            disabledDatesDeb: date => {
            const day = date.getDay()

            return day === 6 || day === 0
        },
        disabledDatesFin: date => {
            const day = date.getDay()

            return day === 6 || day === 0
        }
        }
    },
    computed: {

        },
    updated(){
        axios.get(`http://127.0.0.1:8080/portal/rest/category/allCatgories`)
            .then(response => {
            // JSON responses are automatically parsed.
            this.options= response.data
           this.selectedCategory=response.data.value
        //console.log(response.data)
    })
    .catch(e => {
            this.errors.push(e)
    })

    },
    methods: {
        submit() {
            if(this.$refs.form.validate()) {
                console.log(this.title, this.content)
            }
        },
        quitter:function() {
            this.$router.push('/')
            }
    },
    selectionChanged: function() {
        console.log('selectionChanged:this.selectedCountry:' , this.selectedCountry);
    }

}

</script>
<style>
.para_border{
    border: 4px dotted lightslategrey;
}
.select_style {
    border: 1px solid;
    border-color: #929b9c;
    border-radius: 5px;
    outline: none;
    width: 100%;
    height: 35px;

}
i.uiIconMiniArrowDown.uiIconLightGray {
    color: #ffffff;
    font-size: 23px;
    top: 29px;
    float: right;
    margin-right: 10px;
}
</style>
