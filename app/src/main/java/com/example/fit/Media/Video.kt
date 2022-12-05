package com.example.fit.Media

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.request.Disposable
import com.example.fit.R
import com.example.fit.data.StateApplication.Resource
import com.example.fit.ui.BottomBar.BottomBarScreen
import com.example.fit.ui.navigation.DetailScreen
import com.example.fit.viewmodel.CourseModel
import com.google.relay.compose.RowScopeInstanceImpl.align


@Composable
fun Video(navController: NavController,context:Context)
{
    val viewModel= hiltViewModel<CourseModel>()
    Log.d("viewmodel",viewModel.toString())
    val currentcourse=viewModel.currentcourse
    val activity= context as Activity
    var lifecycle by remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }
    val lifecycleOwner= LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner)
    {
        val observer = LifecycleEventObserver{_,event->
            lifecycle=event
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    activity.requestedOrientation=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    Box(modifier = Modifier.fillMaxSize())
    {
        Button(onClick = {
            viewModel.makeBottomTopBarVisible()
            navController.navigate(DetailScreen.COURSES.route) {
            popUpTo(navController.graph.findStartDestination().id)
            launchSingleTop = true
        }},
        modifier = Modifier
            .height(50.dp)
            .width(50.dp)
            .align(Alignment.TopStart)
            .background(Color.White)
            .padding(10.dp)
            .zIndex(1f)
        ) {
            Icon(painter = painterResource(id = R.drawable.arrowback100), contentDescription ="" )
        }
        AndroidView(factory = {context ->
            PlayerView(context).apply {

                resizeMode=AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                player=viewModel.player

                when(currentcourse?.value)
                {
                    is Resource.Success-> {
                        viewModel.getcurrentvideo()

                        viewModel.playVideo((currentcourse.value as Resource.Success<EntityCourse>).result.VideoUrl.toUri())

                    }
                    is Resource.Loading->Unit
                    is Resource.Failure-> Log.d("errorvideo",
                        (currentcourse.value as Resource.Failure).message)
                    else->Unit
                }
            }
        }
        , update = {
            when(lifecycle)
            {
                Lifecycle.Event.ON_PAUSE->{
                    it.onPause()
                    it.player?.pause()

                }
                Lifecycle.Event.ON_RESUME->{
                    it.onResume()

                }
                else ->Unit
            }
            }
        )
    }
}
