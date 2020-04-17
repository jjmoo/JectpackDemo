package com.jjmoo.android.common

import android.app.Application

@Suppress("unused", "MemberVisibilityCanBePrivate")
open class CommonApplication : Application() {
    val commonComponent: CommonComponent = DaggerCommonComponent.create()
}