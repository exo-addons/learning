<template>
    <v-card
            class="mb-5"
            height="700px">
        <v-card-text>
            <v-form ref="form" class="px-3">
                <v-container>

                <v-flex md10>
                    <v-text-field
                            v-model="nameCourse"
                            label="Libellé"
                            prepend-icon="folder"
                            :rules="inputRules" />

                </v-flex>
                </v-container>

            </v-form>
                </v-card-text>
            </v-card>
</template>

<script>
    import Datepicker from 'vuejs-datepicker';
    /*import moment from 'moment';
    Vue.filter('formatDate', function(value) {
        if (value) {
            const lang = eXo.env.portal.language;
            moment.locale(lang);
            return moment(String(value), value.includes('/') ? 'DD/MM/YYYY' : 'YYYY-MM-DD').format(lang == 'fr' ? 'DD MMMM YYYY' : 'MMMM DD YYYY');
        }
    });*/

    export default {
        components:{
            Datepicker
        },

        data:function(){
            return{
                nameCourse:''

            }
        }
    }
</script>

<style scoped>

</style>
