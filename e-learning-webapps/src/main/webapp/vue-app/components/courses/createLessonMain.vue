<template>
    <div class="team">
        <v-container class="my-5" elevation-1 >
    <v-card
            class="mb-5"
            height="650px">
        <app-edit-cours-tab />
        <v-form ref="form" class="px-3">
            <div>
                <div v-if="alt" class="alert alert-error" style="    margin-left: 11%;
    width: 74%;">
                    <i class="uiIconClose" @click="DontShow"></i>Remplire tout les champs
                </div>
            </div>
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

                <center>
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
                </center>
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
                alt:false,
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
            DontShow() {
                this.alt = false;
            },
            cancel() {
                this.alerte = false;
            },
            quitter: function () {
                this.$router.push('/gestionLesson')
            },
            addLesson: function () {
                if (this.lessonContent === '' || this.lessonGeneral === '' || this.lessonTitle) {
                    this.alt = true;
                }
                if (this.alt === false) {
                    this.lesson.idCourse = this.selectedCourse;
                    this.lesson.contentLesson = this.lessonContent;
                    this.lesson.descriptionLesson = this.lessonGeneral;
                    this.lesson.titleLesson = this.lessonTitle;
                    axios.post('/portal/rest/lesson/add', this.lesson, {
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    }).then(() => {
                            this.lessonContent = '';
                            this.lessonGeneral = '';
                            this.lessonTitle = '';

                        });
                }
            }
        }
    }
</script>

