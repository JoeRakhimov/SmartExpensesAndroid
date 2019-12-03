package com.joerakhimov.smartexpenses.data.repository

import com.ipakyulibank.mobile.data.preferences.Prefs
import com.joerakhimov.smartexpenses.data.network.SmartExpensesNetworkDataSource
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProvider
import com.joerakhimov.smartexpenses.screen.auth.login.model.LoginRequest
import com.joerakhimov.smartexpenses.screen.auth.login.model.LoginResponse
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterRequest
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterResponse
import com.joerakhimov.smartexpenses.screen.main.addexpense.model.AddExpenseRequest
import com.joerakhimov.smartexpenses.screen.main.addexpense.model.AddExpenseResponse
import com.joerakhimov.smartexpenses.screen.main.details.model.ExpenseResponse
import com.joerakhimov.smartexpenses.screen.main.expenses.model.ExpensesResponse
import com.joerakhimov.smartexpenses.screen.main.home.model.DeleteExpenseResponse
import com.joerakhimov.smartexpenses.screen.main.home.model.RecentExpensesResponse
import com.joerakhimov.smartexpenses.screen.main.profile.model.*
import com.joerakhimov.smartexpenses.screen.main.social.model.LocationsResponse
import io.reactivex.Single

class SmartExpensesRepositoryImpl(
    private val networkDataSource: SmartExpensesNetworkDataSource,
    private val prefs: Prefs,
    private val schedulerProvider: SchedulerProvider): SmartExpensesRepository {

    override fun register(registerRequest: RegisterRequest): Single<RegisterResponse> {
            return networkDataSource.register(registerRequest)
                .subscribeOn(schedulerProvider.io)
                .doOnSuccess {
                    if (it.status == 0) {
                        prefs.setEmail(registerRequest.email)
                        prefs.setToken(it.refreshToken)
                    }
                }
    }

    override fun login(loginRequest: LoginRequest): Single<LoginResponse> {
        return networkDataSource.login(loginRequest)
            .doOnSuccess {
                if (it.status == 0) {
                    prefs.setEmail(loginRequest.email)
                    prefs.setToken(it.refreshToken)
                }
            }
    }

    override fun saveToken(token: String?) {
        prefs.setToken(token)
    }

    override fun getToken() = prefs.getToken()

    override fun isUserLoggedIn() = prefs.isUserLogged()

    override fun saveEmail(email: String?) = prefs.setEmail(email)

    override fun getEmail() = prefs.getEmail()

    override fun removeToken() {
        prefs.removeToken()
    }

    override fun removeEmail() {
        prefs.removeEmail()
    }

    override fun getRecentExpenses(): Single<RecentExpensesResponse> {
        return networkDataSource.getRecentExpenses(prefs.getLatestSpendingsAmount())
    }

    override fun getExpenses(): Single<ExpensesResponse> {
        return networkDataSource.getExpenses()
    }

    override fun addExpense(addExpenseRequest: AddExpenseRequest): Single<AddExpenseResponse> {
        return networkDataSource.addExpense(addExpenseRequest)
    }

    override fun getExpense(id: Int): Single<ExpenseResponse> {
        return networkDataSource.getExpense(id)
    }

    override fun deleteExpense(expenseId: Int?): Single<DeleteExpenseResponse> {
        return networkDataSource.deleteExpense(expenseId)
    }

    override fun getLocations(): Single<LocationsResponse> {
        return networkDataSource.getLocations()
    }

    override fun getProfile(): Single<ProfileResponse> {
        return networkDataSource.getProfile()
    }

    override fun updateProfile(request: UpdateProfileRequest): Single<UpdateProfileResponse> {
        return networkDataSource.updateProfile(request)
    }

    override fun logout(): Single<LogoutResponse> {
        return networkDataSource.logout()
    }

    override fun setLatestSpendingsAmount(amount: Int) {
        prefs.setLatestSpendingsAmount(amount)
    }

    override fun getLatestSpendingsAmount(): Int {
        return prefs.getLatestSpendingsAmount()
    }

    override fun updatePhoto(request: PhotoRequest): Single<UpdateProfileResponse> {
        return networkDataSource.updatePhoto(request)
    }

}