package com.joerakhimov.smartexpenses.screen.auth.register.model

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("access_token")
	val accessToken: String? = null,

	@field:SerializedName("refresh_token")
	val refreshToken: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null

)