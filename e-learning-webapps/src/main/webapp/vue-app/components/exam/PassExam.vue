<template>
            <v-container grid-list-md text-xs-center class="my-5" elevation-10>
                <v-layout>
                    <v-flex md12>
                        <app-edit-cours-tab />
                    </v-flex>
                </v-layout>
                <v-layout>
                    <div class="t1 mt-2">
                        <v-card>
                            <v-card-text>{{time}}</v-card-text>
                        </v-card>
                    </div>

                    <div class="t2 mt-2">
                        <v-card>
                            <v-card-text>{{this.note}}/{{this.global}}</v-card-text>
                        </v-card>
                    </div>
                </v-layout>
                    <div class="cardTemp" v-for="(c,index) in questions" :key="c.idExercise" >
                        <v-card >
                            <v-card-title>
                                <h3>{{c.idExam}}</h3>
                                <label class="mt-2">Question</label>
                                <div class="Q1">
                                    <input type="text" style="width:300%" v-model="c.questionExercise" disabled>

                                </div>
                            </v-card-title>
                            <v-card-title>
                                <label >Réponse</label>
                            </v-card-title>
                            <v-card-text>
                                <div class="Q2">
                                    <div class="ml-5">
                                        <v-layout>
                                            <v-radio-group v-model="courseStatus[index]" >
                                                <v-radio
                                                        id="a"
                                                        :label=c.choose1
                                                        color="blue"
                                                        :value="c.choose1" />
                                                <v-radio
                                                        id="b"
                                                        :label="c.choose2"
                                                        color="blue"
                                                        :value="c.choose2" />
                                                <v-radio
                                                        id="c"
                                                        :label="c.choose3"
                                                        color="blue"
                                                        :value="c.choose3" />
                                            </v-radio-group>

                                        </v-layout>
                                </div>
                                </div>
                            </v-card-text>
                        </v-card>
                        <br>
                        <br>

                    </div>
                <div class="Q5">
                    <button class="btn-primary" @click.prevent="validateAnswer(index)">valider</button>
                    <button class="btn" @click="quitter">annuler</button>

                </div>

            </v-container>
</template>
<script>
    import AppEditCoursTab from '../courses/AppEditCoursTabMain.vue'
    import axios from 'axios';
    import moment from 'moment';


    export default {
        components:{
            AppEditCoursTab
        },
        data () {
            return {

                counter:0,
                note:0,
                global:0,
                courseStatus:[],
                date: moment(60 * 0.1 * 1000),
                questionIndex: 0,
                idE:this.$route.query.id,
                questions:[],
                userResponses:false
            }
        },
        computed: {
            time: function(){
                return this.date.format('mm:ss');
            }
        },
        mounted() {
                setInterval(() => {
                    this.date = moment(this.date.subtract(1, 'seconds'))
                }, 1000);
            axios.get(`/portal/rest/exercise/getExercisesByIdExam/` + this.idE).then((response) => {
                this.questions = response.data;
                console.log('ok', this.questions)
            }).catch(error => {
                console.log(error)
            })
            console.log("val null",this.questions)
        },

        methods: {
            validateAnswer(c) {
                this.counter++;
                console.log("val compteur",this.counter)
                if (this.counter < 2) {
                    for (let i = 0; i < this.questions.length; i++) {
                        console.log("correcte", this.questions[i].answerExercise)
                        console.log("ok sta", this.courseStatus[i])
                        this.global += this.questions[i].scaleExercise
                        if (this.questions[i].answerExercise == this.courseStatus[i]) {
                            this.note += this.questions[i].scaleExercise;
                            e = e - 1;
                            // console.log(this.note)
                        }
                    }

                    console.log("valeur final", this.note, "/", this.global)

                }
            },
            quitter: function () {
                this.$router.push('/')
            },
            }
    }
</script>
<style>
    .t1{
        width: 15%;
    }
    .cardTemp{
        margin-bottom: 1%;
        width: 60%;
        margin-left: 17%;
        margin-top: -4%;
    }
    .Q1{
        margin-left: 5%;
        margin-top: 1%;
    }
    .Q2{
        margin-top: -6%;
        margin-left: 2%;
    }
    .t2 {
        width: 16%;
        margin-left: 83%;
    }
</style>
