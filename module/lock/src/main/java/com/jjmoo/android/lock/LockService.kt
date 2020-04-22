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

    private var enabled: MutableLiveData<Boolean>
    private var type: MutableLiveData<Lock.Type>
    private var password: String
    private var callback: (() -> Unit)? = null

    init {
        context.getSharedPreferences(TAG, Context.MODE_PRIVATE).run {
            enabled = MutableLiveData(getBoolean(KEY_ENABLED, false))
            type = MutableLiveData(Lock.Type.values()[getInt(KEY_TYPE, Lock.Type.UNDEFINED.ordinal)])
            password = getString(KEY_PASSWORD, "")!!
        }
        set(Lock.Type.PIN, "8868")
    }

    override fun isInstalled() = true

    override fun getType() = type

    override fun isEnabled() = enabled

    override fun setEnabled(enabled: Boolean, caller: Activity?) {
        if (this.enabled.value != enabled) {
            validate {
                this.enabled.postValue(enabled)
            }
        }
    }

    override fun configure(caller: Activity?) {
        startActivity(caller, Intent(caller, LockActivity::class.java)
            .putExtra(INTENT_CONFIGURE, true))
    }

    override fun validate(caller: Activity?, callback: () -> Unit) {
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

    suspend fun pass() {
        withContext(Dispatchers.Main) {
           callback?.invoke()
        }
    }

    fun check(input: String) = password == md5(input)

    fun set(type: Lock.Type, input: String) {
        this.type.postValue(type)
        password = md5(input)
        save()
    }

    private fun save() {
        context.getSharedPreferences(TAG, Context.MODE_PRIVATE).edit {
            putInt(KEY_TYPE, type.value!!.ordinal)
            putString(KEY_PASSWORD, password)
        }
    }

    private fun md5(string: String): String {
        if (!TextUtils.isEmpty(string)) {
            val hex = StringBuilder()
            for (b in MessageDigest.getInstance("MD5").digest(string.toByteArray())) {
                if ((0xFF and b.toInt()) < 0x10) {
                    hex.append("0")
                }
                hex.append(Integer.toHexString(0xFF and b.toInt()))
            }
            return hex.substring(8, 24)
        }
        return ""
    }

    companion object {
        const val TAG = "LockProvider"
        private const val KEY_ENABLED = "lock_enabled"
        private const val KEY_TYPE = "lock_type"
        private const val KEY_PASSWORD = "lock_password"
        private const val INTENT_CONFIGURE = "intent_configure"
    }
}