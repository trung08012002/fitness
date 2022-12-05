package com.example.fit.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.fit.ui.Prepare.LevelInfor
import com.example.fit.ui.Prepare.TimeWorkout
import com.example.fit.ui.Prepare.weightheight

fun NavGraphBuilder.PrePareNavGraph(navController: NavController){
    navigation(startDestination = Screen.WeightHeight.router, route = PREPARE_ROUTER)
    {
        composable(route= Screen.WeightHeight.router)
        {
            weightheight(navController = navController)
        }
        composable(route = Screen.LevelInfor.router)
        {
            LevelInfor(navController =navController)
        }
        composable(route = Screen.TimeWorkout.router)
        {
            TimeWorkout(navController = navController)
        }
    }

}