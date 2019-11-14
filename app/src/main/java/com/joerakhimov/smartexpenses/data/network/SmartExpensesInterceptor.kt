package com.joerakhimov.smartexpenses.data.network

import com.ipakyulibank.mobile.data.preferences.Prefs
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

const val HEADER_AUTHORIZATION = "Authorization"

class SmartExpensesInterceptor(private val prefs: Prefs): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        val newRequest = request.newBuilder()
            .addHeader(HEADER_AUTHORIZATION, "Bearer "+prefs.getToken())
            .build()

        return chain.proceed(newRequest)

    }

}