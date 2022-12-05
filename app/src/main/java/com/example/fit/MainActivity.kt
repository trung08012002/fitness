package com.example.fit


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.fit.ui.Login.setupNavGraph

import com.example.fit.ui.theme.FitTheme

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FitTheme {               // A surface container using the 'background' color from the theme
               Surface(modifier = Modifier.fillMaxSize()) {
                   val navController= rememberNavController()
                   setupNavGraph(navController = navController)
               }


            }
        }
    }
}


