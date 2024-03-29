package com.joerakhimov.smartexpenses.screen.main.expenses.model

import com.google.gson.annotations.SerializedName
import com.joerakhimov.smartexpenses.screen.main.home.model.ExpensesItem

data class ExpensesResponse(

    @field:SerializedName("expenses")
    val expenses: List<ExpensesItem?>? = null,

    @field:SerializedName("status")
    val status: Int? = null,

    @field:SerializedName("message")
    val message: Int? = null

)