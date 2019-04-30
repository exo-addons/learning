<template>
  <v-card
    class="mb-5"
    height="650px">
    <notification v-bind:notifications="notifications" >
    </notification>

    <v-form ref="form" class="px-3">
      <v-container>
        <v-flex md12>
          <p class=" text-md-left headline font-weight-bold blue-grey--text text--darken-1 ">Général</p>
        </v-flex>
        <v-flex md10>
          <v-textarea
            v-model="lessonGeneral"
            label="Ce qui vous apprendre"
            prepend-icon="edit"
            :rules="inputRules" />
        </v-flex>
        <v-flex md10>
          <p class=" text-md-left headline font-weight-bold blue-grey--text text--darken-1 pa-2">Contenu du course</p>
        </v-flex>
        <v-flex md10>
          <v-textarea
            v-model="lessonContent"
            label="Contenu de Leçon"
            prepend-icon="folder"
            :rules="inputRules" />
        </v-flex>
        <v-flex md10>
          <p class=" text-sm-left subheading  font-weight-light blue-grey--text text--darken-1">libellé Cours</p>
          <select
                  v-model="selectedCourse"
                  class="select_style">
            <option value="" >Select Course</option>
            <option v-for="option in courses" :value="option.idCourse">
              {{ option.nameCourse }}
            </option>
          </select>
        </v-flex>
        <v-layout>
        <v-flex>
          <v-btn Product not created

                  depressed
                  large
                  color="#1867c0"
                  class="white--text"
                  @click="addLesson">
            Valider
          </v-btn>
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
      </v-container>
    </v-form>
  </v-card>
</template>
<script>
    import axios from 'axios'
    import Notification from './notifications.vue';

    export default {
        components:{
          Notification
        },
      data: function () {
            return {
                lessonName:'',
                lessonGeneral:'',
                lessonContent:'',
                selectedCourse:'',
                alerte:false,
                courses:{},
                lesson:{
                    idLesson:null,
                    contentLesson:'',
                    descriptionLesson:'',
                    idCourse:null
                },
                notifications:[]
            }
        },

        updated(){
            axios.get(`http://127.0.0.1:8080/portal/rest/cours/all`)
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.courses= response.data
                    console.log(this.selectedCourse)
                })
                .catch(e => {
                    this.errors.push(e)
                })
        },
                methods: {
                    cancel(){
                        this.alerte=false;

                        console.log(this.alerte);
                    },
                    quitter: function () {
                        this.$router.push('/')
                    },
                    addLesson: function()
                    {
                        this.lesson.idCourse=this.selectedCourse;
                        this.lesson.contentLesson=this.lessonContent;
                        this.lesson.descriptionLesson=this.lessonGeneral;
                        console.log(this.lesson);
                        axios.post('http://127.0.0.1:8080/portal/rest/lesson/add', this.lesson, {
                            headers : {
                                'Content-Type' : 'application/json'
                            }
                        }).then((response) => {
                            this.notifications.push({
                                type: 'success',
                                message: 'Lesson created successfully'
                            });
                                this.lessonContent='';
                                this.lessonGeneral='';

                            },
                            (response) => {
                            this.notifications.push({
                                type: 'error',
                                message: 'Lesson not created'
                            });
                        });
                    }
                },
}
</script>

