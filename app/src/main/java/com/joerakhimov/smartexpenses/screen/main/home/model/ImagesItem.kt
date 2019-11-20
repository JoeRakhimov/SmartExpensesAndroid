package com.joerakhimov.smartexpenses.screen.main.home.model

import com.google.gson.annotations.SerializedName

data class ImagesItem(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("websiteUrl")
	val websiteUrl: String? = null

)