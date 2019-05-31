<template>
  <div id="notifications" v-model="alerte">
    <div v-for="notification in notifications" :class="getNotificationClass(notification)">
      <i class="fas fa-times" @click="cancel">
      </i>
      {{ notification.message }}
    </div>
  </div>
</template>

<script>
    export default{
        props: ['notifications'],
        data(){
            return{
            }
        },
        methods: {
            getNotificationClass: function (notification) {
                return `alert alert-${  notification.type}`;
            },
            cancel() {
                this.state.$remove(notification)

            }
        }
    }
</script>
