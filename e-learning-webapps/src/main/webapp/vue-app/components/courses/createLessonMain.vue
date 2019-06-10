<template>
    <div class="team">
        <v-container class="my-5" elevation-1 >
    <v-card
            class="mb-5"
            height="650px">
        <app-edit-cours-tab />
        <v-form ref="form" class="px-3">
            <v-container>
                <v-flex md12>
                    <p class=" text-md-left headline font-weight-bold blue-grey--text text--darken-1 ">Général</p>
                </v-flex>
                <v-flex md10>
                    <v-textarea
                            v-model="lessonGeneral"
                            label="Ce qui vous apprendre"
                            prepend-icon="edit"
                            :rules="inputRules" />
                </v-flex>
                <v-flex md10>
                    <v-text-field
                            v-model="lessonTitle"
                            label="Titre de Leçon"
                            prepend-icon="folder"
                            :rules="inputRules" />
                </v-flex>

                <v-flex md10>
                    <p class=" text-sm-left subheading  font-weight-light blue-grey--text text--darken-1">libellé Cours</p>
                    <select
                            v-model="selectedCourse"
                            class="select_style">
                        <option value disabled>Select Course</option>
                        <option v-for="option in courses" :value="option.idCourse">
                            {{ option.nameCourse }}
                        </option>
                    </select>
                </v-flex>
                <v-flex md10>
                    <p class=" text-md-left headline font-weight-bold blue-grey--text text--darken-1 pa-2">Contenu du cours</p>
                </v-flex>
                <v-flex md10>
                    <v-textarea
                            v-model="lessonContent"
                            label="Contenu de Leçon"
                            prepend-icon="folder"
                            :rules="inputRules" />
                </v-flex>

                <v-layout>
                    <v-flex>
                        <button
                                type="button" class="btn btn-primary"
                                @click.prevent="addLesson">
                            Ajouter
                        </button>
                        <button
                                type="button" class="btn"
                                @click.prevent="quitter">
                            Terminer
                        </button>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-form>
    </v-card>
        </v-container>
    </div>
</template>
<script>
    import axios from 'axios'
    import { bus } from '../../main';
    import AppEditCoursTab from './AppEditCoursTabMain.vue'

    export default {
        props:['contentcourse'],
        components: {AppEditCoursTab},
        data: function () {
            return {
                lessonTitle:'',
                lessonName:'',
                lessonGeneral:'',
                lessonContent:'',
                selectedCourse:'',
                alerte:false,
                courses:[],
                lesson:{
                    idLesson:null,
                    titleLesson:'',
                    contentLesson:'',
                    descriptionLesson:'',
                    idCourse:null
                },
                notifications:[],
                inputRules: [
                    v => !!v || 'This field is required',
                    v => v.length >= 3 || 'Minimum length is 3 characters'
                ],
            }
        },
        created(){
            bus.$on('CourseChanged', (data) => {
                this.contentcourse=data;
                this.courses.push(this.contentcourse)

            });
        },

        mounted(){
            axios.get(`/portal/rest/cours/all`)
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.courses= response.data
                })
                .catch(e => {
                    this.errors.push(e)
                })
        },
        methods: {
            cancel(){
                this.alerte=false;
            },
            quitter: function () {
                this.$router.push('/')
            },
            addLesson: function()
            {
                this.lesson.idCourse=this.selectedCourse;
                this.lesson.contentLesson=this.lessonContent;
                this.lesson.descriptionLesson=this.lessonGeneral;
                this.lesson.titleLesson=this.lessonTitle;
                axios.post('/portal/rest/lesson/add', this.lesson, {
                    headers : {
                        'Content-Type' : 'application/json'
                    }
                }).then((response) => {
                        this.notifications.push({
                            type: 'success',
                            message: 'Lesson created successfully'
                        });
                        this.lessonContent='';
                        this.lessonGeneral='';
                        this.lessonTitle='';

                    },
                    (response) => {
                        this.notifications.push({
                            type: 'error',
                            message: 'Lesson not created'
                        });
                    });
            }
        },
    }
</script>

