package com.jjmoo.android.jetpackdemo.base.module

import android.app.Activity
import android.content.Intent

@Suppress("unused")
interface Lock {
    enum class Type { DISABLED, PATTERN, PIN }

    fun isInstalled() = false
    fun isEnabled() = Type.DISABLED == getType()
    fun getType() = Type.DISABLED
    fun startSettings(caller: Activity) {}
    fun startActivity(caller: Activity, intent: Intent) = caller.startActivity(intent)
}
