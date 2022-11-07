package com.example.fit


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.fit.ui.Login.loginscreen
import com.example.fit.ui.Login.setupNavGraph
import com.example.fit.ui.Prepare.drawLine
import com.example.fit.ui.Screen

import com.example.fit.ui.theme.FitTheme
import com.example.fit.viewmodel.ModelLogin

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


