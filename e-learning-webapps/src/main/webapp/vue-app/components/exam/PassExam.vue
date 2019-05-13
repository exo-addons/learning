<template>
            <v-container grid-list-md text-xs-center class="my-5" elevation-10>
                <v-layout>
                    <v-flex md12>
                        <app-edit-cours-tab />
                    </v-flex>
                </v-layout>
                <v-layout column>
                    <v-flex>
                        <v-card width="200px" height="200px">
                            <v-card-text>{{time}}</v-card-text>
                        </v-card>
                    </v-flex>

                    <v-flex style="width:500px" v-for="(c,index) in questions" :key="c.idExercise" >
                        <div class="cardTemp">
                        <v-card>
                            <v-card-text>
                                <form>
                                    <v-layout>
                                        <label class="mt-2">Question</label>
                                        <div class="uiLargeTextField">
                                            <div class="input-xxxlarge ml-2">
                                                <input type="text" v-model="c.questionExercise" disabled>
                                            </div>
                                        </div>
                                    </v-layout>
                                    <div>
                                        <label >Réponse</label>
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
                                    <center><button class="btn btn-primary mt-4" type="button" @click="validateAnswer">valider</button></center>
                                </form>
                            </v-card-text>
                        </v-card>
                        </div>
                    </v-flex>
                </v-layout>
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
    .cardTemp{

    }
</style>
