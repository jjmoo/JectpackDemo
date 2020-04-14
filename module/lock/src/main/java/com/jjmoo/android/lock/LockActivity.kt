package com.jjmoo.android.lock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jjmoo.android.lock.databinding.ActivityLockBinding

/**
 * @author Zohn
 */
class LockActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityLockBinding>(this, R.layout.activity_lock)
    }
}
