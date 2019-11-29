package com.joerakhimov.smartexpenses.screen.main.details

import androidx.lifecycle.MutableLiveData
import com.joerakhimov.smartexpenses.base.BaseViewModel
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepository
import com.joerakhimov.smartexpenses.di.Injector
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProvider
import com.joerakhimov.smartexpenses.screen.main.home.model.ExpensesItem
import javax.inject.Inject

class DetailsViewModel : BaseViewModel() {

    @Inject
    lateinit var repository: SmartExpensesRepository

    @Inject
    lateinit var schedulerProvider: SchedulerProvider

    init {
        Injector.appComponent.inject(this)
    }

    var mId: Int? = null

    var expense = MutableLiveData<ExpensesItem?>()

    fun getExpense(id: Int?){
        mId = id
        loadExpense()
    }

    private fun loadExpense() {
        if(mId==null) return
        repository.getExpense(mId!!)
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.ui)
            .doOnSubscribe { isLoading.set(true) }
            .doFinally { isLoading.set(false) }
            .subscribe({
                if (it.status == 0) {
                    expense.value = it.expenses
                } else toastMessage.value = it.message
            }, {
                toastMessage.value = it.message
            })
    }

    fun onRefresh(){
        loadExpense()
    }

}
