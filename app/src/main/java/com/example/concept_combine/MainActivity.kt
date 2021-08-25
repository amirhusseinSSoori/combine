package com.example.concept_combine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.concept_combine.ui.main.MainScree
import com.example.concept_combine.ui.main.MainViewModel
import com.example.concept_combine.ui.theme.Concept_CombineTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Concept_CombineTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val viewModel: MainViewModel = viewModel()
                    MainScree(viewModel)
                }
            }
        }
    }
}





