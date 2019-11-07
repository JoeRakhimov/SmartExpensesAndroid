package com.joerakhimov.smartexpenses.screen.auth.login


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseFragment
import com.joerakhimov.smartexpenses.extension.startClearActivity
import com.joerakhimov.smartexpenses.screen.auth.register.RegisterViewModel
import com.joerakhimov.smartexpenses.screen.auth.register.RegisterViewModelFactory
import com.joerakhimov.smartexpenses.screen.main.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.inputEmail
import kotlinx.android.synthetic.main.fragment_login.inputPassword
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class LoginFragment : BaseFragment(), KodeinAware {

    override fun getLayoutRes() = R.layout.fragment_login

    val activityKodein by closestKodein()
    override val kodein by Kodein.lazy { extend(activityKodein) }
    private val viewModelFactory: LoginViewModelFactory by instance()

    private lateinit var viewModel: LoginViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

        initLoginButton()

        observeToastMessage()

        viewModel.loginSuccessEvent.observe(this, Observer { success ->
            if (success) startActivity(Intent(activity, MainActivity::class.java))
        })

    }

    private fun initLoginButton() {
        button_sign_in.setOnClickListener {
            viewModel.onLoginButtonClick(
                inputEmail.getText(),
                inputPassword.getText()
            )
        }
    }

    private fun observeToastMessage() {
        viewModel.toastMessage.observe(this, Observer { any ->
            var message = ""
            when (any) {
                is Int -> message = getString(any)
                is String -> message = any
            }
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        })
    }

}
