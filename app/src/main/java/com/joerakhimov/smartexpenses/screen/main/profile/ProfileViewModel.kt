package com.joerakhimov.smartexpenses.screen.main.profile

import androidx.lifecycle.MutableLiveData
import com.joerakhimov.smartexpenses.base.BaseViewModel
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepository
import com.joerakhimov.smartexpenses.di.Injector
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProvider
import com.joerakhimov.smartexpenses.screen.main.profile.model.ProfileInfo
import com.joerakhimov.smartexpenses.screen.main.profile.model.UpdateProfileRequest
import javax.inject.Inject

class ProfileViewModel : BaseViewModel() {

    @Inject
    lateinit var repository: SmartExpensesRepository

    @Inject
    lateinit var schedulerProvider: SchedulerProvider

    val email = MutableLiveData<String>()

    var profileInfo = MutableLiveData<ProfileInfo?>()

    init {
        Injector.appComponent.inject(this)
        email.value = repository.getEmail()
        getProfileInfo()
    }

    private fun getProfileInfo() {
        repository.getProfile()
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.ui)
            .doOnSubscribe { isLoading.set(true) }
            .doFinally { isLoading.set(false) }
            .subscribe({
                if (it.status == 0) {
                    if (it.profile != null) {
                        if(it.profile.numLatestSpendings!=null){
                            repository.setLatestSpendingsAmount(it.profile.numLatestSpendings)
                        }
                        profileInfo.value = it.profile
                    }
                } else toastMessage.value = it.message
            }, {
                toastMessage.value = it.message
            })
    }

    fun onLogoutButtonClick() {
        repository.logout()
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.ui)
            .doOnSubscribe { isLoading.set(true) }
            .doFinally { isLoading.set(false) }
            .subscribe({
                if (it.status == 0) {
                    repository.removeToken()
                    repository.removeEmail()
                    isOpen.set(false)
                } else toastMessage.value = it.message
            }, {
                toastMessage.value = it.message
            })
    }

    fun onRefresh() {
        getProfileInfo()
    }

    fun onColorSelection(selectedColor: String) {
        val request = UpdateProfileRequest(
            color = selectedColor,
            numLatestSpendings = profileInfo.value?.numLatestSpendings
        )
        updateProfile(request)
    }

    fun onAmountSelection(amount: Int) {
        val request = UpdateProfileRequest(
            color = profileInfo.value?.color,
            numLatestSpendings = amount
        )
        updateProfile(request)
    }

    private fun updateProfile(request: UpdateProfileRequest) {
        repository.updateProfile(request)
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.ui)
            .doOnSubscribe { isLoading.set(true) }
            .doFinally { isLoading.set(false) }
            .subscribe({
                if (it.status == 0) {
                    getProfileInfo()
                } else toastMessage.value = it.message
            }, {
                toastMessage.value = it.message
            })
    }

}