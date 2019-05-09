<template>
    <v-layout row justify-center>
        <v-dialog v-model="dialog" persistent width="600px">
            <template v-slot:activator="{ on }">
                <v-btn flat slot="activator" icon color="#578dc9">
                    <i class="fas fa-clipboard-list fa-3x" style="color:#578dc9;"></i>
                </v-btn>
            </template>
            <v-card>
                <v-card-title>
                    <span class="headline">Ajouter Cours</span>
                </v-card-title>
                <v-card-text>
                    <v-container grid-list-md>
                        <v-flex md10>
                            <p class=" text-sm-left subheading  font-weight-light blue-grey--text text--darken-1">Choisir Cours {{idExamen}}</p>
                            <select
                                    v-model="selectedCourse"
                                    class="select_style">
                                <option value="" >Select Course</option>
                                <option v-for="option in courses" :value="option.idCourse">
                                    {{ option.nameCourse }}
                                </option>
                            </select>
                        </v-flex>
                    </v-container>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-layout>
                        <v-flex md2>
                    <button class="btn btn-primary" type="button"  @click.prevent="selectSearchName">Afficher</button>
                        </v-flex>
                        <v-flex md5>
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

        data: function () {
            return {
                dialog: false,
                selectedCourse: null,
                aa:'',
                courses:[]
            }
        },
        mounted(){
            axios.get(`/portal/rest/cours/all`)
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.courses= response.data
                    console.log("name",this.courses.nameCourse)
                    console.log("contenu courses",this.courses)
                    console.log(this.selectedCourse)
                })
                .catch(e => {
                    this.errors.push(e)
                })
        },
        methods: {
            selectSearchName() {
                for(let i=0;i<this.courses.length;i++){
                    if(this.courses[i].idCourse===this.selectedCourse){
                        this.aa=this.courses[i].nameCourse;
                    }
                }
                this.$router.push('/listeQcm?id='+this.selectedCourse+"&name="+this.aa)
            }
        }
    }
</script>

