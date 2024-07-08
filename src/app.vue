<script setup lang="ts">
import { requestPermission, isPermissionGranted, sendNotification } from '@tauri-apps/plugin-notification'
import { subscribeToTopic , getFCMToken, onPushNotificationOpened, getLatestNotificationData } from 'tauri-plugin-fcm-api'
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

const notificationData = ref()

onMounted(async () => {
  const perm = await isPermissionGranted()
  if (!perm) {
    await requestPermission()
  }

  notificationData.value = await onPushNotificationOpened((d) => {
    console.log('Notification data', d)
  })
})

onUnmounted(() => {
  notificationData.value?.()
})

</script>

<template>
  <div>
    <button @click="setupNotification">setupNotification</button>
    <button @click="testNotification">testNotification</button>
    <button @click="() => {
      getLatestNotificationData().then((data) => {
        console.log('Latest notification data', data)
      }).catch((e) => {
        console.error(e)
      })
    }">
      getLatestNotificationData  
  </button>
  </div>
</template>
