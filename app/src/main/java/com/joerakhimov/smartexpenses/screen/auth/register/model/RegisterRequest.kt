package com.joerakhimov.smartexpenses.screen.auth.register.model

import com.google.gson.annotations.SerializedName

data class RegisterRequest(

	@field:SerializedName("forename")
	val forename: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("surname")
	val surname: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null

)