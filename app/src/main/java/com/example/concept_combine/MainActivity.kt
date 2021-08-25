package com.example.concept_combine

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.widget.PopupMenu
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
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
            var expanded by remember { mutableStateOf(false) }

            Concept_CombineTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxSize()

                    ) {
                        val (btnPop, listCar,pop) = createRefs()

                        DropdownMenu(
                            offset = DpOffset(300.dp, (-2000).dp),
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            ) {
                            DropdownMenuItem(onClick = {
                                viewModel.onCollectFilter("race")
                                expanded = false
                            }) {

                                Text("Race")
                            }
                            DropdownMenuItem(onClick = {
                                viewModel.onCollectFilter("modern")
                                expanded = false
                            }) {

                                Text("Modern")
                            }
                            Divider()
                            DropdownMenuItem(onClick = {
                                viewModel.onCollectFilter("classic")
                                expanded = false
                            }) {
                                Text("Classic")
                            }
                        }

                        Button(
                            onClick = {
                                expanded = true

                            },
                            modifier = Modifier
                                .constrainAs(btnPop) {
                                    top.linkTo(parent.top)
                                }
                                .fillMaxWidth(),
                        ) {

                        }

                        data.let {
                            LazyColumn(modifier = Modifier
                                .constrainAs(listCar) {
                                    top.linkTo(btnPop.bottom, margin = 3.dp)
                                }
                                .fillMaxSize()) {
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




