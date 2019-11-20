package com.joerakhimov.smartexpenses.screen.main.home.model

import com.google.gson.annotations.SerializedName

data class ExpensesItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("private")
	val jsonMemberPrivate: Boolean? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("valueUSD")
	val valueUSD: Double? = null,

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("value")
	val value: Double? = null,

	@field:SerializedName("categoryID")
	val categoryID: Int? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null
)