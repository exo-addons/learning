<template>
    <v-layout row justify-center>
        <v-dialog v-model="dialog" persistent width="600px">
            <template v-slot:activator="{ on }">
                <v-btn
                        @click="exit"
                        fab
                        dark
                        small slot="activator"
                        color="#578dc9">
                    <i class="fas fa-edit"></i>
                </v-btn>
            </template>
            <v-card>
                <v-card-title>
                    <span class="headline">Modifier question</span>
                </v-card-title>
                <v-card-text>
                    <v-container grid-list-md>
                        <v-flex md10>
                            <v-text-field label="Question?"  v-model=exercise.questionExercise prepend-icon="edit"></v-text-field>
                        </v-flex>
                        <v-flex md10>
                            <v-text-field label="Option 1" v-model=exercise.choose1 prepend-icon="edit"></v-text-field>
                        </v-flex>
                        <v-flex md10>
                            <v-text-field  label="Option 2" v-model=exercise.choose2 prepend-icon="edit"></v-text-field>
                        </v-flex>
                        <v-flex md10>
                            <v-text-field label="Option 3" v-model=exercise.choose3 prepend-icon="edit"></v-text-field>
                        </v-flex>
                        <v-flex md10>
                            <v-text-field label="Barème" v-model=exercise.scaleExercise prepend-icon="edit"></v-text-field>
                        </v-flex>
                        <v-flex md10>
                            <v-text-field label="Réponse Correcte" v-model=exercise.answerExercise prepend-icon="edit"></v-text-field>
                        </v-flex>
                    </v-container>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-layout>
                        <v-flex md3>
                            <button class="btn btn-primary" type="button"  @click.prevent="updateQcm(exercise.questionExercise,exercise.choose1,exercise.choose2,exercise.choose3,exercise.scaleExercise,exercise.answerExercise )">Afficher</button>
                        </v-flex>
                        <v-flex  md2>
                            <button class="btn " type="button" @click.prevent="dialog = false">Annuler</button>
                        </v-flex>
                    </v-layout>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-layout>
</template>
<script>
    import axios from 'axios';
    export default {
        props:['exercise'],
        data: function () {
            return {
                dialog:false,
                question:'',
                option1:'',
                option2:'',
                option3:'',
                answer:'',
                scale:'',
               qcm: {
                    'idExercise':null,
                   'idCourse': null,
                   'idExam':null,
                   'questionExercise': '',
                   'answerExercise': '',
                   'scaleExercise':'',
                   'choose1': '',
                   'choose2': '',
                   'choose3': '',
                   'userName':''
               }
            }
        },
        mounted(){

        },
        methods:{
            updateQcm:function(question,option1,option2,option3,scale,answer) {
                this.qcm.idExercise=this.exercise.idExercise;
                this.qcm.idCourse=this.exercise.idCourse;
                this.qcm.idExam=this.exercise.idExam;
                this.qcm.questionExercise=question;
                this.qcm.choose1=option1;
                this.qcm.choose2=option2;
                this.qcm.choose3=option3;
                this.qcm.scaleExercise=scale;
                this.qcm.answerExercise=answer;
                this.qcm.userName=this.exercise.userName;
                axios.put(`/portal/rest/exercise/update`,this.qcm, {
                    headers : {
                        'Content-Type' : 'application/json'
                    }
                }).then(response => {
                    this.qcm= response.data
                    this.dialog=false;
                })
                    .catch(e => {
                    })

            }
        },

    }
</script>
<style>
</style>

