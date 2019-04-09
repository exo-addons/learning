<template>
    <div class="course">
        <h1 class="subheading grey--text">course</h1>

        <v-container class="my-1">

            <v-layout>
                <v-flex md4 lg4 v-for="c in course" :key="c.name">
                    <v-card flat class="text-xs-center ma-1 elevation-10">
                        <v-card-text>
                            <div>
                                <v-img
                                        :src="c.src"
                                        aspect-ratio="1"
                                        class="grey lighten-2" />
                            </div>
                            <div class="subheading">{{ c.name }}</div>
                            <div class="grey--text">{{ c.categorie }}</div>
                        </v-card-text>
                        <v-card-actions>
                            <v-layout>
                                <v-flex md6 lg6>
                            <div class="text-xs-center">
                            <v-btn color="blue darken-3" fab dark small>
                                <v-icon dark>edit</v-icon>
                            </v-btn>
                            </div>
                                </v-flex>

                                <v-flex md6 lg6>
                                    <div class="text-xs-center">
                                        <v-btn color="grey" outline fab dark small>
                                            <v-icon dark>delete</v-icon>
                                        </v-btn>
                                    </div>
                                </v-flex>
                            </v-layout>
                        </v-card-actions>
                    </v-card>
                </v-flex>
            </v-layout>

        </v-container>

    </div>
</template>
<script>
    export default {
        data() {
            return {
                course: [
                    { src:'https://www.packtpub.com/sites/default/files/B05939.png',name: 'The Net Ninja', categorie: 'Web developer' },
                    { src:'https://www.packtpub.com/sites/default/files/B05939.png',name: 'Ryu', categorie: 'Graphic designer' },
                    { src:'yy',name: 'Chun Li', categorie: 'Web developer' },
                    { src:'https://www.packtpub.com/sites/default/files/B05939.png',name: 'Gouken', categorie: 'Social media ' },
                    { src:'https://www.packtpub.com/sites/default/files/B05939.png',name: 'Yoshi', categorie: 'Sales guru'}
                ]
            }
        }
    }
</script>
