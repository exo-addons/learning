<template>
  <v-container class="my-5" elevation-20>
    <v-layout>
      <v-flex md12>
        <app-edit-cours-tab />
      </v-flex>
    </v-layout>
    <v-layout>
      <v-flex md12>
        <v-stepper
          v-model="e1"
          flat
          class="text-xs-center ma-3"
          height="650px">
          <v-stepper-header>
            <v-stepper-step
              :complete="e1 > 1"
              step="1"
              color="blue">
              <h5 class="blue--text .headline">Ajouter des Cours</h5>
            </v-stepper-step>

            <v-divider />

            <v-stepper-step step="2" color="blue"><h5 class="blue--text .headline">Ajouter des Leçons</h5></v-stepper-step>
          </v-stepper-header>

          <v-stepper-content step="1">
            <notification v-bind:notifications="notifications"></notification>
            <v-card
              class="mb-5"
              height="700px">
              <v-card-text>
                <v-form ref="form" class="px-3">
                  <v-container>
                    <v-flex md10>
                      <v-text-field
                        v-model="course.nameCourse"
                        label="Libellé"
                        prepend-icon="folder"
                        :rules="inputRules" />
                    </v-flex>
                    <v-layout>
                      <v-flex md6>
                        <p class=" text-md-left subheading  font-weight-light blue-grey--text text--darken-1">Catégorie de cours</p>
                      </v-flex>
                    </v-layout>
                    <v-layout>
                      <v-flex md10>
                        <!--    <i class="uiIconMiniArrowDown uiIconLightGray"></i>-->
                        <select
                          class="select_style"
                          v-model="course.idCategory"
                          :options="option"
                          placeholder="Categories">
                          <option v-for="option in options">
                            {{ option.nameCategory }}
                          </option>
                        </select>
                      </v-flex>
                      <v-flex>
                        <app-category-cours />
                      </v-flex>
                    </v-layout>
                    <v-layout>
                      <v-flex md10>
                        <v-switch
                          v-model="course.visibilityCourse"
                          color="blue"
                          :label="`Visibilité`" />
                      </v-flex>
                    </v-layout>
                    <v-layout>
                        <v-radio-group v-model="course.status" row>
                          <v-radio label="Drafted" color="blue" value="DRAFTED"></v-radio>
                          <v-radio label="Completed" color="blue" value="COMPLETED"></v-radio>
                          <v-radio label="Published" color="blue" value="PUBLISHED"></v-radio>
                        </v-radio-group>
                    </v-layout>
                    <v-flex md10>
                      <v-text-field
                        v-model="course.nbPerson"
                        label="Nbre des personnes"
                        prepend-icon="folder" />
                    </v-flex>
                    <v-layout>
                      <v-flex md6>
                        <p class=" text-md-left subheading  font-weight-light blue-grey--text text--darken-1">Date de début</p>
                        <Datepicker
                          v-model="course.dateStart"
                          id='dateStart'
                          monday-first="true"
                          :format="formatDate"
                          :language="fr"></Datepicker>
                      </v-flex>
                      <v-flex md6>
                        <p class=" text-md-left subheading  font-weight-light blue-grey--text text--darken-1">Date de Fin</p>
                        <Datepicker
                          v-model="course.dateEnd"
                          id='dateEnd'
                          monday-first="true"
                          :format="formatDate"
                          :language="fr"> </Datepicker>
                      </v-flex>
                      <v-spacer />
                    </v-layout>
                    <v-layout>
                      <v-flex md4>
                        <p class=" text-sm-left subheading  font-weight-light blue-grey--text text--darken-1">Récompense</p>
                      </v-flex>
                      <v-layout>
                        <v-flex md4>
                          <v-checkbox
                            v-model="course.rewardCourse"
                            label="Badge"
                            value="badge" />
                        </v-flex>
                        <v-flex>
                          <v-checkbox
                            v-model="course.rewardCourse"
                            label="Certification"
                            value="certification" />
                        </v-flex>
                      </v-layout>
                    </v-layout>

                    <div class="para_border">
                      <v-layout>
                        <v-flex>
                          <p class=" text-md-left subheading  font-weight-light blue-grey--text text--darken-1 pa-2">Upload image de course</p>
                        </v-flex>
                        <v-flex>
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
              depressed
              large
              dark
              color="#1867c0"
              class="white--text"
              @click="e1 = 2">
              Suivant
              <v-icon dark>navigate_next</v-icon>
            </v-btn>
                       <v-btn
              depressed
              large
              dark
              color="#1867c0"
              class="white--text"
              @click="saveCourse">
              Save
            </v-btn>
          </v-stepper-content>

          <v-stepper-content step="2">
            <v-card
              class="mb-5"
              height="650px">
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
                    <v-text-fieldhttp://127.0.0.1:8080/portal/rest/cours/add
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
              @click="e1 = 1">
              <v-icon dark>navigate_before</v-icon>
              Précédent
            </v-btn>

            <v-btn
              depressed
              large
              color="#1867c0"
              class="white--text"
              @click="quitter">
              Terminer
            </v-btn>
          </v-stepper-content>
          </v-stepper-items>
        </v-stepper>
      </v-flex>
    </v-layout>
  </v-container>
</template>


<script>
    import axios from 'axios'
    import AppEditCours from './AppEditCours.vue'
    import AppCategoryCours from './AppCategoryCours.vue'
    import AppEditCoursTab from './AppEditCoursTabMain.vue'
    import Datepicker from 'vuejs-datepicker';
    import {en, fr} from 'vuejs-datepicker/dist/locale'
    	import moment from 'moment';
    import Notification from './notifications.vue';
  	Vue.filter('formatDate', function(value) {
		if (value) {
			const lang = eXo.env.portal.language;
			moment.locale(lang);
			return moment(String(value), value.includes('/') ? 'DD/MM/YYYY' : 'YYYY-MM-DD').format(lang == 'fr' ? 'DD MMMM YYYY' : 'MMMM DD YYYY');
		}
	});
    export default {
    components:{AppEditCours,AppCategoryCours,AppEditCoursTab,Datepicker,'notification' : Notification},

    data () {
        return {
            notifications:[],
            status:null,
            en: en,
            fr: fr,
            menu: false,
            options: [],
            selectedOption:{},
            selectedCategory:'',
            question:'',
            libelleChapitre:'',
            TitreLecon:'',
            contenu_lecon:'',
            switch1: true,
            e1: 0,
             config: {
                    format: 'YYYY-MM-DD',
                    useCurrent: false,
                },
            course:{},
            name_course:'', 
            nbre_pers: '',
            content: '',
            category:'',
            dateStart:'',
            dateEnd:'',
            inputRules: [
                v => !!v || 'This field is required',
            v => v.length >= 3 || 'Minimum length is 3 characters'
        ]
        }
    },
    updated(){
        axios.get(`http://127.0.0.1:8080/portal/rest/category/all`)
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
        saveCourse() {
                      axios.post('http://127.0.0.1:8080/portal/rest/cours/add', this.course, {
                    headers : {
                        'Content-Type' : 'application/json'
                    }
                }).then((response) => {
                  this.notifications.push({
                        type: 'success',
                        message: 'Course created successfully'
                    });

                }, (response) => {
                    this.notifications.push({
                        type: 'error',
                        message: 'Course not created'
                    });

                });
            
        },
        quitter:function() {
            this.$router.push('/')
            }
    },
    selectionChanged: function() {
        console.log('selectionChanged:this.selectedCountry:' , this.selectedCountry);
    },
    /* formatDate(date) {
      return moment(date).format('YYYY-MM-DD');
    }*/
}
</script>
<style>
.para_border{
  width:600px;
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
input#dateStart {
    background: transparent;
    cursor: pointer;
}
input#dateEnd {
    background: transparent;
    cursor: pointer;
}
</style>