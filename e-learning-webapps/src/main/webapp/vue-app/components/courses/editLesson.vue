<template>
    <div class="team">
        <v-container class="my-5" elevation-1 >
            <v-card
                    class="mb-5"
                    height="650px">
                <app-edit-cours-tab />

                <v-form ref="form" class="px-3">
                    <v-container>
                        <div >
                            <v-flex md12>
                                <p class=" text-md-left headline font-weight-bold blue-grey--text text--darken-1 ">Général</p>
                            </v-flex>
                            <v-flex md10>
                                <v-textarea
                                        v-model="lessons[0].descriptionLesson"
                                        prepend-icon="edit"
                                        :rules="inputRules" />

                            </v-flex>
                            <v-flex md10>
                                <p class=" text-md-left headline font-weight-bold blue-grey--text text--darken-1 pa-2">Contenu du cours</p>
                            </v-flex>
                            <v-flex md10>
                                <v-text-field
                                        v-model="lessons[0].titleLesson"
                                        label="Titre de Leçon"
                                        prepend-icon="folder"
                                        :rules="inputRules" />
                            </v-flex>
                            <v-flex md10>
                                <v-textarea
                                        v-model="lessons[0].contentLesson"
                                        label="Contenu de Leçon"
                                        prepend-icon="folder"
                                        :rules="inputRules" />
                            </v-flex>
                            <center>

                                <button
                                        type="button" class="btn btn-primary"
                                        @click.prevent="updateLesson(lessons[0].idCourse,lessons[0].idLesson,lessons[0].descriptionLesson,lessons[0].titleLesson,lessons[0].contentLesson)">
                                    Valider
                                </button>
                                <button type="button" class="btn"
                                        @click.prevent="quitter">
                                    Terminer
                                </button>

                            </center>
                        </div>
                    </v-container>
                </v-form>
            </v-card>
        </v-container>
    </div>
</template>
<script>
    import axios from 'axios'
    import AppEditCoursTab from './AppEditCoursTabMain.vue'
    export default {
        components: {
            AppEditCoursTab
        },
        data: function () {
            return {
                idc: this.$route.query.id,
                lessonsupdate:
                    {
                        idLesson:null,
                        idCourse:null,
                        descriptionLesson:'',
                        titleLesson:'',
                        contentLesson:''

                    },
                lessons:[],
                notifications: [],
                inputRules: [
                    v => !!v || 'This field is required',
                    v => v.length >= 3 || 'Minimum length is 3 characters'
                ],
            }
        },
        mounted() {
            axios.get(`/portal/rest/lesson/getLessonbyId/` + this.idc)
                .then(response => {
                    this.lessons.push(response.data);
                    console.log(this.lessons)
                })
        },
        methods: {

            quitter: function () {
                this.$router.push('/gestionLesson')
            },
            updateLesson: function (idCourse,idLesson,descriptionLesson,titleLesson,contentLesson) {
                this.lessonsupdate.idCourse=idCourse;
                this.lessonsupdate.idLesson=idLesson;
                this.lessonsupdate.descriptionLesson=descriptionLesson;
                this.lessonsupdate.titleLesson=titleLesson;
                this.lessonsupdate.contentLesson=contentLesson;
                axios.put(`/portal/rest/lesson/update`,this.lessonsupdate,{
                    headers: {
                        'Content-type': 'application/json',
                    },
                }).then(()=>{
                        this.$router.push('/gestionLesson')
                    }
                )
            },
        }
    }
</script>

