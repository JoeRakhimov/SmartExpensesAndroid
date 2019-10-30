package com.joerakhimov.smartexpenses.screen.main.expenses

data class Expense(
    val title: String,
    val icon: Int,
    val address: String,
    val amount: Double,
    val currency: String
)