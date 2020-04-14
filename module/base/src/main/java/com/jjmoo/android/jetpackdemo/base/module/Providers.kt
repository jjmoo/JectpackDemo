package com.jjmoo.android.jetpackdemo.base.module

import com.jjmoo.appjoint.AppJoint

object Providers {
    @JvmStatic
    val lock by lazy { AppJoint.service(Lock::class.java) ?: object : Lock {} }
}
