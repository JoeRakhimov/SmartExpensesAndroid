package com.joerakhimov.smartexpenses.data.repository

import com.joerakhimov.smartexpenses.data.network.SmartExpensesNetworkDataSource
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterRequest
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterResponse
import io.reactivex.Single

class SmartExpensesRepositoryImpl(private val networkDataSource: SmartExpensesNetworkDataSource): SmartExpensesRepository {

    override fun register(registerRequest: RegisterRequest): Single<RegisterResponse> {
            return networkDataSource.register(registerRequest)
    }

}