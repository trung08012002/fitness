package com.example.fit.ui.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.fit.ui.Login.Graph
import com.example.fit.ui.Login.loginscreen
import com.example.fit.ui.Login.signupscreen
import com.example.fit.ui.Tab.CourseScreen

fun NavGraphBuilder.authNavGraph(navController:NavController)
{
    navigation(startDestination = AuthScreen.Login.route,route= Graph.AUTHENTICATION)
    {
        composable(route= AuthScreen.Login.route)
        {

            loginscreen(navController = navController)
        }
        composable(route= AuthScreen.SignUp.route)
        {
            signupscreen(navController)
        }

    }


}


//fun NavGraphBuilder.detailsNavGraph(navController: NavController)
//{
//    navigation(
//        route = Graph.DETAIL,
//        startDestination = DetailsScreen.
//    )
//}

sealed class AuthScreen(val route:String)
{
    object Login:AuthScreen(route="LOGIN")
    object SignUp:AuthScreen(route="SIGN_UP")
    object Forgot:AuthScreen(route="FORGOT")
}