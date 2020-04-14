package com.jjmoo.android.lock

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjmoo.android.lock.databinding.FragmentValidateBinding

/**
 * @author Zohn
 */
class ValidateFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentValidateBinding.inflate(inflater, container, false).root
    }
}
