package com.joerakhimov.smartexpenses.screen.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepository
import com.joerakhimov.smartexpenses.di.Injector
import javax.inject.Inject

class ProfileViewModel: ViewModel() {

    @Inject
    lateinit var repository: SmartExpensesRepository

    val email = MutableLiveData<String>()

    init {
        Injector.appComponent.inject(this)
        email.value = repository.getEmail()
    }

    fun onLogoutButtonClick() {
        repository.removeToken()
        repository.removeEmail()
    }

}