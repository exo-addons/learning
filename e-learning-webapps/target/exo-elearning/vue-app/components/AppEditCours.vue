<template>
    <div class="team">

        <div>
            <v-dialog
                    v-model="dialog"
                    max-width="500px">
                <v-card>
                    <v-card-title>
                        <span class="headline">List des cours</span>
                    </v-card-title>

                    <v-card-text>
                        <v-container grid-list-md>
                            <v-layout wrap>
                                <v-flex
                                        xs12
                                        sm6
                                        md4>
                                    <v-text-field v-model="editedItem.fat" label="Fat (g)" />
                                </v-flex>
                                <v-flex
                                        xs12
                                        sm6
                                        md4>
                                    <v-text-field v-model="editedItem.carbs" label="Carbs (g)" />
                                </v-flex>
                                <v-flex
                                        xs12
                                        sm6
                                        md4>
                                    <v-text-field v-model="editedItem.protein" label="Protein (g)" />
                                </v-flex>
                            </v-layout>
                        </v-container>
                    </v-card-text>

                    <v-card-actions>
                        <v-spacer />
                        <v-btn
                                color="blue darken-1"
                                flat
                                @click="close">
                            Cancel
                        </v-btn>
                        <v-btn
                                color="blue darken-1"
                                flat
                                @click="save">
                            Save
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </div>

        <v-data-table
                :headers="headers"
                :items="desserts"
                class="elevation-1">
            <template slot="items" slot-scope="props">
                <td class="text-xs-left">{{ props.item.fat }}</td>
                <td class="text-xs-left">{{ props.item.carbs }}</td>
                <td class=" text-xs-left layout px-0">
                    <v-icon
                            flat
                            class="mr-2"
                            @click="editItem(props.item)">
                        edit
                    </v-icon>
                    <v-icon
                            flat
                            @click="deleteItem(props.item)">
                        delete
                    </v-icon>
                </td>
            </template>
            <template v-slot:no-data>
                <v-btn color="primary" @click="initialize">Reset</v-btn>
            </template>
        </v-data-table>
        <AppCardEditCours></AppCardEditCours>
    </div>
</template>

<script>
    import AppCardEditCours from './AppCardEditCours.vue'
    export default {
        components:{AppCardEditCours},
        data: () => ({
        dialog: false,
        headers: [
            {
                text: 'Description',
                align: 'left',
                sortable: false,
                value: 'description'
            },

            { text: 'Titre de Leçon', value: 'carbs',sortable: false,
            },
            { text: 'Actions', value: 'name', sortable: false }
        ],
        desserts: [],
        editedIndex: -1,
        editedItem: {

            fat: 0,
            carbs: 0
        },
        defaultItem: {

            fat: 0,
            carbs: 0
        }
    }),

    computed: {
        formTitle () {
            return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
        }
    },

    watch: {
        dialog (val) {
            val || this.close()
        }
    },

    created () {
        this.initialize()
    },

    methods: {
        initialize () {
            this.desserts = [
                {

                    fat: 6.0,
                    carbs: 24
                },
                {

                    fat: 9.0,
                    carbs: 37
                },
                {

                    fat: 16.0,
                    carbs: 23
                }
            ]
        },

        editItem (item) {
            this.editedIndex = this.desserts.indexOf(item)
            this.editedItem = Object.assign({}, item)
            this.dialog = true
        },

        deleteItem (item) {
            const index = this.desserts.indexOf(item)
            confirm('Are you sure you want to delete this item?') && this.desserts.splice(index, 1)
        },

        close () {
            this.dialog = false
            setTimeout(() => {
                this.editedItem = Object.assign({}, this.defaultItem)
            this.editedIndex = -1
        }, 300)
        },

        save () {
            if (this.editedIndex > -1) {
                Object.assign(this.desserts[this.editedIndex], this.editedItem)
            } else {
                this.desserts.push(this.editedItem)
            }
            this.close()
        }
    }
    }
</script>
<style>
    .theme--light.v-table thead th{
        color:#01579B;
    }
    table.v-table thead th{
        font-size:14px;
        font-family: 'Roboto', sans-serif;
    }
</style>
