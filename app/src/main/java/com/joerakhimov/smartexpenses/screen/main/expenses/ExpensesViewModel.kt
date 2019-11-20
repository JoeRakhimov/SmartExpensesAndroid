package com.joerakhimov.smartexpenses.screen.main.expenses

import androidx.lifecycle.MutableLiveData
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseViewModel
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepository
import com.joerakhimov.smartexpenses.di.Injector
import com.joerakhimov.smartexpenses.events.ExpenseAddedEvent
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProvider
import com.joerakhimov.smartexpenses.screen.main.home.model.ExpensesItem
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

class ExpensesViewModel : BaseViewModel() {

    var expenses = MutableLiveData<List<ExpensesItem?>>()

    @Inject
    lateinit var repository: SmartExpensesRepository

    @Inject
    lateinit var schedulerProvider: SchedulerProvider

    init {
        Injector.appComponent.inject(this)
        EventBus.getDefault().register(this)
        getExpenses()
    }

    @Subscribe
    fun onExpenseAdded(event: ExpenseAddedEvent){
        getExpenses()
    }

    fun onRefresh(){
        getExpenses()
    }

    private fun getExpenses(){
        repository.getExpenses()
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.ui)
            .doOnSubscribe { isLoading.set(true) }
            .doFinally { isLoading.set(false) }
            .subscribe({
                if (it.status == 0) expenses.value = it.expenses
                else toastMessage.value = it.message
            }, {
                toastMessage.value = it.message
            })
    }

    override fun onCleared() {
        super.onCleared()
        EventBus.getDefault().unregister(this)
    }

    fun deleteExpense(expenseId: Int?) {
        repository.deleteExpense(expenseId)
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.ui)
            .subscribe({
                if (it.status == 0){
                    toastMessage.value = R.string.expense_deleted_successfully
                    getExpenses()
                }
                else toastMessage.value = it.message
            }, {
                toastMessage.value = it.message
            })
    }

}