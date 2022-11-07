package com.example.fit.ui.Login

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fit.ui.Prepare.weightheight
import com.example.fit.ui.Screen


@Composable
fun setupNavGraph(navController:NavHostController)
{
    NavHost(navController = navController, startDestination = Screen.Login.router) {
       composable(route= Screen.Login.router)
       {
//           weightheight()
          loginscreen(navController = navController)
       }
        composable(route= Screen.SignUp.router)
        {
            signupscreen(navController)
        }
    }
}