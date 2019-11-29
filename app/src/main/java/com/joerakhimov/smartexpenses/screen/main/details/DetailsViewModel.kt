package com.joerakhimov.smartexpenses.screen.main.details

import com.joerakhimov.smartexpenses.base.BaseViewModel
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepository
import com.joerakhimov.smartexpenses.di.Injector
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProvider
import javax.inject.Inject

class DetailsViewModel : BaseViewModel() {

    @Inject
    lateinit var repository: SmartExpensesRepository

    @Inject
    lateinit var schedulerProvider: SchedulerProvider

    init {
        Injector.appComponent.inject(this)
    }

    fun getExpense(id: Int?){
        if(id==null) return
        repository.getExpense(id)
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.ui)
            .doOnSubscribe { isLoading.set(true) }
            .doFinally { isLoading.set(false) }
            .subscribe({
                if (it.status == 0){}
                else toastMessage.value = it.message
            }, {
                toastMessage.value = it.message
            })
    }

}
