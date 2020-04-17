@file:Suppress("unused")

package com.jjmoo.android.jetpackdemo.base

import android.app.Application
import androidx.lifecycle.ViewModel
import com.jjmoo.appjoint.AppJoint
import com.jjmoo.appjoint.AppLike
import dagger.Component
import dagger.MapKey
import dagger.Module
import dagger.Provides
import javax.inject.Scope
import javax.inject.Singleton
import kotlin.reflect.KClass

/**
 * @author Zohn
 */

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope

@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Singleton
@Component(modules = [Providers::class])
interface AppComponent {
    val application: Application
    val lock: Lock
}

@Module
class Providers {
    @Singleton
    @Provides
    fun providerLock(): Lock = AppJoint.service(Lock::class.java) ?: object : Lock {}

    @Singleton
    @Provides
    fun providerApplication(): Application = AppLike.getInstance().context as Application
}