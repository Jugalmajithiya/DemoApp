package com.example.demoapplication.data.network.common

import android.app.Activity
import android.content.Intent
import com.example.demoapplication.utils.base.NoInternetActivity

object CommonMethod {

    fun checkNetworkConnection(activity: Activity, isConnectionOn: Boolean) {
        if (!isConnectionOn) {
            activity.startActivity(Intent(activity, NoInternetActivity::class.java))
        } else {
            if (activity is NoInternetActivity) activity.finish()
        }
    }
}