package com.jjmoo.android.jetpackdemo

import android.app.Application
import com.jjmoo.android.jetpackdemo.base.DaggerAppComponent
import dagger.internal.DaggerCollections

/**
 * @author Zohn
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}