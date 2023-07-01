package com.example.demoapplication.ui

import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.demoapplication.R
import com.example.demoapplication.databinding.ActivityDashBoardBinding
import com.example.demoapplication.ui.home.HomeFragment
import com.example.demoapplication.utils.base.BaseActivity

class DashBoardActivity : BaseActivity() {
    private lateinit var binding: ActivityDashBoardBinding

    override fun getContentResource(): View {
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onClickSafe(view: View) {
    }

    override fun initViews() {
        binding.bottomNavigationView.setupWithNavController(
            Navigation.findNavController(
                this,
                R.id.nav_host_fragment
            )
        )
    }
    override fun onBackPressed() {
            val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            if (fragment is HomeFragment) {
                doubleTapToExit()
            } else {
                super.onBackPressed()
            }

    }
}