package com.joerakhimov.smartexpenses.data.repository

import com.joerakhimov.smartexpenses.screen.auth.login.model.LoginRequest
import com.joerakhimov.smartexpenses.screen.auth.login.model.LoginResponse
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterRequest
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterResponse
import io.reactivex.Single

interface SmartExpensesRepository {
    fun register(registerRequest: RegisterRequest): Single<RegisterResponse>
    fun login(loginRequest: LoginRequest): Single<LoginResponse>
}