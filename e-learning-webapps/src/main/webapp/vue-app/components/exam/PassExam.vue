<template>
            <v-container grid-list-md text-xs-center class="my-5" elevation-10>
                <v-layout>
                    <v-flex md12>
                        <app-edit-cours-tab />
                    </v-flex>
                </v-layout>
                        <div class="t1">
                        <v-card  >
                            <v-card-text>{{time}}</v-card-text>
                        </v-card>
                        </div>
                    <div class="cardTemp" v-for="(c,index) in questions" :key="c.idExercise" >
                        <v-card >
                            <v-card-title>
                                <label class="mt-2">Question</label>
                                <div class="Q1">
                                    <input type="text" style="width:422%" v-model="c.questionExercise" disabled>

                                </div>


                            </v-card-title>
                            <v-card-title>
                                <label >Réponse</label>

                            </v-card-title>
                            <v-card-text>
                                <div class="Q2">
                                    <div class="ml-5">
                                        <v-layout>
                                            <v-radio-group v-model="courseStatus" >
                                                <v-radio
                                                        :label=c.choose1
                                                        color="blue"
                                                        :value="c.choose1" />
                                                <v-radio
                                                        :label="c.choose2"
                                                        color="blue"
                                                        :value="c.choose2" />
                                                <v-radio
                                                        :label="c.choose3"
                                                        color="blue"
                                                        :value="c.choose3" />
                                            </v-radio-group>
                                        </v-layout>
                                </div>
                                </div>
                            </v-card-text>
                            <v-card-title>
                                <div class="Q5">
                                    <button class="btn-primary" >save</button>
                                </div>
                            </v-card-title>
                        </v-card>
         <br>
                        <br>


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
                aa:null,
                cmpte:null,
                courseStatus:'',
                date: moment(60 * 0.1 * 1000),
                questionIndex: 0,
                idC:this.$route.query.id,
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
            axios.get(`/portal/rest/exercise/getExercisesByIdForOther/` + this.idC).then((response) => {
                this.questions = response.data;
                console.log('ok', this.questions)
            }).catch(error => {
                console.log(error)
            })
            console.log("val null",this.questions)
        },

        methods: {
            validateAnswer(event){
                for(let i=0;i<this.questions.length;i++){
                    console.log(this.questions[i].answerExercise)
                    console.log(this.courseStatus)
                    if(this.questions[i].answerExercise==this.courseStatus)
                    {
                        this.cmpte+=this.questions[i].scaleExercise;
                        console.log(this.cmpte)
                    }
                    }
                }
            }
    }
</script>
<style>
    .t1{
        width: 15%;
    }
    .cardTemp{
        margin-bottom: 1%;
        width: 83%;
        margin-left: 16%;
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
</style>
