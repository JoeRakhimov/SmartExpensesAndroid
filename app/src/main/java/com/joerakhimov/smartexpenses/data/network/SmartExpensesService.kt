package com.joerakhimov.smartexpenses.data.network

import android.content.Context
import com.ipakyulibank.mobile.data.preferences.Prefs
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.joerakhimov.smartexpenses.BuildConfig
import com.joerakhimov.smartexpenses.screen.auth.login.model.LoginRequest
import com.joerakhimov.smartexpenses.screen.auth.login.model.LoginResponse
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterRequest
import com.joerakhimov.smartexpenses.screen.auth.register.model.RegisterResponse
import com.joerakhimov.smartexpenses.screen.main.addexpense.model.AddExpenseRequest
import com.joerakhimov.smartexpenses.screen.main.addexpense.model.AddExpenseResponse
import com.joerakhimov.smartexpenses.screen.main.expenses.model.ExpensesResponse
import com.joerakhimov.smartexpenses.screen.main.home.model.RecentExpensesResponse
import com.readystatesoftware.chuck.ChuckInterceptor
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface SmartExpensesService {

    companion object {
        operator fun invoke(context: Context, prefs: Prefs): SmartExpensesService {

            val client = OkHttpClient.Builder()
                .addInterceptor(SmartExpensesInterceptor(prefs))
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

    @POST("/login")
    fun login(@Body request: LoginRequest): Single<LoginResponse>

    @GET("expense/recent/{amount}")
    fun getRecentExpenses(@Path("amount") amount: Int = 5): Single<RecentExpensesResponse>

    @GET("/expense/all")
    fun getExpenses(): Single<ExpensesResponse>

    @POST("/expense/add")
    fun addExpense(@Body addExpenseRequest: AddExpenseRequest): Single<AddExpenseResponse>

}