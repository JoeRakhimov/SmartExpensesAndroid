package com.joerakhimov.smartexpenses.screen.auth.login


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseFragment
import com.joerakhimov.smartexpenses.screen.main.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.inputEmail
import kotlinx.android.synthetic.main.fragment_login.inputPassword

class LoginFragment : BaseFragment() {

    override fun getLayoutRes() = R.layout.fragment_login

    private lateinit var viewModel: LoginViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        initLoginButton()

        observeToastMessage()

        viewModel.loginSuccessEvent.observe(this, Observer { success ->
            if (success){
                startActivity(Intent(activity, MainActivity::class.java))
                activity?.finish()
            }
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
