package com.example.concept_combine

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.concept_combine.ui.screen.MainViewModel
import com.example.concept_combine.ui.theme.Concept_CombineTheme
import com.example.concept_combine.ui.theme.Purple200
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = viewModel()
//            val data by viewModel._data.collectAsState()
            val data by viewModel.value
            Concept_CombineTheme {

                viewModel.onCollect()
                Surface(color = MaterialTheme.colors.background) {

                    Column() {
                        Button(onClick = {
                            viewModel.onCollectFilter("race")
                        },modifier = Modifier.fillMaxWidth()) {

                        }
                        Button(onClick = {
                            viewModel.onCollectFilter("classic")
                        },modifier = Modifier.fillMaxWidth()) {

                        }
                        data?.let {

                            LazyColumn {
                                items(it) {
                                    LazyList(img = it.img)
                                }
                            }
                        }

                    }









                }
            }
        }
    }
}

@Composable
fun LazyList(img: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .height(200.dp)
    ) {
        Card {
        Image(
            painter = painterResource(img),
            "content description",
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxSize()
                .padding(10.dp),
            contentScale = ContentScale.Crop,
        )
    }
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