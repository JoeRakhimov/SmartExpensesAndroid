package com.joerakhimov.smartexpenses.di.modules

import com.joerakhimov.smartexpenses.screen.main.addexpense.ExpensesModel
import dagger.Module
import dagger.Provides

@Module
class ExpensesModule {

    @Provides
    fun provideExpenseModel() = ExpensesModel()

}