package com.joerakhimov.smartexpenses.screen.auth.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepository
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProvider
import com.joerakhimov.smartexpenses.screen.auth.login.LoginViewModel
import com.joerakhimov.smartexpenses.screen.auth.register.AuthModel
import org.kodein.di.Kodein

class LoginViewModelFactory(
    private val authModel: AuthModel,
    private val repository: SmartExpensesRepository,
    private val schedulerProvider: SchedulerProvider) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(authModel, repository, schedulerProvider) as T
    }

}