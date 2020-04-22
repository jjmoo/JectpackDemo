package com.jjmoo.android.jetpackdemo

import com.jjmoo.android.jetpackdemo.base.BaseApplication

/**
 * @author Zohn
 */
class MyApplication : BaseApplication() {
    override fun onCreate() {
        if (BuildConfig.DEBUG) setStrictModeEnabled()
        super.onCreate()
    }
}