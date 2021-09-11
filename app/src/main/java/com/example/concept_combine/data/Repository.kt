package com.example.concept_combine.data

import com.example.concept_combine.data.network.Data
import com.example.concept_combine.data.network.FakeData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

import javax.inject.Inject

class Repository @Inject constructor(var api:FakeData) {

    fun showList(): Flow<List<Data>> = flow {
        emit(api.list)
    }


    fun filterType(type:String):Flow<List<Data>> = flow{
        emit(api.list.filter { it.type == type })

    }

    fun searchType(type:String):Flow<List<Data>> = flow {
        val resultList = ArrayList<Data>()
        for (name in api.list) {
            if (name.model.lowercase(Locale.getDefault()).contains(type.lowercase(Locale.getDefault())) ) {
                resultList.add(name)
            }
            emit(resultList)
        }
    }

}

