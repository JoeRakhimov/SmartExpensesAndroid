package com.joerakhimov.smartexpenses.screen.auth.login


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseFragment
import com.joerakhimov.smartexpenses.extension.startClearActivity
import com.joerakhimov.smartexpenses.screen.main.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment() {

    override fun getLayoutRes() = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        button_sign_in.setOnClickListener {
            context?.startClearActivity<MainActivity>()
        }

    }

}
