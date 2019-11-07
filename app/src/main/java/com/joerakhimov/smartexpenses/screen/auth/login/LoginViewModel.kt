package com.joerakhimov.smartexpenses.screen.auth.login

import androidx.lifecycle.ViewModel
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepository
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProvider
import com.joerakhimov.smartexpenses.helper.mvvm.SingleLiveEvent
import com.joerakhimov.smartexpenses.screen.auth.login.model.LoginRequest
import com.joerakhimov.smartexpenses.screen.auth.register.AuthModel
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterRequest
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class LoginViewModel(private val model: AuthModel,
                     private val repository: SmartExpensesRepository,
                     private val schedulerProvider: SchedulerProvider): ViewModel() {

    internal val toastMessage = SingleLiveEvent<Any>()
    internal val loginSuccessEvent = SingleLiveEvent<Boolean>()

    fun onLoginButtonClick(email: String, password: String) {

        val isEmailValid = model.isEmailValid(email)
        if (!isEmailValid) {
            toastMessage.value = R.string.email_is_invalid
            return
        }

        val isPasswordValid = model.isPasswordValid(password)
        if (!isPasswordValid) {
            toastMessage.value = R.string.password_is_invalid
            return
        }

        val encryptedPassword = model.toMd5(password)

        val request = LoginRequest(email = email, password = encryptedPassword)
        repository.login(request)
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.ui)
            .subscribe({
                if (it.status == 0) loginSuccessEvent.value = true
                else toastMessage.value = it.message
            }, {
                toastMessage.value = it.message
            })

    }


}