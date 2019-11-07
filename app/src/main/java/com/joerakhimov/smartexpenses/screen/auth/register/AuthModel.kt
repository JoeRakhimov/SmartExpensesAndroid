package com.joerakhimov.smartexpenses.screen.auth.register

import android.util.Patterns
import java.security.NoSuchAlgorithmException

class AuthModel {

    fun isEmailValid(email: String): Boolean{
        val emailPattern = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$"
        val emailPatternRegex = Regex(emailPattern)
        return email.matches(emailPatternRegex)
    }

    fun isPasswordValid(password: String): Boolean {
        val passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$"
        val passwordPatternRegex = Regex(passwordPattern)
        return password.matches(passwordPatternRegex)
    }

    fun toMd5(s: String): String {

        try {

            // Create MD5 Hash
            val digest = java.security.MessageDigest.getInstance("MD5")
            digest.update(s.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2)
                    h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ""

    }

    fun arePasswordsAreSame(password: String, confirmPassword: String) = password == confirmPassword

}