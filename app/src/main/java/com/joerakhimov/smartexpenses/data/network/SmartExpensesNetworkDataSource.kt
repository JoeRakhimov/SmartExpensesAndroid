package com.joerakhimov.smartexpenses.data.network

import com.joerakhimov.smartexpenses.screen.auth.login.model.LoginRequest
import com.joerakhimov.smartexpenses.screen.auth.login.model.LoginResponse
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterRequest
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterResponse
import com.joerakhimov.smartexpenses.screen.main.addexpense.model.AddExpenseRequest
import com.joerakhimov.smartexpenses.screen.main.addexpense.model.AddExpenseResponse
import com.joerakhimov.smartexpenses.screen.main.expenses.model.ExpensesResponse
import com.joerakhimov.smartexpenses.screen.main.home.model.DeleteExpenseResponse
import com.joerakhimov.smartexpenses.screen.main.home.model.RecentExpensesResponse
import com.joerakhimov.smartexpenses.screen.main.profile.model.LogoutResponse
import com.joerakhimov.smartexpenses.screen.main.profile.model.ProfileResponse
import com.joerakhimov.smartexpenses.screen.main.social.model.LocationsResponse
import io.reactivex.Single

interface SmartExpensesNetworkDataSource {

    fun register(registerRequest: RegisterRequest): Single<RegisterResponse>
    fun login(registerRequest: LoginRequest): Single<LoginResponse>
    fun getRecentExpenses(amount: Int): Single<RecentExpensesResponse>
    fun getExpenses(): Single<ExpensesResponse>
    fun addExpense(addExpenseRequest: AddExpenseRequest): Single<AddExpenseResponse>
    fun deleteExpense(expenseId: Int?): Single<DeleteExpenseResponse>
    fun getLocations(): Single<LocationsResponse>
    fun getProfile(): Single<ProfileResponse>
    fun logout(): Single<LogoutResponse>

}