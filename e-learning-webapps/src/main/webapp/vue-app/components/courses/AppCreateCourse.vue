<template>
    <v-card
      class="mb-5"
      height="700px">
      <v-card-text>
        <v-form ref="form" class="px-3">
          <v-container>
            <v-flex md10>
              <v-text-field
                v-model="nameCourse"
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
                  v-model="selectedCategory"
                  class="select_style">
                  <option value="" >Select Categorie</option>
                  <option v-for="option in categories"  :value="option.idCategory">
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
                  v-model="visibilityCourse"
                  color="blue"
                  :label="`Visibilité`" />
              </v-flex>
            </v-layout>
            <v-layout>
              <v-radio-group v-model="courseStatus" row>
                <v-radio
                  label="Drafted"
                  color="blue"
                  value="DRAFTED" />
                <v-radio
                  label="Completed"
                  color="blue"
                  value="COMPLETED" />
                <v-radio
                  label="Published"
                  color="blue"
                  value="PUBLISHED" />
              </v-radio-group>
            </v-layout>
            <v-flex md10>
              <v-text-field
                v-model="nbPerson"
                label="Nbre des personnes"
                prepend-icon="folder" />
            </v-flex>
            <v-layout>
              <v-flex md6>
                <p class=" text-md-left subheading  font-weight-light blue-grey--text text--darken-1">Date de début</p>
                <Datepicker
                  id="dateStart"
                  v-model="dateStart"
                  placeholder="Select Date Start" 
                  monday-first="true"
                  :format="formatDate"
                  :language="fr" />
              </v-flex>
              <v-flex md6>
                <p class=" text-md-left subheading  font-weight-light blue-grey--text text--darken-1">Date de Fin</p>
                <Datepicker
                  id="dateEnd"
                  v-model="dateEnd"
                  placeholder="Select Date End" 
                  monday-first="true"
                  :format="formatDate"
                  :language="fr" />
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
                    v-model="rewardCourse"
                    label="course"
                    value="course" />
                </v-flex>
                <v-flex>
                  <v-checkbox
                    v-model="rewardCourse"
                    label="Certification"
                    value="certification" />
                </v-flex>
              </v-layout>
            </v-layout>

            <div class="para_border">
              <v-layout>
                <v-flex>
                  <p class=" text-md-left subheading  font-weight-light blue-grey--text text--darken-1 pa-2">Upload image</p>
                </v-flex>
                <v-flex>
                  <input type="file"  v-on:change="onImageChange">
                </v-flex>
              </v-layout>

            </div>

            <!--  @click="submit"  -->

            <v-layout>

            </v-layout>
            <v-flex>
              <v-btn
                type="button"
                depressed
                large
                dark
                color="#1867c0"
                class="white--text"
                @click.prevent="onSubmit">
                ADD
              </v-btn>
            </v-flex>
            <v-spacer />
          </v-container>
        </v-form>
      </v-card-text>
    </v-card>
</template>
<script>
    import axios from 'axios'
    import Datepicker from 'vuejs-datepicker';
    import AppCategoryCours from './AppCategoryCours.vue'
    import {en, fr} from 'vuejs-datepicker/dist/locale'
    import moment from 'moment';
   // import UploadButton from 'vuetify-upload-button';
   /// import Notification from './notifications.vue';

    Vue.filter('formatDate', function(value) {
        if (value) {
            const lang = eXo.env.portal.language;
            moment.locale(lang);
            return moment(String(value), value.includes('/') ? 'DD/MM/YYYY' : 'YYYY-MM-DD').format(lang == 'fr' ? 'DD MMMM YYYY' : 'MMMM DD YYYY');
        }
    });

    export default {
        components:{
            AppCategoryCours,
            Datepicker
    },
        props: {
            course: {
                type: Object,
                default: () => null
            }
        },
    data:function(){
        return{
            selectedCategory:'',
            categories:[],
            image:'',
            en: en,
            fr: fr,
                date: new Date(),
                config: {
                    format: 'YYYY-MM-DD',
                    useCurrent: false,
                },
            inputRules: [
                v => !!v || 'This field is required',
                v => v.length >= 3 || 'Minimum length is 3 characters'
            ],
            e1: 0,
            nameCourse:'',
            visibilityCourse:true,
            courseStatus:'DRAFTED',
            rewardCourse:'',
            dateStart:'',
            dateEnd:'',
            nbPerson:'',
            courses: {
                nameCourse: '',
                dateStart: '',
                dateEnd: '',
                nbPerson: '',
                rewardCourse: '',
                visibilityCourse:'',
                idCategory:'',
                icon: '',
                status:''

            },
            addSuccess: false,
            addError: false,
            updateMessage: '',
            categories:[]
        }
    },
        mounted(){
            axios.get(`http://127.0.0.1:8080/portal/rest/category/all`)
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.categories= response.data
                    //console.log(response.data)
                })
                .catch(e => {
                    this.errors.push(e)
                })
        },
        created() {
        },
        methods: {
            quitter: function () {
                this.$router.push('/')
            },
            onImageChange(e) {
                console.log(e.target.files[0]);
                this.course.icon = e.target.files[0];
            },
            onSubmit(course) {
                const formData = new FormData();

                formData.append('image', this.courses.icon)
                const MAX_RANDOM_NUMBER = 100000;
                const uploadId = Math.round(Math.random() * MAX_RANDOM_NUMBER);

                axios.post(`http://127.0.0.1:8080/portal/upload?uploadId=${uploadId}&action=upload`, formData,
                    {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    }).then(response => {
                    this.courses.nameCourse=this.nameCourse;
                    this.courses.dateStart=this.dateStart;
                    this.courses.dateEnd=this.dateEnd;
                    this.courses.nbPerson=this.nbPerson;
                    this.courses.rewardCourse=this.rewardCourse;
                    this.courses.visibilityCourse=this.visibility;
                    this.courses.idCategory=this.selectedCategory;
                    this.courses.icon=this.formData
                    axios.post(`http://127.0.0.1:8080/portal/rest/cours/add`, this.course)
                        .then(response => {
                            console.log("uploadId this", this.course)
                            this.$emit('testevent', this.course)

                            this.addSuccess = true
                            this.updateMessage = 'added'
                        })
                        .catch(e => {
                            this.addError = true
                        })

                })
                    .catch(e => {
                        console.log("Error")
                    })
            },
        }

}
</script>

