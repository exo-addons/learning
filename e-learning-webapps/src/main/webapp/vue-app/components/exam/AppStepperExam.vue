<template>
    <v-container class="my-5" elevation-10>
                <app-edit-cours-tab />
        <v-layout>
            <v-flex md12>
                <v-stepper
                        v-model="e1"
                        flat
                        class="text-xs-center ma-3"
                        height="650px">
                    <v-stepper-header>
                        <v-stepper-step
                                :complete="e1 > 1"
                                step="1"
                                color="blue">
                            <h5 class="blue--text .headline">Ajouter Examen</h5>
                        </v-stepper-step>

                        <v-divider />

                        <v-stepper-step step="2" color="blue"><h5 class="blue--text .headline">Ajouter Contenu Examen</h5></v-stepper-step>
                    </v-stepper-header>

                    <v-stepper-content step="1">
                        <notification v-bind:notifications="notifications"></notification>
                        <app-create-exam></app-create-exam>
                        <v-layout>
                            <v-flex>
                                <v-btn
                                        depressed
                                        large
                                        dark
                                        color="#1867c0"
                                        class="white--text"
                                        @click.prevent="saveCourse">
                                    Save
                                </v-btn>
                            </v-flex>
                            <v-flex md2>
                                <v-btn flat icon color="indigo">
                                <i class="fas fa-forward fa-3x" style="color:#1867c0;" @click.prevent="e1 = 2"></i>
                                </v-btn>
                            </v-flex>
                        </v-layout>
                    </v-stepper-content>

                    <v-stepper-content step="2">

                        <v-layout>
                            <v-flex md2>
                                <i class="fas fa-backward fa-3x" style="color:#1867c0;" @click="e1 = 1"></i>
                            </v-flex>
                            <v-flex>
                                <v-btn
                                        depressed
                                        large
                                        color="#1867c0"
                                        class="white--text"
                                        @click="quitter">
                                    Terminer
                                </v-btn>
                            </v-flex>
                        </v-layout>
                    </v-stepper-content>
                </v-stepper>
            </v-flex>
        </v-layout>
    </v-container>
</template>


<script>
    import AppCreateExam from './AppCreateExam.vue'
    import {en, fr} from 'vuejs-datepicker/dist/locale'
    import moment from 'moment';
    import Notification from '../commun/notifications.vue';
    Vue.filter('formatDate', function(value) {
        if (value) {
            const lang = eXo.env.portal.language;
            moment.locale(lang);
            return moment(String(value), value.includes('/') ? 'DD/MM/YYYY' : 'YYYY-MM-DD').format(lang == 'fr' ? 'DD MMMM YYYY' : 'MMMM DD YYYY');
        }
    });
    export default {
        components:{AppCreateExam, 'notification' : Notification},
        data () {
            return {
                notifications:[],
                status:null,
                en: en,
                fr: fr,
                menu: false,
                options: [
                    {idCategory:''},
                    {nameCategory:''}
                ],
                selectedOption:{},
                selectedCategory:'',
                question:'',
                libelleChapitre:'',
                TitreLecon:'',
                contenu_lecon:'',
                switch1: true,
                e1: 0,
                config: {
                    format: 'YYYY-MM-DD',
                    useCurrent: false,
                },
                course:{
                    visibilityCourse:false,
                    status:'DRAFTED'
                },
                name_course:'',
                nbre_pers: '',
                content: '',
                category:'',
                dateStart:'',
                dateEnd:'',
                inputRules: [
                    v => !!v || 'This field is required',
                    v => v.length >= 3 || 'Minimum length is 3 characters'
                ]
            }
        },
    }
</script>
<style>
    .para_border{
        width:600px;
        border: 4px dotted lightslategrey;
    }
    .select_style {
        border: 1px solid;
        border-color: #929b9c;
        border-radius: 5px;
        outline: none;
        width: 100%;
        height: 35px;
    }
    i.uiIconMiniArrowDown.uiIconLightGray {
        color: #ffffff;
        font-size: 23px;
        top: 29px;
        float: right;
        margin-right: 10px;
    }
    input#dateStart {
        background: transparent;
        cursor: pointer;
    }
    input#dateEnd {
        background: transparent;
        cursor: pointer;
    }
</style>

