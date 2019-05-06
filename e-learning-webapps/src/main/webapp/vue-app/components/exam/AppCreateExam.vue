<template>
    <v-card
            class="mb-5"
            height="500px">
        <v-card-text>
            <v-form ref="form" class="px-3">
                <v-container>

                <v-flex md10>
                    <v-text-field
                            v-model="nameExam"
                            label="Libellé Examen"
                            prepend-icon="folder"
                            :rules="inputRules" />
                </v-flex>
                    <v-flex md10>
                <v-layout>
                        <v-flex md4>
                            <p class=" text-sm-left subheading  font-weight-light blue-grey--text text--darken-1">Récompense</p>
                        </v-flex>
                        <v-layout>
                            <v-flex md4>
                                <v-checkbox
                                        v-model="rewardExam"
                                        label="Badge"
                                        value="course" />
                            </v-flex>
                            <v-flex>
                                <v-checkbox
                                        v-model="rewardExam"
                                        label="Certification"
                                        value="certification" />
                            </v-flex>
                        </v-layout>
                    </v-layout>
                    </v-flex>
                    <v-flex md10>
                    <v-text-field
                            v-model="nbrBidExam"
                            label="Tentative"
                            prepend-icon="folder"
                            :rules="inputRules" />

                </v-flex>
                    <v-flex md10>
                    <v-layout>
                        <v-flex md6>
                            <p class=" text-md-left subheading  font-weight-light blue-grey--text text--darken-1">Date de début </p>
                            <Datepicker
                                    id="dateStart"
                                    v-model="dateStartExam"
                                    placeholder="Select Date Start"
                                    monday-first="true"
                                    :format="formatDate"
                                    :language="fr" />
                        </v-flex>
                        <v-flex md6>
                            <p class=" text-md-left subheading  font-weight-light blue-grey--text text--darken-1">Date de Fin</p>
                            <Datepicker
                                    id="dateEnd"
                                    v-model="dateEndExam"
                                    placeholder="Select Date End"
                                    monday-first="true"
                                    :format="formatDate"
                                    :language="fr" />
                        </v-flex>
                        <v-spacer />
                    </v-layout>
                    </v-flex>
                    <v-flex md10>
                        <v-text-field
                                v-model="dureeExam"
                                label="Durée Examen"
                                prepend-icon="folder"
                                :rules="inputRules" />
                    </v-flex>
                </v-container>
                <v-flex>
                    <button class="btn btn-primary" type="button"  @click.prevent="addExam">Ajouter</button>
                    <button class="btn " type="button">Annuler</button>
                </v-flex>

            </v-form>
                </v-card-text>
            </v-card>
</template>

<script>
    import axios from 'axios';
    import {bus} from '../../main.js';
    import Datepicker from 'vuejs-datepicker';
    import moment from 'moment';
    Vue.filter('formatDate', function(value) {
        if (value) {
            const lang = eXo.env.portal.language;
            moment.locale(lang);
            return moment(String(value), value.includes('/') ? 'DD/MM/YYYY' : 'YYYY-MM-DD').format(lang == 'fr' ? 'DD MMMM YYYY' : 'MMMM DD YYYY');
        }
    });

    export default {
        components:{
            Datepicker
        },
        props:{
            idExamen:{
                type:Number
            }
        },
        data:function(){
            return{
                childData:'',
                nameExam:'',
                rewardExam:'',
                nbrBidExam:'',
                dateStartExam:'',
                dateEndExam:'',
                dureeExam:'',
                exam:{
                    nameExam: '',
                    dateStartExam: '',
                    dateEndExam:'',
                    nbBidExam:'',
                    rewardExam:'',
                    dureeExam:''
                },
                config: {
                    format: 'YYYY-MM-DD',
                    useCurrent: false,
                }
            }
        },

        methods:{
            addExam:function() {
                this.exam.nameExam=this.nameExam;
                this.exam.rewardExam=this.rewardExam;
                this.exam.nbBidExam=this.nbrBidExam;
                this.exam.dateStartExam=this.dateStartExam;
                this.exam.dateEndExam=this.dateEndExam;
                this.exam.dureeExam=this.dureeExam;
                axios.post(`/portal/rest/exam/add`,this.exam, {
                    headers : {
                        'Content-Type' : 'application/json'
                    }
            }).then(response => {
                        this.exam= response.data
                    console.log("contenu exam",this.exam.idExam)
                    this.idExamen=this.exam.idExam
                    bus.$emit('sendIdExam',this.exam.idExam)
                    this.nameExam='';
                        this.rewardExam='';
                        this.nbrBidExam='';
                        this.dureeExam='';
                    })
                    .catch(e => {
                    })

            }
        }
    }
</script>

<style scoped>

</style>
