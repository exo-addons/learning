<template>
    <v-card
            class="mb-5"
            height="650px" >
        <v-card-text>
            <center>
                <div class="alert alert-error"  v-if="alt">
                    <i class="uiIconError" @click="changeError"></i>Aucun Examen Crée
                </div>
            </center>
            <v-form ref="form">
                <v-container>
                    <v-flex md10>
                        <p class=" text-sm-left subheading  font-weight-light blue-grey--text text--darken-1">Cours Affecté {{idExamen}}</p>
                        <select
                                v-model="selectedCourse"
                                class="select_style">
                            <option value="" >Select Course</option>
                            <option v-for="option in courses" :value="option.idCourse">
                                {{ option.nameCourse }}
                            </option>
                        </select>
                    </v-flex>
                    <v-flex md10>
                        <p class=" text-sm-left subheading  font-weight-light blue-grey--text text--darken-1">Examen Affecté {{idExamen}}</p>
                        <select
                                v-model="selectedExam"
                                class="select_style">
                            <option value="" >Select Examen</option>
                            <option v-for="option in exams" :value="option.idExam">
                                {{ option.nameExam }}
                            </option>
                        </select>
                    </v-flex>
                   <v-flex md10>
                        <v-text-field
                                type="number"
                                v-model="scaleExam"
                                label="barème"
                                prepend-icon="folder"
                                >

                        </v-text-field>
                    </v-flex>
                    <v-flex md10>
                        <v-text-field
                                v-model="questionExam"
                                label="Question Examen"
                                prepend-icon="folder"
                                :rules="inputRules" />
                    </v-flex>
                    <v-flex md8>
                        <v-text-field
                                v-model="optionExam1"
                                label="Réponse 1"
                                prepend-icon="edit"
                               />
                    </v-flex>
                    <v-flex md8>
                        <v-text-field
                                v-model="optionExam2"
                                label="Réponse 2"
                                prepend-icon="edit"
                                />
                    </v-flex>
                    <v-flex md8>
                        <v-text-field
                                v-model="optionExam3"
                                label="Réponse 3"
                                prepend-icon="edit"
                                />
                    </v-flex>
                    <v-flex md10>
                        <v-text-field
                                v-model="answer"
                                label="Réponse Correcte"
                                prepend-icon="edit"
                                />
                    </v-flex>
                </v-container>
                <button class="btn btn-primary" type="button"  @click.prevent="saveQcm">Ajouter</button>
                <button class="btn " type="button">Annuler</button>
                <div class="btn-popup">
                <app-search-qcm></app-search-qcm>
                </div>
            </v-form>
        </v-card-text>
    </v-card>

</template>

<script>
    import { bus } from '../../main';
    import axios from 'axios';
    import AppSearchQcm from './AppSearchQcm.vue'
    export default {
        props:['contentExam'],
        components:{AppSearchQcm},
        data:function(){
            return{
                alt:false,
                selectedExam:'',
                selectedCourse:'',
                questionExam:'',
                optionExam1:'',
                optionExam2:'',
                optionExam3:'',
                scaleExam:null,
                answer:'',
                idref:null,
                courses:{},
                exams:[],
                qcm:{
                    idCourse: null,
                    idExam: null,
                    questionExercise: '',
                    answerExercise: '',
                    scaleExercise: null,
                    choose1: '',
                    choose2: '',
                    choose3: '',
                },
                inputRules: [
                    v => !!v || 'les champs sont obligatoires',
                    v => v.length >= 3 || 'entrer plus que 3 caractéres'
                ],
            }
        },
        created(){
            bus.$on('ExamChanged', (data) => {
                this.contentExam=data;
                this.exams.push(this.contentExam)
                console.log("bus value",this.exams)
            });
        },
        mounted(){
            axios.get(`/portal/rest/cours/allCompletedByUser/COMPLETED`)
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.courses= response.data;
                    if(this.courses.length===0){
                        this.alt=true;
                    }
                })
                .catch(e => {
                    this.errors.push(e)
                })
            axios.get(`/portal/rest/exam/allExamByUser`)
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.exams= response.data
                    console.log(this.selectedCourse)
                })
        },
        methods: {
            saveQcm: function() {
                this.qcm.idCourse = this.selectedCourse;
                this.qcm.idExam = this.selectedExam;
                this.qcm.questionExercise = this.questionExam;
                this.qcm.scaleExercise = this.scaleExam;
                this.qcm.answerExercise = this.answer;
                this.qcm.choose1 = this.optionExam1;
                this.qcm.choose2 = this.optionExam2;
                this.qcm.choose3 = this.optionExam3;
                if ((this.questionExam === '') || (this.scaleExam === null) || (this.answer === '')) {
                    this.alt = true;
                    console.log(this.alt);
                }
                console.log(this.qcm);
                if (this.alt === false) {
                    axios.post(`/portal/rest/exercise/add`, this.qcm, {
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    }).then((response) => {
                        this.questionExam='';
                        this.scaleExam=null;
                        this.answer='';

                        });
                }
            },
            changeError(){
                this.alt=false
            },
        }
    }
</script>

<style >
.btn-popup{
    margin-left: 26.3%;
    margin-top: -3.5%;
}
</style>
