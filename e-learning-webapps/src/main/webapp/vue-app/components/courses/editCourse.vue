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
                    <i class="fas fa-edit fa-2x"></i>
                </v-btn>
            </template>
            <v-card>
                    <v-card-title>
                        <span class="headline">Modifier Course</span>
                    </v-card-title>
                <v-card-text>
                <v-flex md10>
                        <v-text-field label="Question?"   v-model="course.nameCourse" prepend-icon="edit"></v-text-field>
                    </v-flex>
                    <v-flex md10>
                        <v-text-field label="Option 1"  v-model="course.nbPerson" prepend-icon="edit"></v-text-field>
                    </v-flex>
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
                    <v-layout>
                        <v-flex md4>
                            <p class=" text-sm-left subheading  font-weight-light blue-grey--text text--darken-1">Statut</p>
                        </v-flex>
                        <v-radio-group v-model="courseStatus" row>
                            <v-radio
                                    label="Draft"
                                    color="blue"
                                    value="DRAFT" />
                            <v-radio
                                    label="Archived"
                                    color="blue"
                                    value="ARCHIVED" />
                            <v-radio
                                    label="Published"
                                    color="blue"
                                    value="PUBLISHED" />
                        </v-radio-group>
                    </v-layout>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-layout>
                        <v-flex md3>
                            <button class="btn btn-primary" type="button"  @click.prevent="updateQcm(course.nameCourse,course.nbPerson,course.rewardCourse,courseStatus)">Valider</button>
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
        props:['course'],
        data: function () {
            return {
                courseStatus: 'DRAFT',
                dialog:false,
                currentcourse:{
                    idCourse:null,
                    nameCourse:'',
                    nbPerson:null,
                    rewardCourse:'',
                    status:''
                }
            }
        },
        mounted(){

        },
        methods:{
            updateQcm:function(name,nb,reward,status) {
                this.currentcourse.idCourse=this.course.idCourse;
                this.currentcourse.nameCourse=this.course.nameCourse;
                this.currentcourse.nbPerson=this.course.nbPerson;
                this.currentcourse.rewardCourse=this.course.rewardCourse;
                this.currentcourse.status=this.courseStatus;
                axios.put(`/portal/rest/cours/update`,this.currentcourse, {
                    headers : {
                        'Content-Type' : 'application/json'
                    }
                }).then(response => {
                    this.currentcourse= response.data
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

