package com.jjmoo.android.jetpackdemo.base.module

import android.content.Context
import android.content.Intent

@Suppress("unused")
interface Lock {
    companion object {
        const val TYPE_DISABLED = 0
        const val TYPE_PATTERN = 1
        const val TYPE_PIN = 2
    }
    fun isEnabled() = TYPE_DISABLED == getType()
    fun getType() = TYPE_DISABLED
    fun startActivity(context: Context, intent: Intent) = context.startActivity(intent)
}
