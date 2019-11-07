package com.joerakhimov.smartexpenses.data.network

import com.joerakhimov.smartexpenses.screen.auth.login.model.LoginRequest
import com.joerakhimov.smartexpenses.screen.auth.login.model.LoginResponse
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterRequest
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterResponse
import io.reactivex.Single

class SmartExpensesNetworkDataSourceImpl(private val api: SmartExpensesService): SmartExpensesNetworkDataSource {

    override fun register(request: RegisterRequest): Single<RegisterResponse> {
        return api.register(request)
    }

    override fun login(request: LoginRequest): Single<LoginResponse> {
        return api.login(request)
    }

}