package com.example.demoapplication.ui.invoice

import android.view.View
import android.view.ViewGroup
import com.example.demoapplication.databinding.FragmentInvoiceBinding
import com.example.demoapplication.utils.base.BaseFragment


class InvoiceFragment : BaseFragment() {
    lateinit var binding: FragmentInvoiceBinding

    override fun bind(container: ViewGroup?): View? {
        binding = FragmentInvoiceBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
    }

    override fun onClickSafe(view: View) {
    }
}