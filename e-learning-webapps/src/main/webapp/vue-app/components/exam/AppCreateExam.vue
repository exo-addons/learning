<template>
    <v-card
            class="mb-5"
            height="550px">
        <v-card-text>
                <center>
                    <div class="alert alert-error"  v-if="alt">
                  <i class="uiIconError" @click="changeError"></i>Aucun Examen Crée
                </div>
                </center>
            <v-form ref="form" class="px-3">
                <v-container>

                    <v-flex md10>
                        <!--    <i class="uiIconMiniArrowDown uiIconLightGray"></i>-->
                        <select
                                v-model="selectedCourse"
                                class="select_style">
                            <option value="" selected>Select Course</option>
                            <option v-for="option in courses"  :value="option.idCourse">
                                {{ option.nameCourse }}
                            </option>
                        </select>
                    </v-flex>
                <v-flex md10>
                    <v-text-field
                            v-model="nameExam"
                            label="Libellé Examen"
                            prepend-icon="folder"
                            :rules="inputRules" />
                </v-flex>
                    <v-flex md10>
                <v-layout>
                        <v-flex md4>
                            <p class=" text-sm-left subheading  font-weight-light blue-grey--text text--darken-1">Récompense</p>
                        </v-flex>
                        <v-layout>
                            <v-flex md4>
                                <v-checkbox
                                        v-model="rewardExam"
                                        label="Badge"
                                        value="badge" />
                            </v-flex>
                            <v-flex>
                                <v-checkbox
                                        v-model="rewardExam"
                                        label="Certification"
                                        value="certification" />
                            </v-flex>
                        </v-layout>
                    </v-layout>
                    </v-flex>
                    <v-flex md10>
                    <v-text-field
                            type="number"
                            v-model="nbrBidExam"
                            label="Tentative"
                            prepend-icon="folder"
                             />

                </v-flex>
                    <v-flex md10>
                    <v-layout>
                        <v-flex md6>
                            <p class=" text-md-left subheading  font-weight-light blue-grey--text text--darken-1">Date de début </p>
                            <Datepicker
                                    id="dateStart"
                                    v-model="dateStartExam"
                                    placeholder="Select Date Start"
                                    monday-first="true"
                                    :format="formatDate"
                                    :language="fr" />
                        </v-flex>
                        <v-flex md6>
                            <p class=" text-md-left subheading  font-weight-light blue-grey--text text--darken-1">Date de Fin</p>
                            <Datepicker
                                    id="dateEnd"
                                    v-model="dateEndExam"
                                    placeholder="Select Date End"
                                    monday-first="true"
                                    :format="formatDate"
                                    :language="fr" />
                        </v-flex>
                        <v-spacer />
                    </v-layout>
                    </v-flex>
                    <v-flex md10>
                        <v-text-field
                                type="number"
                                v-model="dureeExam"
                                label="Durée Examen"
                                prepend-icon="folder"
                                 />
                    </v-flex>
                    <v-flex md10>
                        <v-text-field
                                type="number"
                                v-model="scaleExam"
                                label="Barème Examen"
                                prepend-icon="folder"
                                 />
                    </v-flex>
                </v-container>
                <v-flex>
                    <button class="btn btn-primary" type="button"  @click.prevent="addExam">Ajouter</button>
                    <button class="btn " type="button">Annuler</button>
                </v-flex>

            </v-form>
                </v-card-text>
            </v-card>
</template>

<script>
    import { bus } from '../../main';
    import axios from 'axios';
    import Datepicker from 'vuejs-datepicker';
    import moment from 'moment';
    Vue.filter('formatDate', function(value) {
        if (value) {
            const lang = eXo.env.portal.language;
            moment.locale(lang);
            return moment(String(value), value.includes('/') ? 'DD/MM/YYYY' : 'YYYY-MM-DD').format(lang == 'fr' ? 'DD MMMM YYYY' : 'MMMM DD YYYY');
        }
    });

    export default {
        props:['contentExam'],
        components:{
            Datepicker
        },
        data:function(){
            return{
                courses:[],
                selectedCourse:null,
                alt:false,
                childData:'',
                nameExam:'',
                rewardExam:'',
                nbrBidExam:null,
                dateStartExam:'',
                dateEndExam:'',
                dureeExam:null,
                scaleExam:null,
                exam:{
                    nameExam: '',
                    dateStartExam: '',
                    dateEndExam:'',
                    nbBidExam:'',
                    rewardExam:'',
                    dureeExam:'',
                    idCourse:null
                },
                inputRules: [
                    v => !!v || 'les champs sont obligatoires',
                    v => v.length >= 3 || 'entrer plus que 3 caractéres'
                ],
                config: {
                    format: 'YYYY-MM-DD',
                    useCurrent: false,
                }
            }
        },
        mounted(){
            axios.get(`/portal/rest/cours/allPublishedByUser/PUBLISHED`)
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.courses= response.data
                })
                .catch(e => {
                    this.errors.push(e)
                })
        },


        methods:{
            addExam:function() {
                this.exam.nameExam=this.nameExam;
                this.exam.rewardExam=this.rewardExam;
                this.exam.nbBidExam=this.nbrBidExam;
                this.exam.dateStartExam=this.dateStartExam;
                this.exam.dateEndExam=this.dateEndExam;
                this.exam.dureeExam=this.dureeExam;
                this.exam.idCourse=this.selectedCourse;
                if((this.nameExam==='')||(this.nbrBidExam===null)||(this.dureeExam===null)||(this.scaleExam===null))
                {
                    this.alt=true;
                }
                if(this.alt===false){
                    axios.post(`/portal/rest/exam/add`, this.exam, {
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    }).then(response => {

                        this.exam = response.data;
                        bus.$emit('ExamChanged', this.exam);
                        this.nameExam = '';
                        this.rewardExam='';
                        this.scaleExam = null;
                        this.nbrBidExam = null;
                        this.dureeExam = null;
                    })
                        .catch(e => {
                        })
                }

            },
            changeError(){
                this.alt=false
            }
        }
    }
</script>

<style scoped>
    .alert.alert-error {
        width: 700px;
    }

</style>
