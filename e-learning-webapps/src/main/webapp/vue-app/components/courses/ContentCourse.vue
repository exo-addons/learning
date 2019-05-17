<template>
    <div class="team">
        <v-container class="my-5" elevation-1>
                <div class="alert alert-warning" v-if="alt">
                    <i class="uiIconWarning"></i>Aucun Idée redigée  .


                </div>
                <div v-for="d in lessons" :key="d.idLesson">
            <v-card height='600px'>
                <v-toolbar dark color="#3972B1">
                    <v-toolbar-title class="lead">Contenu du cours: {{d.titleLesson}}</v-toolbar-title>
                    <v-spacer></v-spacer>
                </v-toolbar>
                <v-card-text>
                    <div class="table-special">
                    <table class="uiGrid table">
                        <td class="empty ">
                            <tr>{{d.descriptionLesson}}</tr>
                        <tr>{{d.contentLesson}}</tr>
                        </td>
                    </table>
                    </div>
                </v-card-text>

            </v-card>
                    <br>
                    <br>
                </div>
        </v-container>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        data(){
            return{
                alt:false,
                lessons:[]
            }
        },
        mounted(){
            axios
                .get('/portal/rest/lesson/all')
                .then(response => { this.lessons=response.data;
                    if(this.lessons.length===0){
                        this.alt=true;
                    }
                })
                .catch(error => {
                    console.log(error)
                })
        }
    }
</script>

<style scoped>
    .table-special{
        width: 97%;
        margin-left: 0.25%;
    }

</style>
