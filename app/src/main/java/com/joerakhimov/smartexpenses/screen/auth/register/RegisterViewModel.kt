package com.joerakhimov.smartexpenses.screen.auth.register

import androidx.lifecycle.ViewModel
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepository
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProvider

class RegisterViewModel(
    private val authModel: AuthModel,
    private val forecastRepository: SmartExpensesRepository,
    private val schedulerProvider: SchedulerProvider
): ViewModel() {

    fun onRegisterButtonClick(email: String, password: String, confirmPassword: String) {



    }

}