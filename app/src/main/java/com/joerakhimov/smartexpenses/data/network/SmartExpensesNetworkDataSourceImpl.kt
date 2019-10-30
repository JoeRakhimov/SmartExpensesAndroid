package com.joerakhimov.smartexpenses.data.network

import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterRequest
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterResponse
import io.reactivex.Single

class SmartExpensesNetworkDataSourceImpl(private val api: SmartExpensesService): SmartExpensesNetworkDataSource {

    override fun register(registerRequest: RegisterRequest): Single<RegisterResponse> {
        return api.register(registerRequest)
    }

}