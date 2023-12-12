package com.seymasingin.compose101.data

import com.google.gson.annotations.SerializedName

data class Foods (
    @SerializedName("yemek_id")
    var id : Int,

    @SerializedName("yemek_adi")
    var name: String,

    @SerializedName("yemek_resim_adi")
    var image : String,

    @SerializedName("yemek_fiyat")
    var price: Int
)