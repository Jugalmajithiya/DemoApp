package com.example.demoapplication.ui.order

import android.view.View
import android.view.ViewGroup
import com.example.demoapplication.databinding.FragmentOrderBinding
import com.example.demoapplication.utils.base.BaseFragment


class OrderFragment : BaseFragment() {
    lateinit var binding: FragmentOrderBinding

    override fun bind(container: ViewGroup?): View? {
        binding = FragmentOrderBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
    }

    override fun onClickSafe(view: View) {
    }
}