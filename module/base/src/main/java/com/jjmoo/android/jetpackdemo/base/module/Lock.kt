package com.jjmoo.android.jetpackdemo.base.module

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

@Suppress("unused")
interface Lock {
    enum class Type { UNDEFINED, PATTERN, PIN }

    fun isInstalled() = false

    fun getType(): LiveData<Type> = MutableLiveData<Type>(Type.UNDEFINED)

    fun isEnabled(): LiveData<Boolean> = MutableLiveData<Boolean>(false)

    fun setEnabled(enable: Boolean, caller: Activity? = null) {}

    fun validate(caller: Activity? = null, callback: () -> Unit) {
        callback.invoke()
    }
}
