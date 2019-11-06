package com.joerakhimov.smartexpenses.screen.auth.register

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : BaseFragment() {

    override fun getLayoutRes() = R.layout.fragment_register

    private lateinit var viewModel: RegisterViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        buttonRegister.setOnClickListener {
            viewModel.onRegisterButtonClick(
                inputEmail.getText(),
                inputPassword.getText(),
                inputConfirmPassword.getText())
        }
    }

}
