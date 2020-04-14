package com.jjmoo.android.lock

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener

class MockInput(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int
) : AppCompatEditText(
    context,
    attrs,
    defStyleAttr
), PasswordInput {
    @Suppress("MemberVisibilityCanBePrivate")
    var listener: PasswordInput.Listener? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.attr.editTextStyle
    ) {
        setTextColor(context.getColor(android.R.color.holo_green_dark))
        addTextChangedListener {
            listener?.onChange(it?.toString() ?: "")
        }
    }

    override fun setInputEnabled(enabled: Boolean) {
        isEnabled = enabled
    }

    override fun setDisplayMode(mode: Int) {
        if (PasswordInput.MODE_WRONG == mode) {
            setTextColor(context.getColor(android.R.color.holo_red_light))
        } else {
            setTextColor(context.getColor(android.R.color.holo_green_dark))
        }
    }

    override fun clearInput() {
        setText("")
    }
}