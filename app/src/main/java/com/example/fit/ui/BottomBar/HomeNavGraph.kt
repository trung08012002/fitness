package com.example.fit.ui.BottomBar

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fit.ui.BottomBar.Home.Home
import com.example.fit.ui.BottomBar.WorkOut.WorkOut
import com.example.fit.ui.Login.Graph


@Composable
fun HomeNavGraph(navController: NavHostController)
{

    NavHost(navController = navController,
        route= Graph.HOME,
        startDestination = BottomBarScreen.Home.route )
    {

        composable(route=BottomBarScreen.Home.route)
     {
          Home(navController)
     }
        composable(route=BottomBarScreen.WorkOut.route)
        {
            WorkOut(navController = navController)
        }
        composable(route=BottomBarScreen.Advice.route)
        {

        }

       
    }
}


