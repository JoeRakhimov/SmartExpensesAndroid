package com.joerakhimov.smartexpenses.extension

import android.app.Activity
import android.content.Context
import android.content.Intent

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