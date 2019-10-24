package com.joerakhimov.smartexpenses.screen.signup

import android.content.Intent
import android.os.Bundle
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseActivity
import com.joerakhimov.smartexpenses.screen.main.MainActivity
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : BaseActivity() {

    override fun getLayoutRes() = R.layout.activity_sign_up

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        buttonRegister.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

}
