package com.example.concept_combine

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.concept_combine.ui.screen.MainViewModel
import com.example.concept_combine.ui.theme.Concept_CombineTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Concept_CombineTheme {
                val viewModel: MainViewModel  = viewModel()
                Surface(color = MaterialTheme.colors.background) {

                   val data by viewModel._data.collectAsState()

                    data?.let {

                   LazyColumn{
                       items(it) {
                          LazyList(img = it.img)
                       }
                   }



                    }


                    viewModel.onCollect()
                }
            }
        }
    }
}

@Composable
fun LazyList(img:Int){
    Column(modifier = Modifier.fillMaxSize()) {

        Image(painterResource(img),"content description")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Concept_CombineTheme {
        Greeting("Android")
    }
}