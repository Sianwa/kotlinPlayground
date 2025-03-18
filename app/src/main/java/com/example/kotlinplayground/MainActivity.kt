package com.example.kotlinplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinplayground.ui.theme.KotlinPlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinPlaygroundTheme {
                MyApp(
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@Composable
fun Onboarding(modifier: Modifier = Modifier, onClickContinue: () -> Unit){
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!",
            style = TextStyle(
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        )
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onClickContinue
        ) {
            Text("Continue", style = TextStyle(
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp))
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier){
    //Instead of using remember you can use rememberSaveable. This will save each state surviving configuration changes (such as rotations) and process death.
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier){
        if(shouldShowOnboarding){
            Onboarding(onClickContinue = {shouldShowOnboarding = false})
        }else{
            Greetings()
        }
    }
}

@Composable
fun Greetings(modifier: Modifier = Modifier,names: List<String> = List(100){"$it"}){
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names){ name ->
         Greeting(name = name)
      }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Card (colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        GreetingCardContent(name = name)
    }
}

@Composable
fun GreetingCardContent(name: String){
    var expanded by rememberSaveable { mutableStateOf(false) }

    Row(modifier = Modifier
        .padding(24.dp)
        .animateContentSize(
            animationSpec =
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )) {
        Column(modifier = Modifier
            .weight(1f)
            .padding(bottom = 12.dp)
        ) {
            Text(text = "Hello, ",
                style = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp))
            Text(text = name,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontFamily = FontFamily.Monospace
                )
            )
            if(expanded){
                Text(text = "We are happy to have you here....",
                    style = TextStyle(fontFamily = FontFamily.Monospace)
                )
            }
        }
        IconButton(
            onClick = { expanded = !expanded }
        ) {
            Icon(imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = if (expanded) {
                    "Show Less"
                } else {
                    "Show More"
                })
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinPlaygroundTheme {
        MyApp()
    }
}