package com.joerakhimov.smartexpenses.di

import com.joerakhimov.smartexpenses.di.modules.AppModule
import com.joerakhimov.smartexpenses.di.modules.AuthModule
import com.joerakhimov.smartexpenses.di.modules.DataModule
import com.joerakhimov.smartexpenses.screen.auth.login.LoginViewModel
import com.joerakhimov.smartexpenses.screen.auth.register.RegisterViewModel
import com.joerakhimov.smartexpenses.screen.auth.welcome.WelcomeViewModel
import com.joerakhimov.smartexpenses.screen.main.expenses.ExpensesViewModel
import com.joerakhimov.smartexpenses.screen.main.home.HomeViewModel
import com.joerakhimov.smartexpenses.screen.main.profile.ProfileViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AuthModule::class, DataModule::class])
interface AppComponent {
    fun inject(viewModel: LoginViewModel)
    fun inject(viewModel: RegisterViewModel)
    fun inject(viewModel: WelcomeViewModel)
    fun inject(viewModel: ProfileViewModel)
    fun inject(viewModel: ExpensesViewModel)
    fun inject(viewModel: HomeViewModel)
}