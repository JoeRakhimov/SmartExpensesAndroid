package com.joerakhimov.smartexpenses.screen.main.addexpense.model

import com.google.gson.annotations.SerializedName
import com.joerakhimov.smartexpenses.screen.main.home.model.ExpensesItem

class AddExpenseResponse(

    @field:SerializedName("expense")
    val expenses: ExpensesItem? = null,

    @field:SerializedName("status")
    val status: Int? = null,

    @field:SerializedName("message")
    val message: String? = null

)