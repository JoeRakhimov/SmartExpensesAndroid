package com.joerakhimov.smartexpenses.screen.main.profile.model

import com.google.gson.annotations.SerializedName

data class UpdateProfileRequest(

	@field:SerializedName("color")
	val color: String? = null,

	@field:SerializedName("num_latest_spendings")
	val numLatestSpendings: Int? = null

)