package com.example.kotlinplayground.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlinplayground.R
import com.example.kotlinplayground.ui.theme.KotlinPlaygroundTheme

@Composable
fun ModifiedSearchBar(modifier: Modifier = Modifier) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        TextField(
            value = "",
            onValueChange = {},
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = 15.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = "")
            },
            placeholder = {
                Text(text = "Search here...", style = TextStyle(fontFamily = FontFamily.Monospace))
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface
            ),
        )
    }
}

@Composable
fun ExerciseRoutine(modifier: Modifier = Modifier, text: String){
    Surface(shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primary){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(24.dp)) {
            Image(painter = painterResource(
                id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
            )
            Text(text = text, style = TextStyle(fontFamily = FontFamily.Monospace))
        }
    }
}

@Composable
fun FavoriteCard(modifier: Modifier = Modifier, text: String){
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primary
        ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)
        ){
            Image(
                //colorFilter = ColorFilter.tint(color = Color.Green),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = ""
            )
            Text(
                text = text,
                style = TextStyle(fontFamily = FontFamily.Monospace)
            )
        }

    }

}

@Composable
fun ExerciseRow(modifier: Modifier = Modifier, exercise: List<String>){
    LazyRow(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),horizontalArrangement = Arrangement.spacedBy(8.dp)){
        items(items = exercise){ item -> ExerciseRoutine(text = "Example $item");
        }
    }
}

@Composable
fun FavoriteRow(modifier: Modifier = Modifier, favs: List<String>){
    LazyHorizontalGrid(rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.height(168.dp)
        ){
        items(favs){ item -> FavoriteCard(text = "Example $item");
        }
    }
}


@Preview(showBackground = true,backgroundColor = 0xFFF5F0EE)
@Composable
fun Searchbar() {
    KotlinPlaygroundTheme {
        FavoriteRow(favs = mutableListOf("One", "Two", "Three", "Four"))
    }
}