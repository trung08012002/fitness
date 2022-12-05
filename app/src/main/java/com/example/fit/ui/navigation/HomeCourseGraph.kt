package com.example.fit.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fit.Media.Video
import com.example.fit.ui.Login.Graph
import com.example.fit.ui.Tab.CourseScreen



@Composable
fun HomeCourseGraph()
{
    val navController= rememberNavController()
    NavHost(navController = navController,
        route= Graph.DETAIL_HOME,
        startDestination = DetailScreen.COURSES.route )
    {
        composable(route=DetailScreen.COURSES.route)
        {
            CourseScreen(navController)
        }
        composable(route=DetailScreen.VIDEO.route)
        {
            Video(navController = navController, context = LocalContext.current)
        }
    }
}
sealed class DetailScreen(val route:String){
    object COURSES:DetailScreen("COURSES")
    object  VIDEO:DetailScreen("VIDEO")
    object RECOMMENDED:DetailScreen("RECOMMENDED")
    object PROGRAM:DetailScreen("PROGRAM")
}