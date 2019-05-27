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
                                <p class=" text-sm-left subheading  font-weight-light blue-grey--text text--darken-1">Choisir Cours</p>
                                <select v-model="selectedCourse">
                                    <option value="" >Select Course</option>
                                    <option v-for="option in courses" :value="option.idCourse">
                                        {{ option.nameCourse }}
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

    export default {

        components: {AppEditCoursTab},
        data: function () {
            return {
                dialog: false,
                selectedCourse: null,
                namec:'',
                courses:[],
            }
        },
        mounted(){
            axios.get(`/portal/rest/cours/allPublishedByUser/PUBLISHED`)
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.courses= response.data
                    console.log("name",this.courses.nameCourse)
                    console.log("contenu courses",this.courses)
                    console.log(this.selectedCourse)
                })
                .catch(e => {
                    this.errors.push(e)
                })
            axios.get(`/portal/rest/exam/allExamByUserName/`+eXo.env.portal.userName)
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.exams= response.data

                })
        },
        methods: {
            selectSearchName() {
                this.$router.push('/listeQcm?idc='+this.selectedCourse+"&ide="+this.selectedExam)


            }
        }
    }
</script>




