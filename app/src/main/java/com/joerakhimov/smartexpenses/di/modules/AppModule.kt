package com.joerakhimov.smartexpenses.di.modules

import android.app.Application
import android.content.Context
import com.ipakyulibank.mobile.data.preferences.Prefs
import com.ipakyulibank.mobile.data.preferences.PrefsImpl
import com.ipakyulibank.mobile.data.preferences.PrefsUtil
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProvider
import com.joerakhimov.smartexpenses.helper.concurrent.SchedulerProviderImpl
import dagger.Module
import dagger.Provides

@Module
class AppModule(var applicaiton: Application) {

    @Provides
    fun provideContext(): Context = applicaiton.applicationContext

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = SchedulerProviderImpl()

    @Provides
    fun providePrefs(preferencesUtil: PrefsUtil): Prefs = PrefsImpl(preferencesUtil)

//    bind() from provider { WelcomeViewModelFactory() }
//    bind() from provider { RegisterViewModelFactory(instance(), instance(), instance()) }
//    bind() from provider { LoginViewModelFactory(instance(), instance(), instance()) }

}