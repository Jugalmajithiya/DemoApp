package com.example.demoapplication.ui.splash

import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.demoapplication.databinding.ActivitySplashBinding
import com.example.demoapplication.ui.DashBoardActivity
import com.example.demoapplication.utils.base.BaseActivity
import com.example.demoapplication.utils.launchActivity

class SplashDataActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun getContentResource(): View {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onClickSafe(view: View) {
    }

    override fun initViews() {
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            launchActivity<DashBoardActivity> {}
            finish()
        }, 3000)
    }

}