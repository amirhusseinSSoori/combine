package com.example.concept_combine.data

import com.example.concept_combine.data.network.Data
import com.example.concept_combine.data.network.FakeData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class Repository @Inject constructor(var api:FakeData) {

    fun generateList(): Flow<List<Data>> = flow {
        emit(api.list)
    }


    fun filterType(typse:String):Flow<List<Data>> = flow{
        emit(api.list.filter { it.type == typse })
    }

}