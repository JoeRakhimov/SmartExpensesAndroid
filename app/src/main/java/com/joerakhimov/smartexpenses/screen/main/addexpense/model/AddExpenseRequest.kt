package com.joerakhimov.smartexpenses.screen.main.addexpense.model

import com.google.gson.annotations.SerializedName

data class AddExpenseRequest(

	@field:SerializedName("private")
	val jsonMemberPrivate: Boolean? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("value")
	val value: Long? = null,

	@field:SerializedName("categoryID")
	val categoryID: Int? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null,

	@field:SerializedName("date")
	val date: Long? = null

)