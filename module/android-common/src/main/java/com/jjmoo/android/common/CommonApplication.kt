package com.jjmoo.android.common

import android.app.Application
import android.os.StrictMode

@Suppress("unused", "MemberVisibilityCanBePrivate")
open class CommonApplication : Application() {
    val commonComponent: CommonComponent = DaggerCommonComponent.create()

    fun setStrictModeEnabled() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder().detectAll().permitDiskReads().penaltyDeath().build())
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder().detectAll().penaltyDeath().build())
    }
}