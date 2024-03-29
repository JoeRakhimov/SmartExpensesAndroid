package com.joerakhimov.smartexpenses.screen.main.profile.model

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

    @field:SerializedName("profile")
    val profile: ProfileInfo? = null,

    @field:SerializedName("status")
    val status: Int? = null,

    @field:SerializedName("message")
    val message: String? = null

)