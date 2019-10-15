package com.joerakhimov.smartexpenses.screen.launcher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LauncherViewModelFactory() : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LauncherViewModel() as T
    }

}