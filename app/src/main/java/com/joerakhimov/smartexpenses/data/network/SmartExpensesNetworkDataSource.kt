package com.joerakhimov.smartexpenses.data.network

import com.joerakhimov.smartexpenses.screen.auth.login.model.LoginRequest
import com.joerakhimov.smartexpenses.screen.auth.login.model.LoginResponse
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterRequest
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterResponse
import io.reactivex.Single

interface SmartExpensesNetworkDataSource {

    fun register(registerRequest: RegisterRequest): Single<RegisterResponse>
    fun login(registerRequest: LoginRequest): Single<LoginResponse>

}