package com.example.demoapplication.ui.notification

import android.view.View
import android.view.ViewGroup
import com.example.demoapplication.databinding.FragmentNotificationBinding
import com.example.demoapplication.utils.base.BaseFragment


class NotificationFragment : BaseFragment() {
    lateinit var binding: FragmentNotificationBinding

    override fun bind(container: ViewGroup?): View? {
        binding = FragmentNotificationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
    }

    override fun onClickSafe(view: View) {
    }

}