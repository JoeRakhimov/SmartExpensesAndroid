package com.joerakhimov.smartexpenses.app

import android.app.Application
import com.joerakhimov.smartexpenses.launcher.LauncherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class App: Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        bind() from singleton { this@App }
        bind() from provider { LauncherViewModelFactory() }
    }

}