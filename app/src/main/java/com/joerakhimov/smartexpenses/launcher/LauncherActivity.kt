package com.joerakhimov.smartexpenses.launcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joerakhimov.smartexpenses.R
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class LauncherActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: LauncherViewModelFactory by instance()
    lateinit var viewModel: LauncherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
    }

}
