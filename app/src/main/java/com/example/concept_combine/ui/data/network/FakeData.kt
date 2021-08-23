package com.example.concept_combine.ui.data.network

import com.example.concept_combine.R

var list = listOf(
    Data("Mercedes-Benz", "race", "C291",R.drawable.b_clsa),
    Data("Mercedes-Benz", "modern", "CLA",R.drawable.b_clsa),
    Data("Mercedes-Benz", "modern", "GLA",R.drawable.b_gla),
    Data("Mercedes-Benz", "classic", "123",R.drawable.b123),
    Data("Peugeot", "race", "205",R.drawable.p205),
    Data("Peugeot", "modern", "208",R.drawable.p208a),
    Data("Peugeot", "modern", "2008",R.drawable.p2008),
    Data("Peugeot", "classic", "504",R.drawable.p504),
    Data("bmw", "modern", "ix3", R.drawable.ix3),
    Data("bmw", "modern", "i4", R.drawable.i4),
    Data("bmw", "modern", "ix", R.drawable.ix),
    Data("ford", "classic", "mk2", R.drawable.mk2),
    Data("ford", "classic", "Scorpio", R.drawable.scorpio),
    Data("ford", "modern", "Focus", R.drawable.focus),
    Data("ford", "modern", "mustang", R.drawable.mustang),

    )

data class Data(var name: String, var type: String, var model: String, var img: Int)