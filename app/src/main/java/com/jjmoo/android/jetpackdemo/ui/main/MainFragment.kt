package com.jjmoo.android.jetpackdemo.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jjmoo.android.jetpackdemo.MyApplication
import com.jjmoo.android.jetpackdemo.databinding.MainFragmentBinding
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {
    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return MainFragmentBinding.inflate(inflater, container, false).root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        message.setOnClickListener {
            Intent().apply {
                action = "android.intent.action.VIEW"
                data = Uri.parse("http://www.baidu.com")
            }.run {
//                (activity!!.application as MyApplication).appComponent.lock()
//                    .startActivity(activity!!, this)
            }
        }
    }
}