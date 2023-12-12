package com.seymasingin.compose101.source

class ApiUtils {
    companion object{
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getFoods():FoodService{
            return RetrofitClient.getClient(BASE_URL).create(FoodService::class.java)
        }
    }
}