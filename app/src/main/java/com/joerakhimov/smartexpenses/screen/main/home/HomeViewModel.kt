package com.joerakhimov.smartexpenses.screen.main.home

import androidx.lifecycle.MutableLiveData
import com.joerakhimov.smartexpenses.base.BaseViewModel
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepository
import com.joerakhimov.smartexpenses.di.Injector
import com.joerakhimov.smartexpenses.events.ExpenseAddedEvent
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProvider
import com.joerakhimov.smartexpenses.screen.main.home.model.ExpensesItem
import com.joerakhimov.smartexpenses.screen.main.home.model.ImagesItem
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

class HomeViewModel : BaseViewModel() {

    var expenses = MutableLiveData<List<ExpensesItem?>>()
    var images = MutableLiveData<List<ImagesItem?>>()

    @Inject
    lateinit var repository: SmartExpensesRepository

    @Inject
    lateinit var schedulerProvider: SchedulerProvider

    init {
        Injector.appComponent.inject(this)
        EventBus.getDefault().register(this)
    }

    fun onRefresh(){
        getExpenses()
    }

    @Subscribe
    fun onExpenseAdded(event: ExpenseAddedEvent){
        getExpenses()
    }

    fun getExpenses(){
        repository.getRecentExpenses(5)
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.ui)
            .doOnSubscribe { isLoading.set(true) }
            .doFinally { isLoading.set(false) }
            .subscribe({
                if (it.status == 0){
                    expenses.value = it.expenses?.expenses
                    images.value = it.expenses?.images
                }
                else toastMessage.value = it.message
            }, {
                toastMessage.value = it.message
            })
    }

    override fun onCleared() {
        super.onCleared()
        EventBus.getDefault().unregister(this)
    }

}