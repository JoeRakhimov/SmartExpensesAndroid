package com.joerakhimov.smartexpenses.screen.auth.register

import org.hamcrest.core.Is.`is`
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class AuthModelTest {

    private lateinit var SUT: AuthModel

    @Before
    fun setUp() {
        SUT = AuthModel()
    }

    @Test
    fun validateEmail_validEmail_trueReturned(){
        val email = "test@test.com"
        val result = SUT.isEmailValid(email)
        val expected = true
        assertThat(result, `is`(expected))
    }

    @Test
    fun validateEmail_emptyEmail_falseReturned(){
        val email = ""
        val result = SUT.isEmailValid(email)
        val expected = false
        assertThat(result, `is`(expected))
    }

    @Test
    fun validateEmail_noTextBeforeAtSign_falseReturned(){
        val email = "@test.com"
        val result = SUT.isEmailValid(email)
        val expected = false
        assertThat(result, `is`(expected))
    }

    @Test
    fun validateEmail_noTextBetweenAtSignAndDotSign_falseReturned(){
        val email = "test@.com"
        val result = SUT.isEmailValid(email)
        val expected = false
        assertThat(result, `is`(expected))
    }

    @Test
    fun validateEmail_noTextAfterDotSign_falseReturned(){
        val email = "test@test."
        val result = SUT.isEmailValid(email)
        val expected = false
        assertThat(result, `is`(expected))
    }

    @Test
    fun validatePasswords_samePasswords_trueReturned(){
        val password = "testpassword123"
        val confirmPassword = "testpassword123"
        val result = SUT.arePasswordsAreSame(password, confirmPassword)
        val expected = true
        assertThat(result, `is`(expected))
    }

    @Test
    fun validatePasswords_emptyPasswords_trueReturned(){
        val password = ""
        val confirmPassword = ""
        val result = SUT.arePasswordsAreSame(password, confirmPassword)
        val expected = true
        assertThat(result, `is`(expected))
    }

    @Test
    fun validatePasswords_differentPasswords_trueReturned(){
        val password = "testpassword123"
        val confirmPassword = "testpassword1"
        val result = SUT.arePasswordsAreSame(password, confirmPassword)
        val expected = false
        assertThat(result, `is`(expected))
    }

    @Test
    fun validatePassword_validPassword_trueReturned(){
        val password = "testpassword123"
        val result = SUT.isPasswordValid(password)
        val expected = true
        assertThat(result, `is`(expected))
    }

    @Test
    fun validatePassword_empty_falseReturned(){
        val password = ""
        val result = SUT.isPasswordValid(password)
        val expected = false
        assertThat(result, `is`(expected))
    }

    @Test
    fun validatePassword_lessThanEightCharacters_falseReturned(){
        val password = "pass123"
        val result = SUT.isPasswordValid(password)
        val expected = false
        assertThat(result, `is`(expected))
    }

    @Test
    fun validatePassword_onlyCharacters_falseReturned(){
        val password = "testpassword"
        val result = SUT.isPasswordValid(password)
        val expected = false
        assertThat(result, `is`(expected))
    }

    @Test
    fun validatePassword_onlyDigits_falseReturned(){
        val password = "1234567890"
        val result = SUT.isPasswordValid(password)
        val expected = false
        assertThat(result, `is`(expected))
    }

    @Test
    fun encryptToMd5_normalText_correctMd5Returned(){
        val text = "testpassword"
        val result = SUT.toMd5(text)
        val expected = "e16b2ab8d12314bf4efbd6203906ea6c"
        assertThat(result, `is`(expected))
    }

    @Test
    fun encryptToMd5_empty_correctMd5Returned(){
        val text = ""
        val result = SUT.toMd5(text)
        val expected = "d41d8cd98f00b204e9800998ecf8427e"
        assertThat(result, `is`(expected))
    }

    @Test
    fun encryptToMd5_longText_correctMd5Returned(){
        val sb = StringBuilder()
        for(i in 0..10000){
            sb.append("testpassword")
        }
        val result = SUT.toMd5(sb.toString())
        val expected = "b6e8e6047faf968775a8edeae145ad91"
        assertThat(result, `is`(expected))
    }

    @Test
    fun encryptToMd5_textWithSpecialChars_correctMd5Returned(){
        val text = "~!@#%^&*()_=+;'/.,?"
        val result = SUT.toMd5(text)
        val expected = "022c0bff35aa3d145af80c1e05c71b1d"
        assertThat(result, `is`(expected))
    }

}