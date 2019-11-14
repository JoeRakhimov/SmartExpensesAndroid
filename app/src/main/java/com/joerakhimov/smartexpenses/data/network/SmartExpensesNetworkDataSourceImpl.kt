package com.joerakhimov.smartexpenses.data.network

import com.joerakhimov.smartexpenses.screen.auth.login.model.LoginRequest
import com.joerakhimov.smartexpenses.screen.auth.login.model.LoginResponse
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterRequest
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterResponse
import com.joerakhimov.smartexpenses.screen.main.expenses.model.ExpensesResponse
import io.reactivex.Single

class SmartExpensesNetworkDataSourceImpl(private val api: SmartExpensesService): SmartExpensesNetworkDataSource {

    override fun register(request: RegisterRequest): Single<RegisterResponse> {
        return api.register(request)
    }

    override fun login(request: LoginRequest): Single<LoginResponse> {
        return api.login(request)
    }

    override fun getExpenses(): Single<ExpensesResponse> {
        return api.getExpenses()
    }

}