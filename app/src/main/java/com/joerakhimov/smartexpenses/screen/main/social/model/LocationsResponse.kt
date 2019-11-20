package com.joerakhimov.smartexpenses.screen.main.social.model

import com.google.gson.annotations.SerializedName

data class LocationsResponse(

	@field:SerializedName("location")
	val location: List<LocationItem?>? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("message")
	val message: Int? = null

)