package com.joerakhimov.smartexpenses.screen.main.profile.model

import com.google.gson.annotations.SerializedName

data class UpdateProfileResponse(

    @field:SerializedName("status")
    val status: Int? = null,

    @field:SerializedName("message")
    val message: String? = null

)