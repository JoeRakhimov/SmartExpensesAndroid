package com.joerakhimov.smartexpenses.screen.main.social.model

import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName
import com.google.maps.android.clustering.ClusterItem

data class LocationItem(

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("categoryID")
	val categoryID: Int? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null

): ClusterItem {
	override fun getPosition() = LatLng(latitude!!, longitude!!)
}