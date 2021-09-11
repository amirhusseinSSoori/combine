package com.example.concept_combine.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.concept_combine.R
import com.example.concept_combine.data.network.Data
import com.example.concept_combine.ui.theme.yellow
import com.example.concept_combine.util.Constance.classic
import com.example.concept_combine.util.Constance.column
import com.example.concept_combine.util.Constance.modern
import com.example.concept_combine.util.Constance.race
import com.example.concept_combine.util.Constance.row
import com.example.concept_combine.util.utilFont


@Composable
fun MainScreen(viewModel: MainViewModel) {
    viewModel._resultState.collectAsState().let { details ->
        Menu(
            enableMenu = details.value.enableMenu is VisibleMenu.Enable,
            disableMenu = { viewModel.event(AllEvent.EnableMenu(VisibleMenu.Disable)) },
            filterCar = { viewModel.event(AllEvent.FilterData(it)) })

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    CustomSearch(expanded ={ viewModel.event(AllEvent.EnableMenu(VisibleMenu.Enable)) } ,onNameChanged = {
                        viewModel.event(AllEvent.SearchByName(it))
                    })
                    Topic(text = stringResource(id = R.string.best_cars))
                    LazyList(data = details.value.modern, row)
                    Topic(text = stringResource(id = R.string.all_cars))
                    LazyList(data = details.value.car, column)
                }


    }
}

@Composable
fun Topic(text: String) {
    Text(
        modifier = Modifier.padding(15.dp), text = text, fontFamily = utilFont,
        fontWeight = FontWeight.ExtraLight
    )
}

@Composable
fun CustomSearch(expanded:()->Unit,onNameChanged: (String) -> Unit,) {
    var text by remember {
        mutableStateOf("")
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = yellow,
        elevation = 12.dp,
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
          ) {
            TextField(
                value = text,
                onValueChange = {
                    text =it
                    onNameChanged(it)
                                },
                singleLine = true,
                modifier = Modifier
                    .weight(8f),
                textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
//                        onExecuteSearch()
//                        keyboardController?.hide()
                    },
                ),
            )
            Image(
                painter = painterResource(R.drawable.ic_search),
                "content description",
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .padding(5.dp)
                    .weight(1f),
                contentScale = ContentScale.Crop,


                )
            Image(
                painter = painterResource(R.drawable.ic_filter),
                "content description",
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .padding(5.dp)
                    .weight(1f)
                    .clickable {
                        expanded()
                    },
                contentScale = ContentScale.Crop,



                )


        }

    }

}


@Composable
fun Menu(enableMenu: Boolean, disableMenu: () -> Unit, filterCar: (String) -> Unit) {
    DropdownMenu(
        offset = DpOffset(300.dp, (-1000).dp),
        expanded = enableMenu,
        onDismissRequest = { disableMenu() },
    ) {
        DropdownMenuItem(onClick = {
            filterCar(race)
            disableMenu()
        }) {
            Text(
                stringResource(id = R.string.race), fontFamily = utilFont,
                fontWeight = FontWeight.Normal
            )
        }
        Divider()
        DropdownMenuItem(onClick = {
            filterCar(modern)
            disableMenu()
        }) {

            Text(
                stringResource(id = R.string.modern), fontFamily = utilFont,
                fontWeight = FontWeight.Normal
            )
        }
        Divider()
        DropdownMenuItem(onClick = {
            filterCar(classic)
            disableMenu()
        }) {
            Text(
                stringResource(id = R.string.classic), fontFamily = utilFont,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
fun LazyList(data: List<Data>, type: String) {
    when (type) {
        column -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(data) {
                    ColumnItem(data = it)
                }
            }

        }
        row -> {
            LazyRow(
                modifier = Modifier
            ) {
                items(data) {
                    RowItem(data = it)
                }
            }
        }
    }

}


@Composable
fun CombineTopBar(expanded: () -> Unit) {
    TopAppBar(
        title = {
            Topic(text = stringResource(id = R.string.app_name))
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp,
        actions = {
            IconButton(onClick = { expanded() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = "Change theme button",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }
    )
}

@Composable
fun RowItem(data: Data) {
    Column(
        modifier = Modifier
            .height(130.dp)
            .fillMaxWidth()
            .padding(start = 20.dp)
    ) {
        Image(
            painter = painterResource(data.img),
            "content description",
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .width(150.dp),
            contentScale = ContentScale.Crop,


            )
    }

}

@Composable
fun ColumnItem(data: Data) {
    Row(
        modifier = Modifier
            .height(130.dp)
            .fillMaxWidth()
            .padding(start = 20.dp)

    ) {
        Image(
            painter = painterResource(data.img),
            "content description",
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .width(150.dp),
            contentScale = ContentScale.Crop,


            )
        Column(modifier = Modifier.padding(25.dp)) {
            Text(
                data.name, color = Color(0xFF0C0C0C), fontFamily = utilFont,
                fontWeight = FontWeight.Normal
            )
            Text(
                data.model, fontFamily = utilFont,
                fontWeight = FontWeight.Medium
            )
        }

    }


}

