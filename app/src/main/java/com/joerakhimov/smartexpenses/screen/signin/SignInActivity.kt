package com.joerakhimov.smartexpenses.screen.signin

import android.content.Intent
import android.os.Bundle
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseActivity
import com.joerakhimov.smartexpenses.screen.main.MainActivity
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : BaseActivity() {

    override fun getLayoutRes() = R.layout.activity_sign_in

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        button_sign_in.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

}
