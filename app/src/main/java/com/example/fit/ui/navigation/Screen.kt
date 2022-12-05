package com.example.fit.ui.navigation
const val AUTHENTICATION_ROUTE="authentication"
const val ROOT_ROUTER="root"
const val PREPARE_ROUTER="prepare"
sealed class Screen(val router:String)
{
    object Login: Screen(router="loginform")
    object SignUp: Screen(router="signform")
    object WeightHeight: Screen(router = "weightheight")
    object TimeWorkout: Screen(router="TimeWorkout")
    object LevelInfor: Screen(router = "LevelInfor")
    object  Video:Screen(router="Video")
}
