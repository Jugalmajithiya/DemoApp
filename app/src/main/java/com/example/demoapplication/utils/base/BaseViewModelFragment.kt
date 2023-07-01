package com.example.demoapplication.utils.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import com.example.demoapplication.data.network.common.Resource

import com.example.demoapplication.data.network.common.ProgressDialogUtil
import com.example.demoapplication.utils.Status
import com.example.demoapplication.utils.hideKeyboard

abstract class BaseViewModelFragment<T : BaseViewModel> : BaseFragment(), View.OnClickListener {

    protected val viewModel by lazy { buildViewModel() }

    protected abstract fun buildViewModel(): T

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initLiveDataObservers()
        viewModel.loadPage(isMultipleLoad())
    }

    @CallSuper
    protected open fun initLiveDataObservers() {
    }

    protected open fun isMultipleLoad(): Boolean = false

    override fun onClickSafe(view: View) {
    }

    protected fun showProgressDialog() {
        view?.hideKeyboard()
        context?.let(ProgressDialogUtil::showProgressDialog)
    }

    protected fun hideProgressDialog() {
        ProgressDialogUtil.hideProgressDialog()
    }

        protected fun <T> handleLiveEvent(
            response: Resource<T>, isShowProgressDialog: Boolean = true,
            isHideProgressDialog: Boolean = true, success: (T?) -> Unit
    ) {
        when (response.status) {
            Status.LOADING -> {
                if (isShowProgressDialog) {
                    hideProgressDialog()
                    showProgressDialog()
                }
            }
            Status.ERROR -> handleError(response)
            Status.SUCCESS -> {
                success.invoke(response.item)
                if (isHideProgressDialog)
                    hideProgressDialog()
            }
        }
    }

    private fun <T> handleError(response: Resource<T>) {
        hideProgressDialog()
        response.throwable?.let {
            when (it) {
//                is ConnectException -> activity?.showAlertDialog(getString(R.string.no_internet))
//                else -> activity?.showAlertDialog(
////                    message = it.message ?: getString(R.string.something_went_wrong)
//                )
            }
        }
    }
}
