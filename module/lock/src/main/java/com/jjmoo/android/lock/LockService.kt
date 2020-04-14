package com.jjmoo.android.lock

import android.app.Activity
import android.content.Intent
import com.jjmoo.android.jetpackdemo.base.module.Lock
import com.jjmoo.android.jetpackdemo.base.module.Providers
import com.jjmoo.appjoint.annotation.ServiceProvider
import org.slf4j.LoggerFactory

/**
 * @author Zohn
 */
@Suppress("unused")
@ServiceProvider
class LockService : Lock {
    private val logger by lazy { LoggerFactory.getLogger(TAG) }

    override fun startSettings(caller: Activity) {
        logger.info("startSettings")
        caller.startActivity(Intent(caller, LockActivity::class.java))
    }

    override fun startActivity(caller: Activity, intent: Intent) {
        logger.info("startActivity")
        caller.startActivity(Intent(caller, LockActivity::class.java))
    }

    companion object {
        const val TAG = "LockProvider"
        fun getInstance() = Providers.lock as LockService
    }
}