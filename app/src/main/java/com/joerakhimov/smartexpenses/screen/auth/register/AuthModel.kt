package com.joerakhimov.smartexpenses.screen.auth.register

import android.util.Patterns

class AuthModel {

    fun isEmailValid(email: String): Boolean{
        val emailPattern = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$"
        val emailPatternRegex = Regex(emailPattern)
        return email.matches(emailPatternRegex)
    }

    fun isPasswordValid(password: String): Boolean? {
        val passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$"
        val passwordPatternRegex = Regex(passwordPattern)
        return password.matches(passwordPatternRegex)
    }

    fun arePasswordsAreSame(password: String, confirmPassword: String) = password == confirmPassword

}