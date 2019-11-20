package com.joerakhimov.smartexpenses.screen.main.addexpense

class ExpensesModel {

    fun isNameValid(name: String) = name.isNotBlank()

    fun isAmountValid(amount: String): Boolean {

        if(amount.isEmpty()) return false

        var amountDouble: Double = 0.0
        try {
            amountDouble = amount.toDouble()
        } catch (e: Exception) {
            return false
        }

        if(amountDouble<=0) return false

        return true

    }

    fun isCategoryValid(category: Int) = category in 0..8

}