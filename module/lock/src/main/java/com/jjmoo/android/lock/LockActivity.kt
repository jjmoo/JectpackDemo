package com.jjmoo.android.lock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jjmoo.android.jetpackdemo.base.BaseApplication
import com.jjmoo.android.lock.databinding.ActivityLockBinding

/**
 * @author Zohn
 */
class LockActivity : AppCompatActivity() {

    lateinit var lockComponent: LockComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        lockComponent = DaggerLockComponent.factory().create(
            (application as BaseApplication).appComponent)
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityLockBinding>(this, R.layout.activity_lock)
    }
}