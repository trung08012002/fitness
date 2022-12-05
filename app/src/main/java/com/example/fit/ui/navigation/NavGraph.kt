package com.example.fit.ui.Login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.platform.LocalContext

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fit.Media.Video
import com.example.fit.ui.BottomBar.MainScreen
import com.example.fit.ui.Prepare.LevelInfor
import com.example.fit.ui.Prepare.TimeWorkout
import com.example.fit.ui.Prepare.weightheight
import com.example.fit.ui.Tab.CourseScreen
import com.example.fit.ui.navigation.PrePareNavGraph
import com.example.fit.ui.navigation.ROOT_ROUTER
import com.example.fit.ui.navigation.Screen
import com.example.fit.ui.navigation.authNavGraph


@Composable
fun setupNavGraph(navController:NavHostController)
{
    NavHost(navController = navController, startDestination = Graph.HOME,
        route = Graph.ROOT
    ) {
//        composable(route= Screen.Login.router)
//        {
//            CourseScreen()
////            loginscreen(navController = navController)
//        }
//        composable(route= Screen.SignUp.router)
//        {
//            signupscreen(navController)
//        }
//        composable(route= Screen.WeightHeight.router)
//        {
//            weightheight(navController = navController)
//        }
//        composable(route = Screen.LevelInfor.router)
//        {
//            LevelInfor(navController =navController)
//        }
//        composable(route = Screen.TimeWorkout.router)
//        {
//            TimeWorkout(navController = navController)
//        }
        composable(route=Screen.Video.router)
        {
            Video(navController=navController, LocalContext.current)
        }
        authNavGraph(navController=navController)
        composable(route=Graph.HOME)
        {
            MainScreen()
        }
    }
}
object Graph{
    const val ROOT="root_graph"
    const val AUTHENTICATION="auth-graph"
    const val HOME="home_graph"
    const val DETAIL_HOME="detail_graph"
}