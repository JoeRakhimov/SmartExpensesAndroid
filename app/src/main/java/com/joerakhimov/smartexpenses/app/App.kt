package com.joerakhimov.smartexpenses.app

import android.app.Application
import com.joerakhimov.smartexpenses.di.Injector

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        Injector.initAppComponent(this)

    }

//    override val kodein = Kodein.lazy {
//        bind() from singleton { this@App }
//        bind() from provider { WelcomeViewModelFactory() }
//        bind() from singleton { AuthModel() }
//        bind<SchedulerProvider>() with singleton { SchedulerProviderImpl() }
//        bind() from singleton { SmartExpensesService(instance()) }
//        bind<SmartExpensesNetworkDataSource>() with singleton { SmartExpensesNetworkDataSourceImpl(instance()) }
//        bind<SmartExpensesRepository>() with singleton { SmartExpensesRepositoryImpl(instance()) }
//        bind() from provider { RegisterViewModelFactory(instance(), instance(), instance()) }
//        bind() from provider { LoginViewModelFactory(instance(), instance(), instance()) }
//    }

}