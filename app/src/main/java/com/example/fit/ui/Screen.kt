package com.example.fit.ui

sealed class Screen(val router:String)
{
    object Login:Screen(router="loginform")
    object SignUp:Screen(router="signform")
}
