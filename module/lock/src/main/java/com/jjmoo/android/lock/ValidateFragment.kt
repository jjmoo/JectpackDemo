package com.jjmoo.android.lock

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.CycleInterpolator
import android.view.animation.TranslateAnimation
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.jjmoo.android.common.doAfter
import com.jjmoo.android.lock.databinding.FragmentValidateBinding
import javax.inject.Inject

/**
 * @author Zohn
 */
class ValidateFragment : Fragment() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by viewModels<ValidateViewModel> { factory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as LockActivity).lockComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentValidateBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@ValidateFragment
            viewModel = this@ValidateFragment.viewModel
            subscribeUi(this)
        }
        return binding.root
    }

    private fun subscribeUi(binding: FragmentValidateBinding) {
        viewModel.state.observe(viewLifecycleOwner) {
            if (it) {
                // TODO
            } else {
                binding.input.setDisplayMode(PasswordInput.MODE_WRONG)
                binding.state.run {
                    setTextColor(resources.getColor(android.R.color.holo_red_dark, null))
                    startAnimation(TranslateAnimation(-20f, 20f, 0f, 0f).apply {
                        interpolator = CycleInterpolator(3f)
                        duration = 300
                        doAfter {
                            binding.input.clearInput()
                            binding.input.setDisplayMode(PasswordInput.MODE_NORMAL)
                        }
                    })
                }
            }
        }
    }
}

class ValidateViewModel @Inject constructor(
    private val context: Application,
    private val lock: LockService
) : ViewModel(), PasswordInput.Listener {

    private val _state = MutableLiveData(false)
    private val _action = MutableLiveData("")

    val state: LiveData<Boolean> get() = _state
    val action: LiveData<String> get() = _action

    override fun onChange(password: String) {
        if (4 == password.length) {
            if (lock.check(password)) {
                _state.postValue(true)
                _action.postValue(context.getString(R.string.validate_password_correct))
            } else {
                _state.postValue(false)
                _action.postValue(context.getString(R.string.validate_password_wrong))
            }
        } else if (password.isNotEmpty()) {
            _action.postValue("")
        }
    }
}
