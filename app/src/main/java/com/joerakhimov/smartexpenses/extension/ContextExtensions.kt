package com.joerakhimov.smartexpenses.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri

inline fun <reified T : Activity> Context.newIntent(): Intent =
    Intent(this, T::class.java)

inline fun <reified T : Activity> Context.startActivityNewTask() {
    var intent = newIntent<T>()
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    this.startActivity(intent)
}

inline fun <reified T : Activity> Context.startClearActivity() {
    var intent = newIntent<T>()
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    this.startActivity(intent)
}

inline fun Context.startActivityToOpenUrlInBrowser(url: String?) {
    val browserIntent = newIntentToOpenUrlInBrowser(url)
    if (browserIntent == null) return
    browserIntent.addCategory(Intent.CATEGORY_BROWSABLE);
    try {
        startActivity(browserIntent)
    } catch (e: Exception) {
    }
}

fun newIntentToOpenUrlInBrowser(url: String?): Intent? {
    if (url == null || url!!.isEmpty()) return null
    var fullUrl = url
    if (!url.startsWith("http://") && !url.startsWith("https://"))
        fullUrl = "http://$url"
    return Intent(Intent.ACTION_VIEW, Uri.parse(fullUrl));
}