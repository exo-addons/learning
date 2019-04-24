<template>
    <div id="app">
        <app-nav-main></app-nav-main>
        <router-view />
    </div>
</template>

<script>

    import AppNavMain from './components/AppNavMain.vue'



    export default {
        components:{AppNavMain},
        name: 'App',
        created: function () {
                console.log('new child created: ');
            }
    }
</script>


