<script setup lang="ts">
import { requestPermission, isPermissionGranted, sendNotification } from '@tauri-apps/plugin-notification'
import { subscribeToTopic , getFCMToken } from 'tauri-plugin-fcm-api'
async function setupNotification() {
  console.log('Notification setup before')
  const token = await getFCMToken()
  console.log(token)
  const res = await subscribeToTopic('all')
  console.log(res)
  console.log('Notification setup')
}

async function testNotification() {
  sendNotification({ title: 'TAURI', body: 'Tauri is awesome!' });
}

onMounted(async () => {
  const perm = await isPermissionGranted()
  if (!perm) {
    await requestPermission()
  }
  console.log('Notification setup before')
})
</script>

<template>
  <div>
    <button @click="setupNotification">setupNotification</button>
    <button @click="testNotification">testNotification</button>
  </div>
</template>
