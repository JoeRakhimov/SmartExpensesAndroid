package com.joerakhimov.smartexpenses.data.network

import com.joerakhimov.smartexpenses.screen.auth.login.model.LoginRequest
import com.joerakhimov.smartexpenses.screen.auth.login.model.LoginResponse
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterRequest
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterResponse
import com.joerakhimov.smartexpenses.screen.main.expenses.model.ExpensesResponse
import io.reactivex.Single

interface SmartExpensesNetworkDataSource {

    fun register(registerRequest: RegisterRequest): Single<RegisterResponse>
    fun login(registerRequest: LoginRequest): Single<LoginResponse>
    fun getExpenses(): Single<ExpensesResponse>

}