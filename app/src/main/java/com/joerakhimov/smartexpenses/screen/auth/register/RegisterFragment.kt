package com.joerakhimov.smartexpenses.screen.auth.register


import android.content.Intent
import android.os.Bundle
import android.view.View
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseFragment
import com.joerakhimov.smartexpenses.extension.startClearActivity
import com.joerakhimov.smartexpenses.screen.main.MainActivity
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : BaseFragment() {

    override fun getLayoutRes() = R.layout.fragment_register

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        buttonRegister.setOnClickListener {
            context?.startClearActivity<MainActivity>()
        }

    }

}
