package com.joerakhimov.smartexpenses.screen.auth.register

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepository
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProvider
import com.joerakhimov.smartexpenses.screen.auth.login.LoginViewModel

class RegisterViewModelFactory(
    private val authModel: AuthModel,
    private val repository: SmartExpensesRepository,
    private val schedulerProvider: SchedulerProvider
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegisterViewModel(authModel, repository, schedulerProvider) as T
    }

}