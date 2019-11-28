package com.joerakhimov.smartexpenses.di

import com.joerakhimov.smartexpenses.di.modules.*
import com.joerakhimov.smartexpenses.screen.auth.login.LoginViewModel
import com.joerakhimov.smartexpenses.screen.auth.register.RegisterViewModel
import com.joerakhimov.smartexpenses.screen.auth.welcome.WelcomeViewModel
import com.joerakhimov.smartexpenses.screen.main.addexpense.AddExpenseFragment
import com.joerakhimov.smartexpenses.screen.main.addexpense.AddExpenseViewModel
import com.joerakhimov.smartexpenses.screen.main.expenses.ExpensesViewModel
import com.joerakhimov.smartexpenses.screen.main.home.HomeViewModel
import com.joerakhimov.smartexpenses.screen.main.profile.ProfileViewModel
import com.joerakhimov.smartexpenses.screen.main.social.SocialFragment
import com.joerakhimov.smartexpenses.screen.main.social.SocialViewModel
import com.joerakhimov.smartexpenses.view.spinner.SmartSpinner
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AuthModule::class, DataModule::class, ExpensesModule::class, HelperModule::class])
interface AppComponent {
    fun inject(viewModel: LoginViewModel)
    fun inject(viewModel: RegisterViewModel)
    fun inject(viewModel: WelcomeViewModel)
    fun inject(viewModel: ProfileViewModel)
    fun inject(viewModel: ExpensesViewModel)
    fun inject(viewModel: HomeViewModel)
    fun inject(smartSpinner: SmartSpinner)
    fun inject(viewModel: AddExpenseViewModel)
    fun inject(fragment: AddExpenseFragment)
    fun inject(viewModel: SocialViewModel)
    fun inject(fragment: SocialFragment)
}