package com.joerakhimov.smartexpenses.screen.main.profile.model

import com.google.gson.annotations.SerializedName

data class PhotoRequest(

	@field:SerializedName("image")
	val image: String? = null

)