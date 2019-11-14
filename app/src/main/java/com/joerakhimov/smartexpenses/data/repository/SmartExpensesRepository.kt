package com.joerakhimov.smartexpenses.data.repository

import com.joerakhimov.smartexpenses.screen.auth.login.model.LoginRequest
import com.joerakhimov.smartexpenses.screen.auth.login.model.LoginResponse
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterRequest
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterResponse
import com.joerakhimov.smartexpenses.screen.main.expenses.model.ExpensesResponse
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
    fun getExpenses(): Single<ExpensesResponse>
}