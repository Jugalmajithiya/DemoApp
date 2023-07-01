package com.example.demoapplication.ui.main

import android.view.View
import com.example.demoapplication.R
import com.example.demoapplication.databinding.ActivityMainBinding
import com.example.demoapplication.ui.ordersummary.OrderSummaryFragment
import com.example.demoapplication.utils.base.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun getContentResource(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onClickSafe(view: View) {

    }

    override fun initViews() {
        replaceFragment(R.id.mainContainer, OrderSummaryFragment(), true)


    }

}