package com.joerakhimov.smartexpenses.screen.auth.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_register.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
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
        viewModel.toastMessage.observe(this, Observer { res ->
            if (res != null) {
                val message = getString(res)
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }
        })
        buttonRegister.setOnClickListener {
            viewModel.onRegisterButtonClick(
                inputEmail.getText(),
                inputPassword.getText(),
                inputConfirmPassword.getText()
            )
        }
    }

}
