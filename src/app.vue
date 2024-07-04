<script setup lang="ts">
import { ping } from 'tauri-plugin-fcm-api'

async function setupNotification() {
  const res = await ping('toto')
  console.log(res)
}
</script>

<template>
  <div>
    <button @click="setupNotification">setupNotification</button>
  </div>
</template>
