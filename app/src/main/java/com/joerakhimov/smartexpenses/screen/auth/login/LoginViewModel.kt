package com.joerakhimov.smartexpenses.screen.auth.login

import androidx.lifecycle.ViewModel
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepository
import com.joerakhimov.smartexpenses.di.Injector
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProvider
import com.joerakhimov.smartexpenses.helper.mvvm.SingleLiveEvent
import com.joerakhimov.smartexpenses.screen.auth.login.model.LoginRequest
import com.joerakhimov.smartexpenses.screen.auth.register.AuthModel
import javax.inject.Inject


class LoginViewModel: ViewModel() {

    @Inject
    lateinit var model: AuthModel

    @Inject
    lateinit var repository: SmartExpensesRepository

    @Inject
    lateinit var schedulerProvider: SchedulerProvider

    init {
        Injector.appComponent.inject(this)
    }

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

        val encryptedPassword = model.toSHA256(password)

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