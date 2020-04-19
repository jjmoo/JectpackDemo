package com.jjmoo.android.jetpackdemo.base

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

@Suppress("unused")
interface Lock {
    enum class Type { UNDEFINED, PATTERN, PIN }

    fun isInstalled() = false

    fun getType(): LiveData<Type> = MutableLiveData(Type.UNDEFINED)

    fun isEnabled(): LiveData<Boolean> = MutableLiveData(false)

    fun setEnabled(enabled: Boolean, caller: Activity? = null) {}

    fun configure(caller: Activity? = null) {}

    fun validate(caller: Activity? = null, callback: () -> Unit) {
        callback.invoke()
    }
}
