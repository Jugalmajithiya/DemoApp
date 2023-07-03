package com.example.demoapplication.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.location.LocationManager
import android.net.Uri
import android.os.Looper
import android.provider.OpenableColumns
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.demoapplication.R
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import java.text.DateFormat
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()

        }
        val snackbarView = snackbar.view
        val tv =
            snackbarView.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
        tv.maxLines = 3
        snackbar.show()
    }.show()
}

fun View.showMessage(message: Any) {
    val stMsg = when (message) {
        is String -> message.toString()
        is Int -> resources.getString(message)
        else -> ""
    }

    findViewById<View>(android.R.id.content).apply {
        snackbar(stMsg)
    }
}




fun checkStringValue(value: String?, blank: String = ""): String {
    if (value.isNullOrEmpty()) {
        return blank
    }
    return value
}




fun getDeviceId(mContext: Context): String {
    return Settings.Secure.getString(
        mContext.contentResolver,
        Settings.Secure.ANDROID_ID
    )
}