package com.example.demoapplication.ui.home

import android.view.View
import android.view.ViewGroup
import com.example.demoapplication.R
import com.example.demoapplication.databinding.FragmentHomeBinding
import com.example.demoapplication.ui.home.adapter.DrugPrescribedAdapter
import com.example.demoapplication.ui.home.adapter.RecentlyViewedAdapter
import com.example.demoapplication.ui.home.adapter.TopListAdapter
import com.example.demoapplication.ui.ordersummary.OrderSummaryFragment
import com.example.demoapplication.utils.base.BaseFragment
import com.example.demoapplication.utils.replaceFragment


class HomeFragment : BaseFragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var topListAdapter: TopListAdapter
    lateinit var drugPrescribedAdapter: DrugPrescribedAdapter
    lateinit var recentlyViewedAdapter: RecentlyViewedAdapter

    override fun bind(container: ViewGroup?): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        topListAdapter= TopListAdapter(requireActivity()) {data,position->

        }
        drugPrescribedAdapter= DrugPrescribedAdapter(requireActivity()) {data,position->

        }
        recentlyViewedAdapter= RecentlyViewedAdapter(requireActivity()) {data,position->
            requireActivity().replaceFragment(R.id.nav_host_fragment,OrderSummaryFragment(),true)
        }
        val list = arrayListOf<String>()
        for (i in 0..10){
            list.add(i.toString())
        }
        topListAdapter.addAll(list)
        binding.rvTopList.adapter= topListAdapter

        drugPrescribedAdapter.addAll(list)
        binding.rvDrug.adapter= drugPrescribedAdapter

        recentlyViewedAdapter.addAll(list)
        binding.rvRecentlyViewed.adapter= recentlyViewedAdapter
    }

    override fun onClickSafe(view: View) {
    }


}