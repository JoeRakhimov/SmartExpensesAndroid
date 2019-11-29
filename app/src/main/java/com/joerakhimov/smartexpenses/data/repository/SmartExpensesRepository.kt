package com.joerakhimov.smartexpenses.data.repository

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
import com.joerakhimov.smartexpenses.screen.main.profile.model.UpdateProfileRequest
import com.joerakhimov.smartexpenses.screen.main.profile.model.UpdateProfileResponse
import com.joerakhimov.smartexpenses.screen.main.social.model.LocationsResponse
import io.reactivex.Single

interface SmartExpensesRepository {
    fun register(registerRequest: RegisterRequest): Single<RegisterResponse>
    fun login(loginRequest: LoginRequest): Single<LoginResponse>
    fun saveToken(token: String?)
    fun getToken(): String?
    fun isUserLoggedIn(): Boolean?
    fun saveEmail(email: String?)
    fun getEmail(): String?
    fun removeToken()
    fun removeEmail()
    fun getRecentExpenses(amount: Int): Single<RecentExpensesResponse>
    fun getExpenses(): Single<ExpensesResponse>
    fun addExpense(addExpenseRequest: AddExpenseRequest): Single<AddExpenseResponse>
    fun getExpense(id: Int): Single<ExpenseResponse>
    fun deleteExpense(expenseId: Int?): Single<DeleteExpenseResponse>
    fun getLocations(): Single<LocationsResponse>
    fun getProfile(): Single<ProfileResponse>
    fun logout(): Single<LogoutResponse>
    fun updateProfile(request: UpdateProfileRequest): Single<UpdateProfileResponse>
}