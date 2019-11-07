package com.joerakhimov.smartexpenses.screen.auth.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseFragment
import com.joerakhimov.smartexpenses.screen.main.MainActivity
import kotlinx.android.synthetic.main.fragment_register.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class RegisterFragment : BaseFragment(), KodeinAware {

    override fun getLayoutRes() = R.layout.fragment_register

    private lateinit var viewModel: RegisterViewModel

    val activityKodein by closestKodein()
    override val kodein by Kodein.lazy { extend(activityKodein) }
    private val viewModelFactory: RegisterViewModelFactory by instance()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RegisterViewModel::class.java)

        observeToastMessage()

        initRegisterButton()

        viewModel.registrationSuccessEvent.observe(this, Observer { success ->
            if (success) startActivity(Intent(activity, MainActivity::class.java))
        })

    }

    private fun initRegisterButton() {
        buttonRegister.setOnClickListener {
            viewModel.onRegisterButtonClick(
                inputEmail.getText(),
                inputPassword.getText(),
                inputConfirmPassword.getText()
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
