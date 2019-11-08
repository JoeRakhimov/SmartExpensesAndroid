package com.joerakhimov.smartexpenses.screen.auth.register

import androidx.lifecycle.ViewModel
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepository
import com.joerakhimov.smartexpenses.di.Injector
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProvider
import com.joerakhimov.smartexpenses.helper.mvvm.SingleLiveEvent
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterRequest
import javax.inject.Inject

class RegisterViewModel: ViewModel() {

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
    internal val registrationSuccessEvent = SingleLiveEvent<Boolean>()

    fun onRegisterButtonClick(email: String, password: String, confirmPassword: String) {

        val isEmailValid = model.isEmailValid(email)
        if (!isEmailValid) {
            toastMessage.value = R.string.email_is_invalid
            return
        }

        val arePasswordsAreSame = model.arePasswordsAreSame(password, confirmPassword)
        if (!arePasswordsAreSame) {
            toastMessage.value = R.string.passwords_are_not_same
            return
        }

        val isPasswordValid = model.isPasswordValid(password)
        if (!isPasswordValid) {
            toastMessage.value = R.string.password_is_invalid
            return
        }

        val encryptedPassword = model.toMd5(password)

        val request = RegisterRequest(email = email, password = encryptedPassword)
        repository.register(request)
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.ui)
            .subscribe({
                if (it.status == 0) registrationSuccessEvent.value = true
                else toastMessage.value = it.message
            }, {
                toastMessage.value = it.message
            })

    }

}