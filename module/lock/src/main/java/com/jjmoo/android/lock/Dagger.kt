package com.jjmoo.android.lock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jjmoo.android.jetpackdemo.base.*
import com.jjmoo.appjoint.AppJoint
import dagger.*
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

/**
 * @author Zohn
 */

@ActivityScope
@Component(
    dependencies = [AppComponent::class],
    modules = [Providers::class, ViewModelBuilderModule::class, Binders::class]
)
interface LockComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): LockComponent
    }

    fun inject(activity: LockActivity)
    fun inject(validate: ValidateFragment)
    fun inject(activity: LockTestActivity)
}

@Module
class Providers {
    @Provides
    fun providerService(): LockService = AppJoint.service(Lock::class.java) as LockService
}

@Module
abstract class ViewModelBuilderModule {
    @Binds
    @IntoMap
    @ViewModelKey(LocTestViewModel::class)
    abstract fun bindViewModel(viewModel: LocTestViewModel): ViewModel
}

@Module
abstract class Binders {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}