<template>
    <div>
        <v-container class="my-5" elevation-10>
            <v-layout>
                <v-flex md12>
                    <app-edit-cours-tab />
                </v-flex>
            </v-layout>
            <v-layout>
                <v-flex md12>
                    <v-expansion-panel>
                        <v-expansion-panel-content v-for="(c,index) in exercises" :key="c.idExercise">
                            <div slot="header" class="subheading font-weight-bold py-1">Examen {{nameC}}</div>
                            <v-card>
                                <v-card-text class="px-4">
                                        <v-layout>
                                            <v-flex md10>
                                                <div class="font-weight-bold">Question n°{{ index}}: {{c.questionExercise}}</div>
                                            </v-flex>
                                        </v-layout>
                                    <br>
                                        <v-layout>
                                        <v-flex md3>
                                            Option 1
                                            <div class="uiLargeTextField">
                                                <div class="input-xxxlarge font-weight-light black--text">
                                                    <input type="text" :placeholder="c.choose1" disabled>
                                                </div>
                                            </div>
                                        </v-flex>

                                        <v-flex md3>
                                            Option 2
                                            <div class="uiLargeTextField">
                                                <div class="input-xxxlarge font-weight-light black--text">
                                                    <input type="text" :placeholder="c.choose2" disabled>
                                                </div>
                                            </div>
                                        </v-flex>

                                        <v-flex md3>
                                            Option 3
                                            <div class="uiLargeTextField">
                                                <div class="input-xxxlarge font-weight-light black--text">
                                                    <input type="text" :placeholder="c.choose3" disabled>
                                                </div>
                                            </div>
                                        </v-flex>
                                            <v-flex>
                                                Barème
                                                <div class="uiLargeTextField">
                                                    <div class="input-xxxlarge font-weight-light black--text">
                                                        <input type="text" :placeholder="c.scaleExercise" disabled>
                                                    </div>
                                                </div>
                                            </v-flex>
                                        </v-layout>

                                    <br>
                                        <v-layout>
                                        <v-flex md10>
                                            Réponse Correcte
                                            <div class="uiLargeTextField">
                                                <div class="input-xxxlarge font-weight-light black--text">
                                                    <input type="text" :placeholder="c.answerExercise" disabled>
                                                </div>
                                            </div>
                                        </v-flex>
                                        </v-layout>
                                    <v-layout>
                                            <div class="btn-qcm-edit">
                                            <app-edit-qcm :exercise="exercises[index]"></app-edit-qcm>
                                            </div>
                                            <div class="btn-qcm-delete">
                                            <v-btn
                                                    fab
                                                    dark
                                                    small
                                                    color="#BDBDBD"
                                                    @click.prevent="deleteCourse(c.idExercise)">
                                                <i class="far fa-trash-alt"></i>
                                            </v-btn>
                                            </div>
                                    </v-layout>
                                </v-card-text>
                            </v-card>
                        </v-expansion-panel-content>
                    </v-expansion-panel>
                </v-flex>
            </v-layout>
        </v-container>
    </div>
</template>
<script>
    import axios from 'axios';
    import AppEditCoursTab from '../courses/AppEditCoursTabMain.vue'
    import AppEditQcm from './AppEditQcm.vue'


    export default {
        components: {AppEditCoursTab,AppEditQcm},
        data() {
            return {
                idC:this.$route.query.id,
                nameC:this.$route.query.name,
                exercises: []
            }
        },

        mounted() {
                axios.get(`/portal/rest/exercise/getExercisesFromCourse/` + this.idC).then((response) => {
                    this.exercises = response.data;
                    console.log('ok', this.exercises)
                    console.log('id course'+this.idCourse)

                }).catch(error => {
                    console.log(error)
                })
            console.log("val null",this.exercises)
        },
        methods:{

            deleteCourse: function(event)
            {
                console.log('tab',event)
                axios.delete('http://127.0.0.1:8080/portal/rest/exercise/delete/'+event, {
                    headers : {
                        'Content-Type' : 'application/json'
                    }

                }).then(
                    this.exercises=this.exercises.filter(d=>d.idExercise !==event)
                )
            }

        }
    }
</script>
<style>
    .btn-qcm-edit{
        margin-left: 90%;
    }

</style>
