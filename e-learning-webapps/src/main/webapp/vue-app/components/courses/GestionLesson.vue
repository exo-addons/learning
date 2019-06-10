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
                                    <option v-for="(option,index) in courses" :value="option.idCourse">
                                        {{ option.nameCourse }}
                                    </option>
                                </select>
                                <v-btn color="#578dc9"  @click.prevent="selectSearchLesson">
                                    <i class="fas fa-search fa-2x" style="color: #ffffff"></i>
                                </v-btn>
                                <v-btn color="#BDBDBD"  @click.prevent="addLesson">
                                    <i class="fas fa-plus fa-2x" style="color: #ffffff"></i>
                                </v-btn>
                                <div class="tabEdit">
                                <table class="uiGrid table table-hover table-striped" v-if="alt">
                                    <thead>
                                    <tr>
                                        <th >Lesson Title</th>


                                        <th >Lesson Description</th>


                                        <th >Actions</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr  v-for="(c,index) in lessons" :key="c.idCourse">
                                        <td>{{c.titleLesson}}</td>
                                        <td>{{c.descriptionLesson}}</td>
                                        <td class="center actionContainer">
                                            <a data-placement="bottom" rel="tooltip" class="actionIcon" data-original-title="Edit" @click="editLesson(c.idLesson)">
                                                <i class="uiIconViewUserInfo uiIconLightGray"></i>
                                            </a>

                                            <a data-placement="bottom" rel="tooltip" class="actionIcon" data-original-title="Delete" @click="deleteLesson(c.idLesson)">
                                                <i class="uiIconDeleteUser uiIconLightGray"></i>
                                            </a>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                                </div>
                            </v-container>
                        </v-card-text>

                    </v-card>
                </v-flex>
            </v-layout>
        </v-container>
    </div>
</template>

<script>
    import axios from 'axios';
    import AppEditCoursTab from './AppEditCoursTabMain.vue'

    export default {
        components: {AppEditCoursTab},
        data: function () {
            return {
                dialog: false,
                selectedCourse: null,
                namec:'',
                courses:[],
                lessons:[],
                alt:false
            }
        },
        mounted(){
            axios.get(`/portal/rest/cours/allPublishedByUser/PUBLISHED`)
                .then(response => {
                    this.courses= response.data
                })
                .catch(e => {
                    this.errors.push(e)
                })
        },
        methods: {
            selectSearchLesson() {
                axios.get(`/portal/rest/lesson/getLessonsByIdCourse/`+this.selectedCourse)
                    .then(response=>{
                        this.lessons=response.data;
                        if(this.lessons.length!==0){
                            this.alt=true;
                        }
                    })

            },
            addLesson(){
                this.$router.push('/createLesson');

            },
            editLesson(event){
                this.$router.push('/editLesson?id='+event);
            },
            deleteLesson(event){
                {
                    console.log(event)
                    axios.delete('/portal/rest/lesson/delete/'+event, {
                        headers : {
                            'Content-Type' : 'application/json'
                        }

                    }).then(
                        this.lessons=this.lessons.filter(d=>d.idLesson !==event)
                    )
                }
            }
        }
    }
</script>
<style>
    .tabEdit {
        width: 92%;
    }
</style>




