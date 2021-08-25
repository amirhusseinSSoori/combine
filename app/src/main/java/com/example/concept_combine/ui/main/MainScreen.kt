package com.example.concept_combine.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.concept_combine.data.network.Data
import com.example.concept_combine.util.utilFont


@Composable
fun MainScree(viewModel: MainViewModel) {
    viewModel._state.collectAsState().let { details ->
        Menu(
            enableMenu = details.value.enableMenu is VisibleMenu.Enable,
            disableMenu = { viewModel.disableMenu() },
            filterCar = viewModel::onCollectFilter
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            SelectButton(expanded = { viewModel.enableMenu()})
            Topic(text = "Best Of Car")
            LazyList(data = details.value.modern,"Row")
            Topic(text = "List Of Car")
            LazyList(data = details.value.car, "Column")
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
fun Menu(enableMenu: Boolean, disableMenu: () -> Unit, filterCar: (String) -> Unit) {
    DropdownMenu(
        offset = DpOffset(300.dp, (-1000).dp),
        expanded = enableMenu,
        onDismissRequest = { disableMenu() },
    ) {
        DropdownMenuItem(onClick = {
            filterCar("race")
            disableMenu()
        }) {
            Text(
                "Race", fontFamily = utilFont,
                fontWeight = FontWeight.Normal
            )
        }
        Divider()
        DropdownMenuItem(onClick = {
            filterCar("modern")
            disableMenu()
        }) {

            Text(
                "Modern", fontFamily = utilFont,
                fontWeight = FontWeight.Normal
            )
        }
        Divider()
        DropdownMenuItem(onClick = {
            filterCar("classic")
            disableMenu()
        }) {
            Text(
                "Classic", fontFamily = utilFont,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
fun LazyList(data: List<Data>, type:String) {
    when(type) {
        "Column" -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(data) {
                    ColumnItem(data = it)
                }
            }

        }
        "Row" -> {
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
fun SelectButton(expanded: () -> Unit) {
    Button(
        onClick = {
            expanded()
        },
        modifier = Modifier
            .fillMaxWidth(),
    ) {

    }
}

@Composable
fun RowItem(data: Data){
    Column(   modifier = Modifier
        .height(130.dp)
        .fillMaxWidth()
        .padding(start = 20.dp)) {
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

