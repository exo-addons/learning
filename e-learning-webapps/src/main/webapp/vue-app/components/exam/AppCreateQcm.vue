<template>
    <v-card
            class="mb-5"
            height="600px">
        <v-card-text>
            <v-form ref="form" class="px-3">
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
                        <v-text-field
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
                                :rules="inputRules" />
                    </v-flex>
                    <v-flex md8>
                        <v-text-field
                                v-model="optionExam2"
                                label="Réponse 2"
                                prepend-icon="edit"
                                :rules="inputRules" />
                    </v-flex>
                    <v-flex md8>
                        <v-text-field
                                v-model="optionExam3"
                                label="Réponse 3"
                                prepend-icon="edit"
                                :rules="inputRules" />
                    </v-flex>
                    <v-flex md10>
                        <v-text-field
                                v-model="answer"
                                label="Réponse Correcte"
                                prepend-icon="edit"
                                :rules="inputRules" />
                    </v-flex>
                </v-container>
                <button class="btn btn-primary" type="button"  @click.prevent="saveQcm">Ajouter</button>
                <button class="btn " type="button">Annuler</button>
            </v-form>
        </v-card-text>
    </v-card>

</template>

<script>
    import axios from 'axios';
    import {bus} from '../../main.js';
    import Notification from '../commun/notifications.vue';

    export default {
        components:{
            Notification
        },
        props:{
            idExamen: {
                type: Number
            }
        },
        data:function(){
            return{
                selectedCourse:'',
                questionExam:'',
                optionExam1:'',
                optionExam2:'',
                optionExam3:'',
                scaleExam:'',
                answer:'',
                idref:null,
                courses:{},
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
                notifications:[]
            }
        },
        created(){
            bus.$on('sendIdExam',(data)=>{
                this.idExamen=data;
                this.idref=this.idExamen;
                console.log('contenu',this.idExamen)
            })
        },
        mounted(){
            axios.get(`/portal/rest/cours/all`)
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.courses= response.data
                    console.log(this.selectedCourse)
                })
                .catch(e => {
                    this.errors.push(e)
                })
        },
        methods: {
            saveQcm: function()
            {
                this.qcm.idCourse=this.selectedCourse;
                this.qcm.idExam=this.idref;
                this.qcm.questionExercise=this.questionExam;
                this.qcm.scaleExercise=this.scaleExam;
                this.qcm.answerExercise=this.answer;
                this.qcm.choose1=this.optionExam1;
                this.qcm.choose2=this.optionExam2;
                this.qcm.choose3=this.optionExam3;
                console.log(this.qcm);
                axios.post(`/portal/rest/exercise/add`, this.qcm, {
                    headers : {
                        'Content-Type' : 'application/json'
                    }
                }).then((response) => {
                        this.notifications.push({
                            type: 'success',
                            message: 'Lesson created successfully'
                        });
                      //  this.lessonContent='';

                    },
                    (response) => {
                        this.notifications.push({
                            type: 'error',
                            message: 'Lesson not created'
                        });
                    });
            }

        }
    }
</script>

<style scoped>

</style>
