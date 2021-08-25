package com.example.concept_combine.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
    var expanded by remember { mutableStateOf(false) }
    viewModel._state.collectAsState().let { details ->
        DropdownMenu(
            offset = DpOffset(300.dp, -1000.dp),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            DropdownMenuItem(onClick = {
                viewModel.onCollectFilter("race")
                expanded = false
            }) {
                Text("Race", fontFamily = utilFont,
                    fontWeight = FontWeight.Normal)
            }
            Divider()
            DropdownMenuItem(onClick = {
                viewModel.onCollectFilter("modern")
                expanded = false
            }) {

                Text("Modern", fontFamily = utilFont,
                    fontWeight = FontWeight.Normal)
            }
            Divider()
            DropdownMenuItem(onClick = {
                viewModel.onCollectFilter("classic")
                expanded = false
            }) {
                Text("Classic", fontFamily = utilFont,
                    fontWeight = FontWeight.Normal)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            SelectButton(expanded = { expanded = true })

            LazyColumnList(details.value.car)
        }

    }
}



@Composable
fun LazyColumnList(data: List<Data>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(data) {
            ColumnItem(data = it)
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

