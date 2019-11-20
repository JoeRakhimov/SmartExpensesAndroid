package com.joerakhimov.smartexpenses.base

import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.joerakhimov.smartexpenses.helper.mvvm.SingleLiveEvent

abstract class BaseViewModel: ViewModel() {

    var isLoading = ObservableBoolean(false)
    var isOpen = ObservableBoolean(true)

    internal val toastMessage = SingleLiveEvent<Any>()

}