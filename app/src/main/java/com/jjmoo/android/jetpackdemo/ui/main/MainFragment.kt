package com.jjmoo.android.jetpackdemo.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.jjmoo.android.jetpackdemo.R
import com.jjmoo.android.jetpackdemo.base.module.Providers
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
        message.setOnClickListener {
            Intent().apply {
                action = "android.intent.action.VIEW"
                data = Uri.parse("http://www.baidu.com")
            }.run {
                Providers.lock.startActivity(activity!!, this)
            }
        }
    }
}
