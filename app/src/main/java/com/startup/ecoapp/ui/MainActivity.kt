		package com.startup.ecoapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.startup.ecoapp.navigation.Navigation
import com.startup.ecoapp.ui.theme.EcoAppTheme

class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			EcoAppTheme {
				// A surface container using the 'background' color from the theme
				Surface(modifier = Modifier.fillMaxSize(), color = Color.Gray) {
					Navigation()
				}
			}
		}
	}
}