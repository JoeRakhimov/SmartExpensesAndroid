package com.joerakhimov.smartexpenses.app

import android.app.Application
import com.joerakhimov.smartexpenses.data.network.SmartExpensesNetworkDataSource
import com.joerakhimov.smartexpenses.data.network.SmartExpensesNetworkDataSourceImpl
import com.joerakhimov.smartexpenses.data.network.SmartExpensesService
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepository
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepositoryImpl
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProvider
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProviderImpl
import com.joerakhimov.smartexpenses.screen.auth.register.AuthModel
import com.joerakhimov.smartexpenses.screen.auth.register.RegisterViewModelFactory
import com.joerakhimov.smartexpenses.screen.auth.welcome.WelcomeViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class App: Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        bind() from singleton { this@App }
        bind() from provider { WelcomeViewModelFactory() }
        bind() from singleton { AuthModel() }
        bind<SchedulerProvider>() with singleton { SchedulerProviderImpl() }
        bind() from singleton { SmartExpensesService(instance()) }
        bind<SmartExpensesNetworkDataSource>() with singleton { SmartExpensesNetworkDataSourceImpl(instance()) }
        bind<SmartExpensesRepository>() with singleton { SmartExpensesRepositoryImpl(instance()) }
        bind() from provider { RegisterViewModelFactory(instance(), instance(), instance()) }
    }

}