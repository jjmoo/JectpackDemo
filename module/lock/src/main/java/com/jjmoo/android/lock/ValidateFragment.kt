package com.jjmoo.android.lock

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjmoo.android.lock.databinding.FragmentValidateBinding
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * @author Zohn
 */
class ValidateFragment : Fragment() {

    private val logger by lazy { LoggerFactory.getLogger("ValidateFragment") }
    @Inject lateinit var service: LockService

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as LockActivity).lockComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentValidateBinding.inflate(inflater, container, false)
        binding.input.setListener {
            if (4 == it.length) {
                if (service.check(it)) {
                    activity!!.run {
                        val intent = intent.getParcelableExtra<Intent>(LockService.KEY_INTENT)
                        if (null != intent) {
                            finish()
                            startActivity(intent)
                        } else {
                            // TODO
                            logger.warn("??????")
                        }
                    }
                } else {
                    binding.input.setDisplayMode(PasswordInput.MODE_WRONG)
                }
            }
        }
        return binding.root
    }
}
