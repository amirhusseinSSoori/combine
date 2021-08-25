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
class MainViewModel @Inject constructor(var repository: Repository):ViewModel() {


    private val carState = MutableStateFlow<List<Data>>(emptyList())
    val data2 = MutableStateFlow<List<Data>>(emptyList())

      private val state= MutableStateFlow(KindOfBy())
      val _state= state.asStateFlow()
    init {
         onCollect()
         viewModelScope.launch {
             combine(carState,data2){car,hgfa->
                 KindOfBy(car)
             }.collect {
                 state.value=it
             }
         }

     }

    private fun onCollect(){
        repository.generateList().onEach {
            carState.value = it
        }.launchIn(viewModelScope)
    }

    fun onCollectFilter(type:String){
        repository.filterType(type).onEach {
            carState.value = it
        }.launchIn(viewModelScope)
    }








}

data class KindOfBy(var car:List<Data> = listOf(), var b:List<Data> = listOf()){

}
