package com.joerakhimov.smartexpenses.screen.main.details.model

import com.google.gson.annotations.SerializedName
import com.joerakhimov.smartexpenses.screen.main.home.model.ExpensesItem

data class ExpenseResponse(

	@field:SerializedName("expenses")
	val expenses: ExpensesItem? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("message")
	val message: String? = null

)