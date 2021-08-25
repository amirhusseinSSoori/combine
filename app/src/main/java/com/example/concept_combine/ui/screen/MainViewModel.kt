package com.example.concept_combine.ui.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
class MainViewModel @Inject constructor(var repository: Repository) : ViewModel() {


    val data = MutableStateFlow<List<Data>>(emptyList())
    val _data = data.asStateFlow()


    val value: MutableState<List<Data>> = mutableStateOf(listOf())

    init {
        onCollectDetails()
    }


    private fun onCollectDetails() {
        repository.generateList().onEach {
            value.value = it
        }.launchIn(viewModelScope)
    }

    fun onCollectFilter(type: String) {
        repository.filterType(type).onEach {
            value.value = it
        }.launchIn(viewModelScope)
    }


}

data class ListState(var data: List<Data> = listOf())