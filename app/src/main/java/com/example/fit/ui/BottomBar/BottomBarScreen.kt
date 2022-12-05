package com.example.fit.ui.BottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import com.example.fit.R

sealed class BottomBarScreen(
    val route:String,
    val title:String,
    val icon: Int
) {
    object Home : BottomBarScreen(
        route = "Home",
        title = "For you",
        icon = R.drawable.ic_baseline_account_circle_24
    )

    object WorkOut : BottomBarScreen(
        route = "Workouts",
        title = "Workouts",
        icon = R.drawable.exercise
    )

    object Advice : BottomBarScreen(
        route = "Advice",
        title = "Advice",
        icon = R.drawable.advice
    )

    object Meals : BottomBarScreen(
        route = "Meals",
        title = "Meals",
        icon = R.drawable.meal
    )

    object Friends : BottomBarScreen(
        route = "Friends",
        title = "Friends",
        icon = R.drawable.friendship
    )
}