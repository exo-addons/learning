
<template>
    <v-dialog width="600px">
        <v-btn slot="activator" fab dark color="blue darken-3" small>
            <v-icon dark>add</v-icon>
        </v-btn>
        <v-card>
            <v-card-title>
                <h4>Add New Category</h4>
            </v-card-title>
        <v-card-text>
            <v-form class="px-3" ref="form">
                <div v-if="!submitted">
                <v-text-field v-model="libelle" label="libellé Categorie" prepend-icon="folder"  :rules="inputRules"></v-text-field>
                <v-spacer></v-spacer>
                <v-btn small dark @click="submit" color="blue darken-3" class="mx-0 mt-3">Ajouter</v-btn>
                <v-btn outline small  @click="isOpen = !isOpen" color="blue darken-3" class="mx-0 mt-3">Quitter</v-btn>
                </div>
                <div v-else>
                    <h4>You submitted successfully!</h4>
                    <button class="btn btn-success" v-on:click="newCustomer">Add</button>
                </div>
            </v-form>
        </v-card-text>
        </v-card>
    </v-dialog>
</template>

<script>
    import axios from 'axios'

    export default {
        data() {
            return {
                libelle:'',
                isOpen:false,
                submitted: false,
                inputRules: [
                    v => !!v || 'This field is required',
                v => v.length >= 3 || 'Minimum length is 3 characters'
        ]
            }
        },
        methods: {
            submit() {
                    if(this.$refs.form.validate()) {
                        axios.post('http://127.0.0.1:8080/portal/rest/category/addCourse', {
                            nameCategory: this.libelle,
                    }).then((response) => {console.log(response)})
                .catch((e) => {
                        console.error(e)
                })
                        this.submitted = true;
                    }
            }

        },
        newCategory(){
            this.submitted = false,
                this.libelle=""
        }
    }
</script>
