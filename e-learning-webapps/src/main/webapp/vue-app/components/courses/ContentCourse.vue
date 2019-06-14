<template>
    <div class="team">
        <v-container class="my-5" elevation-1>
            <AppEditCoursTab></AppEditCoursTab>

                   <center> <h1 style="color: #476A9C">Cours</h1></center>
                <div v-for="(d,index) in lessons" :key="d.idLesson">
            <v-card height='600px'>
                <v-toolbar dark color="#3972B1">
                    <v-toolbar-title class="lead">Leçon n°:{{index}} {{d.titleLesson}}</v-toolbar-title>
                    <v-spacer></v-spacer>
                </v-toolbar>
                <v-card-text>
                    <div class="table-special">
                    <table class="uiGrid table">
                        <td class="text-content">
                            <tr>
                                <td><p class="title-content">Description Leçon:</p></td>
                                <td>{{d.descriptionLesson}}</td></tr>
                            <tr>
                                <td><p class="title-content">Contenu Leçon:</p></td>
                                <td>{{d.contentLesson}}</td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <p class="text-md-right"><b>Auteur:</b>
                                    {{d.userName}}
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                <center>
                                <video width="450" controls :src="video"></video>
                                </center>
                                </td>
                            </tr>
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
    import AppEditCoursTab from './AppEditCoursTabMain.vue'
    export default {
        components:{AppEditCoursTab},
        data(){
            return{
                link:this.$route.query.id,
                video: "https://www.dev2qa.com/wp-content/uploads/2018/11/spring-boot-web-mvc-with-h2-database-example-demo.mp4",
                alt:false,
                lessons:[]
            }
        },
        mounted(){
            console.log("effrfrfrff",this.link)
            axios.get('/portal/rest/lesson/getLessonsByIdCourse/'+this.link)
                .then(response => { this.lessons=response.data;
                    if(this.lessons.length===0){
                        this.alt=true;
                    }
                })
                .catch(error => {
                })
        }
    }
</script>

<style scoped>
    .table-special{
        width: 97%;
        margin-left: 0.25%;
    }
    .text-content {
        margin: 0;
        font-family: roberto sans-serif !important;
        font-size: 14px;
        line-height: 20px;
        color: #333333;
        background-color: #ffffff;
    }

    .title-content {
        margin: 0;
        font-family: roberto sans-serif !important;
        font-size: 20px;
        line-height: 32px;
        color: #333333;
        background-color: #ffffff;
        font-weight: bold !important;
    }
    .uiGrid.table td{
        border-left: none!important;
    }

</style>
