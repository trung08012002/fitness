package com.example.fit.ui.Tab

import androidx.compose.runtime.Composable
import com.example.fit.ui.navigation.HomeCourseGraph


sealed class TabItem (var title:String,val screen:@Composable () ->Unit)
{
    object Course:TabItem("Course", { HomeCourseGraph()})
    object Program:TabItem("Program",{ ProgramScreen()})
    object Recommended:TabItem("Recommended",{ RecommendedScreen()})
}