package com.example.concept_combine.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.concept_combine.data.Repository
import com.example.concept_combine.data.network.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var repository: Repository) : ViewModel() {


    private val carState = MutableStateFlow<List<Data>>(emptyList())
    private val modernState = MutableStateFlow<List<Data>>(emptyList())
    private val enableState = MutableStateFlow<VisibleMenu>(VisibleMenu.Disable)
    private val state = MutableStateFlow(KindOfBy())
    val _state = state.asStateFlow()

    init {
        onCollect()
        disableMenu()
        onCollectModern()
        viewModelScope.launch {
            combine(carState, modernState,enableState) { car, modern ,enableState->
                KindOfBy(car, modern,enableState)
            }.collect {
                state.value = it
            }
        }

    }

     fun enableMenu(){
         enableState.value=VisibleMenu.Enable
    }
    fun disableMenu(){
        enableState.value=VisibleMenu.Disable
    }
    private fun onCollect() {
        repository.generateList().onEach {
            carState.value = it

        }.launchIn(viewModelScope)

    }

    private fun onCollectModern() {
        repository.filterType("modern").onEach {
            modernState.value = it
        }.launchIn(viewModelScope)
    }

    fun onCollectFilter(type: String) {
        repository.filterType(type).onEach {
            carState.value = it
        }.launchIn(viewModelScope)
    }
}

data class KindOfBy(
    var car: List<Data> = listOf(),
    var modern: List<Data> = listOf(),
    var enableMenu: VisibleMenu = VisibleMenu.Disable
)


sealed class VisibleMenu(){
    object  Enable:VisibleMenu()
    object  Disable:VisibleMenu()
}
