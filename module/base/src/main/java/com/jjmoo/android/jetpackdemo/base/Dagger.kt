package com.jjmoo.android.jetpackdemo.base

import android.app.Application
import com.jjmoo.android.common.ApplicationScope
import com.jjmoo.android.common.CommonComponent
import com.jjmoo.appjoint.AppJoint
import dagger.Component
import dagger.Module
import dagger.Provides

@ApplicationScope
@Component(
    dependencies = [CommonComponent::class],
    modules = [Providers::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: CommonComponent): AppComponent
    }
    val application: Application
    val lock: Lock
}

@Module
class Providers {
    @ApplicationScope
    @Provides
    fun providerLock(): Lock = AppJoint.service(Lock::class.java) ?: object : Lock {}
}