package com.joerakhimov.smartexpenses.screen.auth.register

import androidx.lifecycle.ViewModel
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepository
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProvider
import com.joerakhimov.smartexpenses.helper.mvvm.SingleLiveEvent
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterRequest
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterResponse

class RegisterViewModel(
    private val model: AuthModel,
    private val repository: SmartExpensesRepository,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    internal val toastMessage = SingleLiveEvent<Int>()

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
        if(!isPasswordValid){
            toastMessage.value = R.string.password_is_invalid
            return
        }

        val request = RegisterRequest(email, password)
        repository.register(request)
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.ui)
            .subscribe ({

            },{
                toastMessage.value = R.string.something_went_wrong
            })

    }


}