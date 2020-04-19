package com.jjmoo.android.lock

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jjmoo.android.jetpackdemo.base.BaseApplication
import com.jjmoo.android.jetpackdemo.base.Lock
import com.jjmoo.android.lock.databinding.ActivityLockTestBinding
import javax.inject.Inject

class LockTestActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModelLazy = viewModels<LockTestViewModel> { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerLockComponent.factory().create((application as BaseApplication).appComponent)
            .inject(this)
        super.onCreate(savedInstanceState)
        val viewModel = viewModelLazy.value
        DataBindingUtil.setContentView<ActivityLockTestBinding>(
            this, R.layout.activity_lock_test
        ).apply {
            lifecycleOwner = this@LockTestActivity
            this.viewModel = viewModel
            setting.setOnClickListener {
                viewModel.configure(this@LockTestActivity)
            }
            enabled.setOnClickListener {
                viewModel.setEnabled(!viewModel.isEnabled().value!!, this@LockTestActivity)
            }
        }
    }
}

class LockTestViewModel @Inject constructor(
    private val lock: LockService
) : Lock by lock, ViewModel()
