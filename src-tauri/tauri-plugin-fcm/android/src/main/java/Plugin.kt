package com.plugin.fcm

import android.app.Activity
import app.tauri.annotation.Command
import app.tauri.annotation.InvokeArg
import app.tauri.annotation.TauriPlugin
import app.tauri.plugin.JSObject
import app.tauri.plugin.Plugin
import app.tauri.plugin.Invoke

import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.messaging.FirebaseMessaging;

@InvokeArg
class PingArgs {
  var value: String? = null
}

@TauriPlugin
class FCMPlugin(private val activity: Activity): Plugin(activity) {
    @Command
    fun ping(invoke: Invoke) {
        val args = invoke.parseArgs(PingArgs::class.java)

        val ret = JSObject()
        ret.put("value", "toto")
        invoke.resolve(ret)
    }
}
