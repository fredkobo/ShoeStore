package com.udacity.shoestore.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {
    private var _shoeList: MutableList<Shoe> = mutableListOf()

    val shoeList: MutableLiveData<MutableList<Shoe>> by lazy {
        MutableLiveData<MutableList<Shoe>>()
    }

    fun addShoe(shoe: Shoe) {
        _shoeList.add(shoe)
        shoeList.value = _shoeList
    }
}