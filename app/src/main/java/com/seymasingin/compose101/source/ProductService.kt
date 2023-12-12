package com.seymasingin.compose101.source

import com.seymasingin.compose101.data.FoodsResponse
import retrofit2.Call
import retrofit2.http.GET

interface FoodService {

    @GET("yemekler/tumYemekleriGetir.php")
    fun getFoods(): Call<FoodsResponse>
}