package com.github.tweeny.ui

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.github.tweeny.R
import com.github.tweeny.ui.theme.TweenyTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TweenyTheme {
                // A surface container using the 'background' color from the theme
                Column(
                        modifier = Modifier.fillMaxSize()
                  ) {
                    Greeting("Android")
                  }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TweenyTheme {
        // A surface container using the 'background' color from the theme
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Greeting("Android")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
    Text(text = "Hello $name!")
    val icon = BitmapFactory.decodeResource(
        LocalContext.current.resources,
        R.drawable.igallery
    )
    FlagImage(imageType = DrawableResource(R.drawable.igallery))
    FlagImage(imageType = BitmapResource(icon))
}
