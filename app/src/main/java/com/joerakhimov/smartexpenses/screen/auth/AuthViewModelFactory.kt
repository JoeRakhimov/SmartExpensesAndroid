package com.joerakhimov.smartexpenses.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepository
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProvider
import com.joerakhimov.smartexpenses.screen.auth.register.AuthModel
import com.joerakhimov.smartexpenses.screen.auth.register.RegisterViewModel

class AuthViewModelFactory(
    private val authModel: AuthModel,
    private val forecastRepository: SmartExpensesRepository,
    private val schedulerProvider: SchedulerProvider
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegisterViewModel(authModel, forecastRepository, schedulerProvider) as T
    }

}