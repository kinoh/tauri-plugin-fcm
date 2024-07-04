<script setup lang="ts">
// import { ping } from 'tauri-plugin-fcm-api'
console.log('app.vue')
async function setupNotification() {
  console.log('pinging')
  // const res = await ping('toto')
  // console.log(res)
}
</script>

<template>
  <div>
    <button @click="setupNotification">setupNotification</button>
    <button @click="() => {
      console.log('test stdout')
    }">test stdout</button>
  </div>
</template>
