package com.joerakhimov.smartexpenses.screen.main.social

import android.content.Context
import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.joerakhimov.smartexpenses.base.BaseViewModel
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepository
import com.joerakhimov.smartexpenses.di.Injector
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProvider
import com.joerakhimov.smartexpenses.screen.main.social.model.LocationItem
import io.nlopez.smartlocation.SmartLocation
import javax.inject.Inject

class SocialViewModel : BaseViewModel() {

    var locations = MutableLiveData<List<LocationItem?>>()
    var location = MutableLiveData<Location?>()

    @Inject
    lateinit var repository: SmartExpensesRepository

    @Inject
    lateinit var schedulerProvider: SchedulerProvider

    @Inject
    lateinit var context: Context

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

    fun onNavigationButtonClick() {

    }

    fun startLocationListening() {
        SmartLocation.with(context).location().start { location.value = it }
    }

}