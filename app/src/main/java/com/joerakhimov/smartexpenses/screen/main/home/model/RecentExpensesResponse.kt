package com.joerakhimov.smartexpenses.screen.main.home.model

import com.google.gson.annotations.SerializedName

data class RecentExpensesResponse(

	@field:SerializedName("expenses")
	val expenses: Expenses? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("message")
	val message: Int? = null

)