package com.example.demoapplication.ui.confrimorder

import android.view.View
import android.view.ViewGroup
import com.example.demoapplication.R
import com.example.demoapplication.databinding.FragmentConfirmOrderBinding
import com.example.demoapplication.ui.ordersummary.ChoseAddressBottomSheetFragment
import com.example.demoapplication.ui.ordersummary.OrderSummaryFragment
import com.example.demoapplication.ui.ordersummary.adapter.OrderItemAdapter
import com.example.demoapplication.utils.base.BaseFragment
import com.example.demoapplication.utils.replaceFragment

class ConfirmOrderFragment : BaseFragment() {
    lateinit var binding: FragmentConfirmOrderBinding
    lateinit var orderItemAdapter: OrderItemAdapter

    override fun bind(container: ViewGroup?): View? {
        binding = FragmentConfirmOrderBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        binding.ivBack.setOnClickListener(this)
        binding.ivLocation.setOnClickListener(this)
        orderItemAdapter = OrderItemAdapter(requireActivity()) { data, position ->
            requireActivity().replaceFragment(R.id.nav_host_fragment, OrderSummaryFragment(), true)
        }
        val list = arrayListOf<String>()
        for (i in 0..2) {
            list.add(i.toString())
        }
        orderItemAdapter.addAll(list)
        binding.rvOrderItem.adapter= orderItemAdapter

    }

    override fun onClickSafe(view: View) {
        when (view.id) {
            R.id.ivBack->{
                requireActivity().onBackPressed()
            }
            R.id.ivLocation->{
                val bottomSheet = ChoseAddressBottomSheetFragment()
                bottomSheet.show(requireActivity().supportFragmentManager, "tag")
            }
        }
    }

}