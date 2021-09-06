package com.example.concept_combine.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.concept_combine.data.Repository
import com.example.concept_combine.data.network.Data
import com.example.concept_combine.util.Constance.modern
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
        event(AllEvent.GetAllData)
        viewModelScope.launch {
            combine(carState, modernState, enableState) { car, modern, enableState ->
                KindOfBy(car, modern, enableState)
            }.collect {
                state.value = it
            }
        }

    }
    fun event(event: AllEvent) {
        when(event){
            is AllEvent.GetAllData->{
                onSubscriberCars()
                onSubscriberModernCars()
            }
            is AllEvent.FilterData->{
                onSubscriberFilterCars(event.type)
            }
            is AllEvent.SearchByName ->{
                onSubscriberSearchCars(event.name)
            }
            is AllEvent.EnableMenu ->{
                if(event.visibleMenu is VisibleMenu.Enable){
                    enableState.value = VisibleMenu.Enable
                }else{
                    enableState.value = VisibleMenu.Disable
                }
            }
        }

    }
    private fun onSubscriberCars() {
        repository.generateList().onEach {
            carState.value = it
        }.launchIn(viewModelScope)
    }
    private fun onSubscriberModernCars() {
        repository.filterType(modern).onEach {
            modernState.value = it
        }.launchIn(viewModelScope)
    }
    private fun onSubscriberSearchCars(name:String){
        repository.searchType(name).onEach {
            carState.value = it
        }.launchIn(viewModelScope)
    }
    private fun onSubscriberFilterCars(type: String) {
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


sealed class VisibleMenu() {
    object Enable : VisibleMenu()
    object Disable : VisibleMenu()
}

sealed class AllEvent() {
    object GetAllData : AllEvent()
    data class FilterData(var type:String) : AllEvent()
    data class SearchByName(var name:String): AllEvent()
    data class EnableMenu(var visibleMenu: VisibleMenu = VisibleMenu.Enable): AllEvent()

}
