package com.seymasingin.compose101.data

import androidx.lifecycle.MutableLiveData
import com.seymasingin.compose101.source.ApiUtils
import com.seymasingin.compose101.source.FoodService
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class Repository() {

    var foodList= MutableLiveData<List<Foods>>()
    var foodService: FoodService

    init {
        foodService =ApiUtils.getFoods()
        foodList = MutableLiveData()
    }

    fun getFoods(): MutableLiveData<List<Foods>> {
        return foodList
    }

    fun getAllFoods() {
        foodService.getFoods().enqueue(object:Callback<FoodsResponse>{
            override fun onResponse(call: Call<FoodsResponse>, response: Response<FoodsResponse>) {
                val liste = response.body()?.foods
                foodList.value = liste
            }
            override fun onFailure(call: Call<FoodsResponse>?, t: Throwable?) {}
        })
    }
}