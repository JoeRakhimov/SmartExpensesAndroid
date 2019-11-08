package com.joerakhimov.smartexpenses.screen.auth.welcome

import androidx.lifecycle.ViewModel
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepository
import com.joerakhimov.smartexpenses.di.Injector
import com.joerakhimov.smartexpenses.helper.mvvm.SingleLiveEvent
import javax.inject.Inject

class WelcomeViewModel: ViewModel() {

    @Inject
    lateinit var repository: SmartExpensesRepository

    internal val userLoggedInEvent = SingleLiveEvent<Boolean>()

    init {
        Injector.appComponent.inject(this)
        userLoggedInEvent.value = repository.isUserLoggedIn()
    }

}