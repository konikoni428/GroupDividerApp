package com.masahiro.groupdevider.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    var nameDatasets: Map<Int, List<String>> = mapOf()

    fun getUsers(index: Int): List<String>{
        return nameDatasets[index-1] ?: listOf()
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}