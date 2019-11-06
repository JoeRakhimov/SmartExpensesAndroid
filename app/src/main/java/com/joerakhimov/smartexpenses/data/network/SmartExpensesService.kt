package com.joerakhimov.smartexpenses.data.network

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.joerakhimov.smartexpenses.BuildConfig
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterRequest
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterResponse
import com.readystatesoftware.chuck.ChuckInterceptor
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SmartExpensesService {

    companion object {
        operator fun invoke(context: Context): SmartExpensesService {

            val client = OkHttpClient.Builder()
                .addInterceptor(ChuckInterceptor(context))
                .build()

            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(SmartExpensesService::class.java)

        }
    }

    @POST("/register")
    fun register(@Body request: RegisterRequest): Single<RegisterResponse>

}