package com.jjmoo.android.lock

/**
 * @author Zohn
 */
interface PasswordInput {
    fun setInputEnabled(enabled: Boolean)
    fun setDisplayMode(mode: Int)
    fun clearInput()

    interface Listener {
        fun onStart()
        fun onClear()
        fun onChange(password: String)
        fun onFinish(password: String)
    }

    companion object {
        const val MODE_WRONG = -1
        const val MODE_NORMAL = 0
    }
}