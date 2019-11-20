package com.joerakhimov.smartexpenses.screen.main.addexpense

import com.joerakhimov.smartexpenses.screen.auth.register.AuthModel
import org.hamcrest.core.Is
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ExpensesModelTest{

    private lateinit var SUT: ExpensesModel

    @Before
    fun setUp() {
        SUT = ExpensesModel()
    }

    @Test
    fun validateName_validName_trueReturned(){
        val name = "Test name"
        val result = SUT.isNameValid(name)
        val expected = true
        assertThat(result, Is.`is`(expected))
    }

    @Test
    fun validateName_emptyName_falseReturned(){
        val name = ""
        val result = SUT.isNameValid(name)
        val expected = false
        assertThat(result, Is.`is`(expected))
    }

    @Test
    fun validateName_nameWithSpace_falseReturned(){
        val name = " "
        val result = SUT.isNameValid(name)
        val expected = false
        assertThat(result, Is.`is`(expected))
    }

    @Test
    fun validateAmount_validAmount_trueReturned(){
        val amount = "1000"
        val result = SUT.isAmountValid(amount)
        val expected = true
        assertThat(result, Is.`is`(expected))
    }

    @Test
    fun validateAmount_emptyAmount_falseReturned(){
        val amount = ""
        val result = SUT.isAmountValid(amount)
        val expected = false
        assertThat(result, Is.`is`(expected))
    }

    @Test
    fun validateAmount_blankAmount_falseReturned(){
        val amount = " "
        val result = SUT.isAmountValid(amount)
        val expected = false
        assertThat(result, Is.`is`(expected))
    }

    @Test
    fun validateAmount_zeroAmount_falseReturned(){
        val amount = "0"
        val result = SUT.isAmountValid(amount)
        val expected = false
        assertThat(result, Is.`is`(expected))
    }

    @Test
    fun validateAmount_minusAmount_falseReturned(){
        val amount = "-1000"
        val result = SUT.isAmountValid(amount)
        val expected = false
        assertThat(result, Is.`is`(expected))
    }

    @Test
    fun validateAmount_amountWithChars_falseReturned(){
        val amount = "1000 HUF"
        val result = SUT.isAmountValid(amount)
        val expected = false
        assertThat(result, Is.`is`(expected))
    }

    @Test
    fun validateAmount_amountWithDecimalPlaces_trueReturned(){
        val amount = "1000.00"
        val result = SUT.isAmountValid(amount)
        val expected = true
        assertThat(result, Is.`is`(expected))
    }

    @Test
    fun validateCategory_minCategoryId_trueReturned(){
        val categoryId = 0
        val result = SUT.isCategoryValid(categoryId)
        val expected = true
        assertThat(result, Is.`is`(expected))
    }

    @Test
    fun validateCategory_maxCategoryId_trueReturned(){
        val categoryId = 8
        val result = SUT.isCategoryValid(categoryId)
        val expected = true
        assertThat(result, Is.`is`(expected))
    }

    @Test
    fun validateCategory_invalidCategoryId_falseReturned(){
        val categoryId = 11
        val result = SUT.isCategoryValid(categoryId)
        val expected = false
        assertThat(result, Is.`is`(expected))
    }

}