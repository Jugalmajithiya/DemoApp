package com.example.demoapplication.utils.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoapplication.data.network.common.Resource
import com.example.demoapplication.data.repo.UserRepository
import com.example.demoapplication.instanceApp
import com.example.demoapplication.utils.data.BaseResponse
import com.example.demoapplication.utils.setSuccess
import com.example.demoapplication.utils.showDialog
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel : ViewModel() {
    private var isFirst = true
    var baseError = MutableLiveData<Resource<String>>()
    val baseResponseLiveData: LiveData<Resource<String>> get() = baseError

    @CallSuper
    open fun loadPage(multipleTimes: Boolean = false): Boolean {
        if (isFirst) {
            isFirst = false
            return true
        }

        return isFirst || multipleTimes
    }
//    val coroutineExceptionHandler : CoroutineExceptionHandler = CoroutineExceptionHandler{ a, throwable ->
////      Toast.makeText(instanceApp, throwable.localizedMessage,Toast.LENGTH_SHORT).show()
////        println("Error>${ throwable.printStackTrace()}")
//        println("##Error>${ throwable.localizedMessage}")
////        instanceApp.show
////        baseError.setSuccess(throwable.localizedMessage)
//    }

    fun isInternetAvailable(): Boolean {
        var result = false
        val connectivityManager =
            instanceApp.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        }
        return result
    }


    protected fun <T : BaseResponse> checkSuccessError(
        it: T, liveEvent: SingleLiveEvent<Resource<T>>
    ) {
//        if (it.meta.status == SUCCESS)
//            liveEvent.setSuccess(it)
//        else
//            liveEvent.setError(Throwable(it.meta.message))
    }
}