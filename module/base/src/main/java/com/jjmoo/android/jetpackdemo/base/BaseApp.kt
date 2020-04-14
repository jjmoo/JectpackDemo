@file:Suppress("RemoveEmptyClassBody")

package com.jjmoo.android.jetpackdemo.base

import android.app.Application
import com.jjmoo.andlogger.LogUtils
import com.jjmoo.appjoint.annotation.AppSpec
import dagger.Component

@Suppress("unused")
@AppSpec
open class BaseApp : Application() {
    val appComponent: BaseComponent = DaggerBaseComponent.create()

    override fun onCreate() {
        LogUtils.prefix = "JetpackDemo/"
        super.onCreate()
    }
}

@Component
interface BaseComponent {}
