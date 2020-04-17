package com.jjmoo.android.lock

import android.app.Application
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jjmoo.android.common.CommonApplication
import com.jjmoo.android.jetpackdemo.base.BaseApplication
import com.jjmoo.android.jetpackdemo.base.Lock
import org.slf4j.LoggerFactory
import javax.inject.Inject

class LockTestActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel = viewModels<LocTestViewModel> { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerLockComponent.factory().create((application as BaseApplication).appComponent)
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock_test)

        LoggerFactory.getLogger("~~~~~").info("${viewModel.value.isInstalled()}")
    }
}

class LocTestViewModel @Inject constructor(private val lock: Lock, private val app: Application) :
    Lock by lock, ViewModel()
