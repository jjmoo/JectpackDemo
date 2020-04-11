package com.jjmoo.android.jetpackdemo.base

import android.content.Context
import android.content.Intent

/**
 * @author Zohn
 */
interface ModuleLock {
    fun startActivity(context: Context, intent: Intent): Boolean
}