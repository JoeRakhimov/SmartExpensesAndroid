package com.joerakhimov.smartexpenses.screen.main.addexpense

import android.content.Context
import android.location.Address
import android.location.Location
import com.joerakhimov.smartexpenses.base.BaseViewModel
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepository
import com.joerakhimov.smartexpenses.di.Injector
import com.joerakhimov.smartexpenses.events.ExpenseAddedEvent
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProvider
import com.joerakhimov.smartexpenses.screen.main.addexpense.model.AddExpenseRequest
import io.nlopez.smartlocation.SmartLocation
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject


class AddExpenseViewModel : BaseViewModel() {

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var repository: SmartExpensesRepository

    @Inject
    lateinit var schedulerProvider: SchedulerProvider

    @Inject
    lateinit var expenseModel: ExpensesModel

    init {
        Injector.appComponent.inject(this)
    }

    var address: Address? = null

    fun startLocationListening() {
        SmartLocation.with(context).location()
            .start {
                getAddress(it)
            }
    }

    private fun getAddress(location: Location){
        SmartLocation.with(context).geocoding()
            .reverse(location) { location, addresses ->
                if(addresses.isNotEmpty()) address = addresses[0]
            }
    }

    fun save(name: String, amount: String, categoryId: Int) {

        if(!expenseModel.isNameValid(name)){
            toastMessage.value = com.joerakhimov.smartexpenses.R.string.expense_name_is_invalid
            return
        }

        if(!expenseModel.isAmountValid(amount)){
            toastMessage.value = com.joerakhimov.smartexpenses.R.string.amount_should_be_more_than_0
            return
        }

        if(!expenseModel.isCategoryValid(categoryId)){
            toastMessage.value = com.joerakhimov.smartexpenses.R.string.category_not_selected
            return
        }

        addExpense(AddExpenseRequest(
            title = name,
            jsonMemberPrivate = false,
            currency = "HUF",
            value = amount.toLong(),
            latitude = address?.latitude,
            longitude = address?.longitude,
            address = if(address?.maxAddressLineIndex!=null && address?.maxAddressLineIndex!! >=0) address?.getAddressLine(0) else null,
            categoryID = categoryId
        ))

    }

    private fun addExpense(request: AddExpenseRequest){
        repository
            .addExpense(request)
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.ui)
            .doOnSubscribe { isLoading.set(true) }
            .doFinally { isLoading.set(false) }
            .subscribe({
                if (it.status == 0){
                    EventBus.getDefault().post(ExpenseAddedEvent())
                    isOpen.set(false)
                }
                else toastMessage.value = it.message
            }, {
                toastMessage.value = it.message
            })

    }

    override fun onCleared() {
        super.onCleared()
        SmartLocation.with(context).location().stop();
    }

}
