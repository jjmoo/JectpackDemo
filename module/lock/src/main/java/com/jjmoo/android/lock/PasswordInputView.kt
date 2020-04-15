package com.jjmoo.android.lock

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import com.jjmoo.android.lock.databinding.MockInputBinding

/**
 * @author Zohn
 */
class PasswordInputView constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : LinearLayout(
    context,
    attrs,
    defStyleAttr,
    defStyleRes
), PasswordInput {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    ) {
        binding = MockInputBinding.inflate(LayoutInflater.from(context), this, true)
        binding.editText.setTextColor(context.getColor(android.R.color.holo_green_dark))
        binding.editText.addTextChangedListener {
            listener?.onChange(it?.toString() ?: "")
        }
    }

    private lateinit var binding: MockInputBinding
    private var listener: PasswordInput.Listener? = null

    override fun setListener(listener: PasswordInput.Listener) {
        this.listener = listener
    }

    override fun setInputEnabled(enabled: Boolean) {
        binding.editText.isEnabled = enabled
    }

    override fun setDisplayMode(mode: Int) {
        if (PasswordInput.MODE_WRONG == mode) {
            binding.editText.setTextColor(context.getColor(android.R.color.holo_red_light))
        } else {
            binding.editText.setTextColor(context.getColor(android.R.color.holo_green_dark))
        }
    }

    override fun clearInput() {
        binding.editText.setText("")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        binding.editText.requestFocus()
        if (context is Activity) {
            (context as Activity).window
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
            (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .showSoftInput(binding.editText, 0)
        }
    }
}