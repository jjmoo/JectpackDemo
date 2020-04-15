package com.jjmoo.android.lock

import com.jjmoo.android.jetpackdemo.base.ActivityScope
import com.jjmoo.android.jetpackdemo.base.AppComponent
import com.jjmoo.android.jetpackdemo.base.module.Lock
import com.jjmoo.appjoint.AppJoint
import dagger.Component
import dagger.Module
import dagger.Provides

/**
 * @author Zohn
 */

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [Providers::class])
interface LockComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): LockComponent
    }
    fun inject(activity: LockActivity)
    fun inject(validate: ValidateFragment)
}

@Module
class Providers {
    @Provides
    fun providerService(): LockService = AppJoint.service(Lock::class.java) as LockService
}