import { addPluginListener, invoke } from '@tauri-apps/api/core';

/**
 * Listen to FCM messages data
 */
async function onPushNotificationOpened(handler) {
    return await addPluginListener('fcm', 'pushNotificationOpened', ({ data, openedAt, sentAt }) => {
        handler({ data, sentAt: new Date(sentAt), openedAt: new Date(openedAt) });
    });
}
/**
 * Get the latest FCM message data
 */
async function getLatestNotificationData() {
    const result = await invoke('plugin:fcm|get_latest_notification_data', { payload: {} });
    if (result.data === null || result.sentAt === null || result.openedAt === null) {
        throw new Error('No notification data available');
    }
    return {
        data: result.data,
        sentAt: new Date(result.sentAt),
        openedAt: new Date(result.openedAt),
    };
}
/**
 * Get the FCM token
*/
async function getFCMToken() {
    return await invoke('plugin:fcm|get_token', { payload: {} });
}
/**
 * Subscribe to a topic
 */
async function subscribeToTopic(topic) {
    await invoke('plugin:fcm|subscribe_to_topic', {
        payload: {
            topic,
        },
    });
    return;
}

export { getFCMToken, getLatestNotificationData, onPushNotificationOpened, subscribeToTopic };
