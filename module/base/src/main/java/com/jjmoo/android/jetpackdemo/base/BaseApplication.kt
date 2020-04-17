package com.jjmoo.android.jetpackdemo.base

import android.app.Application
import com.jjmoo.andlogger.LogUtils
import com.jjmoo.appjoint.annotation.AppSpec

@Suppress("unused", "MemberVisibilityCanBePrivate")
@AppSpec
open class BaseApplication : Application() {

    open val appComponent: AppComponent = DaggerAppComponent.create()

    override fun onCreate() {
        LogUtils.prefix = "JetpackDemo/"
        super.onCreate()
    }
}