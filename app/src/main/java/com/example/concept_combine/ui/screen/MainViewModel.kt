package com.example.concept_combine.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.concept_combine.data.Repository
import com.example.concept_combine.data.network.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var repository: Repository):ViewModel() {


    val data = MutableStateFlow<List<Data>>(emptyList())
    val _data = data.asStateFlow()




    fun onCollect(){
        repository.generateList().onEach { data
            data.value=it
        }.launchIn(viewModelScope)
    }





}