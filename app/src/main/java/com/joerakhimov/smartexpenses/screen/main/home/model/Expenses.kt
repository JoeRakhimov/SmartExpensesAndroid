package com.joerakhimov.smartexpenses.screen.main.home.model

import com.google.gson.annotations.SerializedName

data class Expenses(

	@field:SerializedName("images")
	val images: List<ImagesItem?>? = null,

	@field:SerializedName("expenses")
	val expenses: List<ExpensesItem?>? = null

)