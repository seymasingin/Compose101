package com.seymasingin.compose101

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seymasingin.compose101.data.Foods
import com.seymasingin.compose101.data.Repository

class MainActivityViewModel : ViewModel() {
    var foodList = MutableLiveData<List<Foods>>()
    var repo = Repository()

    init {
        getFoods()
        foodList = repo.getFoods()
    }

    fun getFoods(){
        repo.getAllFoods()
    }
}

