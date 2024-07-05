import { invoke } from '@tauri-apps/api/core'

/**
 * Get the FCM token
*/
export async function getFCMToken(): Promise<string> {
  return await invoke('plugin:fcm|get_token', { payload: {}});
}

/**
 * Subscribe to a topic
 */
export async function subscribeToTopic(topic: string): Promise<void> {
  await invoke('plugin:fcm|subscribe_to_topic', {
    payload: {
      topic,
    },
  });
  return
}