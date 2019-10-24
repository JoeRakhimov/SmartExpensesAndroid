package com.joerakhimov.smartexpenses.screen.launcher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.screen.signin.SignInActivity
import com.joerakhimov.smartexpenses.screen.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_launcher.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class LauncherActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: LauncherViewModelFactory by instance()
    lateinit var viewModel: LauncherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        button_sign_in.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

        button_sign_up.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        viewpager.adapter = LauncherPagerAdapter(supportFragmentManager)
        indicator.setViewPager(viewpager);

    }

}
