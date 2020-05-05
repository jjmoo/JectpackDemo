package com.jjmoo.android.lock

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import com.jjmoo.android.jetpackdemo.base.Lock
import com.jjmoo.appjoint.annotation.ServiceProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import java.security.MessageDigest

/**
 * @author Zohn
 */
@Suppress("unused")
@ServiceProvider
class LockService(private val context: Application) : Lock {

    private val logger by lazy { LoggerFactory.getLogger(TAG) }

    private val enabled: MutableLiveData<Boolean> = MutableLiveData(false)
    private val type: MutableLiveData<Lock.Type> = MutableLiveData(
        Lock.Type.values()[Lock.Type.UNDEFINED.ordinal]
    )
    private var password: String = ""
    private var callback: (() -> Unit)? = null

    init {
        GlobalScope.launch(Dispatchers.IO) {
            context.getSharedPreferences(TAG, Context.MODE_PRIVATE).run {
                password = getString(KEY_PASSWORD, "")!!
                enabled.postValue(getBoolean(KEY_ENABLED, false))
                type.postValue(Lock.Type.values()[getInt(KEY_TYPE, Lock.Type.UNDEFINED.ordinal)])
            }
            set(Lock.Type.PIN, "8868")
        }
    }

    override fun isInstalled() = true

    override fun getType() = type

    override fun isEnabled() = enabled

    override fun setEnabled(enabled: Boolean, caller: Activity?) {
        logger.info("setEnabled: enabled = [{}], caller = [{}]", enabled, caller)
        if (this.enabled.value != enabled) {
            if (enabled && Lock.Type.UNDEFINED == type.value) {
                configure(caller)
            } else {
                validate(caller) {
                    this.enabled.postValue(enabled)
                }
            }
        }
    }

    override fun configure(caller: Activity?) {
        logger.info("configure: caller = [{}]", caller)
        startActivity(
            caller,
            Intent(caller, LockActivity::class.java).putExtra(INTENT_CONFIGURE, true)
        )
    }

    override fun validate(caller: Activity?, callback: () -> Unit) {
        logger.info("validate: caller = [{}]", caller)
        if (!enabled.value!!) {
            callback.invoke()
        } else {
            this.callback = callback
            startActivity(caller, Intent(caller, LockActivity::class.java))
        }
    }

    private fun startActivity(caller: Activity?, intent: Intent) {
        if (caller is Activity) {
            caller.startActivity(intent)
        } else {
            context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }

    fun check(input: String) = password == md5(input)

    suspend fun pass() = withContext(Dispatchers.Main) {
        callback?.invoke()
        callback = null
    }

    suspend fun set(type: Lock.Type, input: String) = withContext(Dispatchers.IO) {
        this@LockService.type.postValue(type)
        password = md5(input)
        context.getSharedPreferences(TAG, Context.MODE_PRIVATE).edit {
            putInt(KEY_TYPE, this@LockService.type.value!!.ordinal)
            putString(KEY_PASSWORD, password)
        }
    }

    private fun md5(string: String): String = if (!TextUtils.isEmpty(string)) {
        val hex = StringBuilder()
        for (b in MessageDigest.getInstance("MD5").digest(string.toByteArray())) {
            if ((0xFF and b.toInt()) < 0x10) hex.append("0")
            hex.append(Integer.toHexString(0xFF and b.toInt()))
        }
        hex.substring(8, 24)
    } else ""

    companion object {
        const val TAG = "LockProvider"
        const val INTENT_CONFIGURE = "intent_configure"
        private const val KEY_ENABLED = "lock_enabled"
        private const val KEY_TYPE = "lock_type"
        private const val KEY_PASSWORD = "lock_password"
    }
}