package com.joerakhimov.smartexpenses.screen.auth.register.model

import com.google.gson.annotations.SerializedName

data class RegisterRequest(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("email")
	val email: String? = null

)