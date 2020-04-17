package com.jjmoo.android.jetpackdemo.base

import com.jjmoo.android.common.CommonApplication
import com.jjmoo.appjoint.annotation.AppSpec

@AppSpec
open class BaseApplication: CommonApplication() {
    val appComponent: AppComponent = DaggerAppComponent.factory().create(commonComponent)
}