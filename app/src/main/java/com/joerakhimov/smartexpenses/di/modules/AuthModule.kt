package com.joerakhimov.smartexpenses.di.modules

import com.ipakyulibank.mobile.data.preferences.Prefs
import com.ipakyulibank.mobile.data.preferences.PrefsImpl
import com.ipakyulibank.mobile.data.preferences.PrefsUtil
import com.joerakhimov.smartexpenses.screen.auth.register.AuthModel
import dagger.Module
import dagger.Provides

@Module
class AuthModule {

    @Provides
    fun provideAuthModel() = AuthModel()

}