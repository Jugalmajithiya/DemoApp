package com.example.demoapplication.ui.ordersummary

import android.view.View
import android.view.ViewGroup
import com.example.demoapplication.R
import com.example.demoapplication.databinding.FragmentOrderSummaryBinding
import com.example.demoapplication.ui.confrimorder.ConfirmOrderFragment
import com.example.demoapplication.ui.home.adapter.RecentlyViewedAdapter
import com.example.demoapplication.ui.ordersummary.adapter.OrderItemAdapter
import com.example.demoapplication.utils.base.BaseFragment
import com.example.demoapplication.utils.replaceFragment

class OrderSummaryFragment : BaseFragment() {
    lateinit var binding: FragmentOrderSummaryBinding
    lateinit var orderItemAdapter: OrderItemAdapter

    override fun bind(container: ViewGroup?): View? {
        binding = FragmentOrderSummaryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        binding.btnProcessPrescription.setOnClickListener(this)
        orderItemAdapter = OrderItemAdapter(requireActivity()) { data, position ->
            requireActivity().replaceFragment(R.id.nav_host_fragment, OrderSummaryFragment(), true)
        }
        val list = arrayListOf<String>()
        for (i in 0..2) {
            list.add(i.toString())
        }
        orderItemAdapter.addAll(list)
        binding.rvOrderItem.adapter = orderItemAdapter

    }

    override fun onClickSafe(view: View) {
        when (view.id) {
            R.id.btnProcessPrescription -> {
                requireActivity().replaceFragment(R.id.nav_host_fragment,ConfirmOrderFragment())
            }
        }
    }

}