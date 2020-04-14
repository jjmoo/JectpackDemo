package com.jjmoo.android.lock

import android.content.Context
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

    override fun startActivity(context: Context, intent: Intent) {
        super.startActivity(context, intent)
        logger.info("startActivity")
    }

    companion object {
        const val TAG = "LockProvider"
        fun getInstance() = Providers.lock as LockService
    }
}