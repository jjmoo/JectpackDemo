package com.jjmoo.android.lock

/**
 * @author Zohn
 */
@Suppress("unused")
interface PasswordInput {

    fun setInputEnabled(enabled: Boolean)
    fun setDisplayMode(mode: Int)
    fun clearInput()
    fun setListener(listener: Listener)

    fun setListener(change: (String) -> Unit) {
        setListener(object : Listener {
            override fun onChange(password: String) {
                change.invoke(password)
            }
        })
    }

    interface Listener {
        fun onStart() {}
        fun onClear() {}
        fun onChange(password: String)
        fun onFinish(password: String) {}
    }

    companion object {
        const val MODE_WRONG = -1
        const val MODE_NORMAL = 0
    }
}