package com.joerakhimov.smartexpenses.screen.main.profile.model

import com.google.gson.annotations.SerializedName

data class ProfileInfo(

	@field:SerializedName("total_spendings")
	val totalSpendings: Double? = null,

	@field:SerializedName("color")
	var color: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("terms_and_conditions_url")
	val termsAndConditionsUrl: String? = null,

	@field:SerializedName("privacy_url")
	val privacyUrl: String? = null,

	@field:SerializedName("num_latest_spendings")
	val numLatestSpendings: Int? = null,

	@field:SerializedName("notifications")
	val notifications: Boolean? = null

)