package com.joerakhimov.smartexpenses.screen.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepository
import com.joerakhimov.smartexpenses.di.Injector
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProvider
import com.joerakhimov.smartexpenses.helper.mvvm.SingleLiveEvent
import com.joerakhimov.smartexpenses.screen.main.expenses.model.ExpensesItem
import javax.inject.Inject

class HomeViewModel : ViewModel() {

    var expenses = MutableLiveData<List<ExpensesItem?>>()

    internal val toastMessage = SingleLiveEvent<Any>()

    @Inject
    lateinit var repository: SmartExpensesRepository

    @Inject
    lateinit var schedulerProvider: SchedulerProvider

    init {
        Injector.appComponent.inject(this)
        getExpenses()
    }

    fun getExpenses(){
        repository.getExpenses()
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.ui)
            .subscribe({
                if (it.status == 0) expenses.value = it.expenses
                else toastMessage.value = it.message
            }, {
                toastMessage.value = it.message
            })
    }

}