package com.seymasingin.compose101.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FoodsResponse(
    @SerializedName("yemekler")
    @Expose
    val foods: List<Foods>,

    @SerializedName("success")
    @Expose
    val success: Int,
)
