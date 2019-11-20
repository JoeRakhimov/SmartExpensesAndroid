package com.joerakhimov.smartexpenses.screen.main.social

import androidx.lifecycle.MutableLiveData
import com.joerakhimov.smartexpenses.base.BaseViewModel
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepository
import com.joerakhimov.smartexpenses.di.Injector
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProvider
import com.joerakhimov.smartexpenses.screen.main.social.model.LocationItem
import javax.inject.Inject

class SocialViewModel : BaseViewModel() {

    var locations = MutableLiveData<List<LocationItem?>>()

    @Inject
    lateinit var repository: SmartExpensesRepository

    @Inject
    lateinit var schedulerProvider: SchedulerProvider

    init {
        Injector.appComponent.inject(this)
        getLocations()
    }

    private fun getLocations(){
        repository.getLocations()
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.ui)
            .doOnSubscribe { isLoading.set(true) }
            .doFinally { isLoading.set(false) }
            .subscribe({
                if (it.status == 0){
                    locations.value = it.location
                }
                else toastMessage.value = it.message
            }, {
                toastMessage.value = it.message
            })
    }

}