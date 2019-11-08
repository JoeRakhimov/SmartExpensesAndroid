package com.joerakhimov.smartexpenses.data.repository

import com.ipakyulibank.mobile.data.preferences.Prefs
import com.joerakhimov.smartexpenses.data.network.SmartExpensesNetworkDataSource
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProvider
import com.joerakhimov.smartexpenses.screen.auth.login.model.LoginRequest
import com.joerakhimov.smartexpenses.screen.auth.login.model.LoginResponse
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterRequest
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterResponse
import io.reactivex.Single

class SmartExpensesRepositoryImpl(

    private val networkDataSource: SmartExpensesNetworkDataSource,
    private val prefs: Prefs,
    private val schedulerProvider: SchedulerProvider): SmartExpensesRepository {

    override fun register(registerRequest: RegisterRequest): Single<RegisterResponse> {
            return networkDataSource.register(registerRequest)
                .subscribeOn(schedulerProvider.io)
                .doOnSuccess {
                    if (it.status == 0) {
                        prefs.setEmail(registerRequest.email)
                        prefs.setToken(it.refreshToken)
                    }
                }
    }

    override fun login(loginRequest: LoginRequest): Single<LoginResponse> {
        return networkDataSource.login(loginRequest)
            .doOnSuccess {
                if (it.status == 0) {
                    prefs.setEmail(loginRequest.email)
                    prefs.setToken(it.refreshToken)
                }
            }
    }

    override fun saveToken(token: String?) {
        prefs.setToken(token)
    }

    override fun getToken() = prefs.getToken()

    override fun isUserLoggedIn() = prefs.isUserLogged()

    override fun saveEmail(email: String?) = prefs.setEmail(email)

    override fun getEmail() = prefs.getEmail()

    override fun removeToken() {
        prefs.removeToken()
    }

    override fun removeEmail() {
        prefs.removeEmail()
    }
}