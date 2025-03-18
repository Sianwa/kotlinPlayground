package com.example.kotlinplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onClickContinue
        ) {
            Text("Continue")
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier){
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Surface(modifier){
        if(shouldShowOnboarding){
            Onboarding(onClickContinue = {shouldShowOnboarding = false})
        }else{
            Greetings()
        }
    }
}

@Composable
fun Greetings(modifier: Modifier = Modifier,names: List<String> = listOf("Android", "Compose")){
    Column(modifier = modifier.padding(vertical = 4.dp)) {
        for(name in names){
         Greeting(name = name)
      }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    val extraPadding = if(expanded) 48.dp else 0.dp

    Surface(color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        Column(modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = extraPadding)) {
            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()){
                Text(
                    text = "Welcome to $name!",
                )
                Spacer(modifier = modifier.width(24.dp))
                ElevatedButton(onClick = {
                    expanded = !expanded;
                }) {
                    Text(if(expanded) "Show Less" else "Show More")
                }
            }
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