<template>
    <div class="team">
        <v-container class="my-5" elevation-1 >
            <v-layout>
                <v-flex md12>
                    <app-edit-cours-tab />
                </v-flex>
            </v-layout>
            <v-layout>
                <v-flex xs12 sm6 offset-sm3>
                    <v-card>
                        <v-card-text>
                            <v-container>
                                <p class=" text-sm-left subheading  font-weight-light blue-grey--text text--darken-1">Choisir Exam</p>
                                <select v-model="selectedCourse">
                                    <option value="" >Select Course</option>
                                    <option v-for="(option,index) in Exams" :value="option.idExam">
                                        {{ option.nameExam}}
                                    </option>
                                </select>

                            </v-container>
                        </v-card-text>
                        <v-card-actions>
                            <v-flex text-xs-center>
                                <button class="btn btn-primary" type="button"  @click.prevent="selectSearchName">Afficher</button>
                                <button class="btn " type="button" @click.prevent="dialog = false">Annuler</button>
                            </v-flex>
                        </v-card-actions>
                    </v-card>
                </v-flex>
            </v-layout>
        </v-container>
    </div>
</template>

<script>
    import axios from 'axios';
    import AppEditCoursTab from '../courses/AppEditCoursTabMain.vue'
    import {bus} from '../../main.js'


    export default {
        props:{
            Exams:{
            }
        },
        components: {AppEditCoursTab},
        data: function () {
            return {
                dialog: false,
                selectedCourse: null,
                examsname:[]
            }
        },
        mounted(){
            bus.$on('transferData',(data)=>{
                this.Exams=data;
                for(let i=0;i<this.Exams.length;i++) {
                    axios.get(`/portal/rest/exercise/getExercisesByIdExam/` + this.Exams[i].idExam)
                        .then(response => {
                            this.examsname[i] = response.data
                        })
                }
            })
            },
        methods: {
            selectSearchName() {
              this.$router.push('/passExam?id='+this.selectedCourse);

            }
        }
    }
</script>




