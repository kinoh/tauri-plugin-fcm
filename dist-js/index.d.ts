import { type PluginListener } from '@tauri-apps/api/core';
/**
 * Listen to FCM messages data
 */
export declare function onPushNotificationOpened(handler: (payload: {
    data: Record<string, string>;
    sentAt: Date;
    openedAt: Date;
}) => void): Promise<PluginListener>;
/**
 * Get the latest FCM message data
 */
export declare function getLatestNotificationData(): Promise<{
    data: Record<string, string>;
    sentAt: Date;
    openedAt: Date;
}>;
/**
 * Get the FCM token
*/
export declare function getFCMToken(): Promise<string>;
/**
 * Subscribe to a topic
 */
export declare function subscribeToTopic(topic: string): Promise<void>;
