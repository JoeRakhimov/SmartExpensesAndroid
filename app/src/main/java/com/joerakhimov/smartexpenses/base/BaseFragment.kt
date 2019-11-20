package com.joerakhimov.smartexpenses.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.screen.auth.login.LoginViewModel

abstract class BaseFragment: Fragment() {

    abstract fun getLayoutRes(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutRes(), container, false)
    }

}