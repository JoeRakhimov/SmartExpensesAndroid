package com.joerakhimov.smartexpenses.di

import android.app.Application
import com.joerakhimov.smartexpenses.di.modules.AppModule

object Injector {

    lateinit var appComponent: AppComponent

    fun initAppComponent(application: Application){
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(application))
                .build()
    }

}