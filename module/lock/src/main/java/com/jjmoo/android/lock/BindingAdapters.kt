@file:Suppress("unused")

package com.jjmoo.android.lock

import androidx.databinding.BindingAdapter

/**
 * @author Zohn
 */
@BindingAdapter("listener")
fun bindListener(view: PasswordInputView, listener: PasswordInput.Listener) {
    view.setListener(listener)
}