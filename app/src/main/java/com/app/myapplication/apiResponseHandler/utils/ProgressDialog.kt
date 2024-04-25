package com.app.myapplication.apiResponseHandler.utils

import android.app.Dialog
import android.content.Context
import com.app.myapplication.R

object ProgressDialog {

    private var progressDialog: Dialog? = null

    /**
     *  show progress
     *  @param context
     * */
    @Synchronized
    fun showProgress(context: Context) {
        try {

            if (!isShowProgress()) {
                progressDialog = Dialog(context, R.style.progressBarDialog)
                progressDialog?.setContentView(R.layout.fragment_my_progress)
                progressDialog?.setCanceledOnTouchOutside(false)
                progressDialog?.setCancelable(false)
                progressDialog?.show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * hide progress
     * */
    @Synchronized
    fun hideProgress() {
        try {
            if (progressDialog != null && progressDialog?.isShowing == true) {
                progressDialog?.dismiss()
            }
        } catch (i: IllegalArgumentException) {
            i.printStackTrace()
        }
    }

    /**
     *  check progress bar is showing
     * */
    private fun isShowProgress(): Boolean {
        return progressDialog != null && progressDialog?.isShowing == true
    }
}