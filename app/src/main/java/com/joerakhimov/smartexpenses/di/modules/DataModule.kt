package com.joerakhimov.smartexpenses.di.modules

import android.content.Context
import com.ipakyulibank.mobile.data.preferences.Prefs
import com.ipakyulibank.mobile.data.preferences.PrefsImpl
import com.ipakyulibank.mobile.data.preferences.PrefsUtil
import com.joerakhimov.smartexpenses.data.network.SmartExpensesNetworkDataSource
import com.joerakhimov.smartexpenses.data.network.SmartExpensesNetworkDataSourceImpl
import com.joerakhimov.smartexpenses.data.network.SmartExpensesService
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepository
import com.joerakhimov.smartexpenses.data.repository.SmartExpensesRepositoryImpl
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideService(context: Context, prefs: Prefs) = SmartExpensesService(context, prefs)

    @Provides
    fun provideNetworkDataSource(service: SmartExpensesService): SmartExpensesNetworkDataSource = SmartExpensesNetworkDataSourceImpl(service)

    @Provides
    fun providePrefsUtil(context: Context) = PrefsUtil(context)

    @Provides
    fun providePrefs(prefsUtil: PrefsUtil) = PrefsImpl(prefsUtil)

    @Provides
    fun provideRepository(networkDataSource: SmartExpensesNetworkDataSource, prefs: Prefs, schedulerProvider: SchedulerProvider): SmartExpensesRepository = SmartExpensesRepositoryImpl(networkDataSource, prefs, schedulerProvider)

}