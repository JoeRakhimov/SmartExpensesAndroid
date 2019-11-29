package com.joerakhimov.smartexpenses.data.network

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
import com.joerakhimov.smartexpenses.screen.main.profile.model.LogoutResponse
import com.joerakhimov.smartexpenses.screen.main.profile.model.ProfileResponse
import com.joerakhimov.smartexpenses.screen.main.social.model.LocationsResponse
import io.reactivex.Single

class SmartExpensesNetworkDataSourceImpl(private val api: SmartExpensesService): SmartExpensesNetworkDataSource {

    override fun register(request: RegisterRequest): Single<RegisterResponse> {
        return api.register(request)
    }

    override fun login(request: LoginRequest): Single<LoginResponse> {
        return api.login(request)
    }

    override fun getRecentExpenses(amount: Int): Single<RecentExpensesResponse> {
        return api.getRecentExpenses(amount)
    }

    override fun getExpenses(): Single<ExpensesResponse> {
        return api.getExpenses()
    }

    override fun getExpense(id: Int): Single<ExpenseResponse> {
        return api.getExpense(id)
    }

    override fun addExpense(addExpenseRequest: AddExpenseRequest): Single<AddExpenseResponse> {
        return api.addExpense(addExpenseRequest)
    }

    override fun deleteExpense(expenseId: Int?): Single<DeleteExpenseResponse> {
        return api.deleteExpense(expenseId)
    }

    override fun getLocations(): Single<LocationsResponse> {
        return api.getLocations()
    }

    override fun getProfile(): Single<ProfileResponse> {
        return api.getProfile()
    }

    override fun logout(): Single<LogoutResponse> {
        return api.logout()
    }

}