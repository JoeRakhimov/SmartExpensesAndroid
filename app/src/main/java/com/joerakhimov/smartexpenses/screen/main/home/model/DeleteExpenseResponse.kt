package com.joerakhimov.smartexpenses.screen.main.home.model

import com.google.gson.annotations.SerializedName

class DeleteExpenseResponse(

    @field:SerializedName("status")
    val status: Int? = null,

    @field:SerializedName("message")
    val message: String? = null

)