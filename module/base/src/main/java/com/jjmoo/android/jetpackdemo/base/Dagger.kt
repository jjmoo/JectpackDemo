@file:Suppress("unused")

package com.jjmoo.android.jetpackdemo.base

import com.jjmoo.android.jetpackdemo.base.module.Lock
import com.jjmoo.appjoint.AppJoint
import com.jjmoo.appjoint.AppLike
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope
import javax.inject.Singleton

/**
 * @author Zohn
 */

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope

@Singleton
@Component(modules = [Providers::class])
interface AppComponent {
    fun application(): BaseApplication
    fun lock(): Lock
}

@Module
class Providers {
    @Singleton
    @Provides
    fun providerLock(): Lock = AppJoint.service(Lock::class.java) ?: object : Lock {}
    @Singleton
    @Provides
    fun providerApplication(): BaseApplication = AppLike.getInstance().context as BaseApplication
}