package com.example.fit.ui.BottomBar.Home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asLiveData

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.fit.R
import com.example.fit.ui.BottomBar.BottomBarScreen

import com.example.fit.ui.Tab.TabItem
import com.example.fit.ui.Tab.Tabs
import com.example.fit.ui.Tab.TabsContent
import com.example.fit.ui.navigation.DetailScreen
import com.example.fit.viewmodel.CourseModel

import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.lifecycle.HiltViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun Home(navController: NavHostController){
   val tabs= listOf(
       TabItem.Course,
       TabItem.Program,
       TabItem.Recommended
   )
    val viewModel= hiltViewModel<CourseModel>()
 Log.d("viewmodel",viewModel.toString())
   val pagerState= rememberPagerState()
 val visibleTopBottomBar = viewModel.visibleBottomTopBar

Log.d("visible",visibleTopBottomBar.value.toString())
  Scaffold(
      bottomBar = {if(visibleTopBottomBar.value!!)BottomBar(navController = navController)},
      topBar = { if(visibleTopBottomBar.value!!)HomeTopBar(modifier = Modifier, url = "#", title = "For you")}
  , content ={paddingValues ->
          Column(modifier = Modifier
              .fillMaxSize()
              .padding(
                  start = 0.dp,
                  bottom = paddingValues.calculateBottomPadding(),
                  top = paddingValues.calculateTopPadding(),
                  end = 0.dp
              )) {
              if(visibleTopBottomBar.value!!)Tabs(tabs=tabs,pagerState=pagerState)
              TabsContent(tabs=tabs,pagerState=pagerState)

          }
      }
  )
}



@Composable
fun HomeTopBar(modifier:Modifier,url:String,title:String){

    val painter:Painter = rememberAsyncImagePainter(
    model=ImageRequest.Builder(LocalContext.current).data(url).build(),
    contentScale = ContentScale.Crop,
        )
    TopAppBar() {
     Image(painter = painter, contentDescription ="",
        modifier = modifier
            .size(44.dp)
            .clip(CircleShape)
     )
     Text(text = title)

     Icon(painter = painterResource(id = R.drawable.ic_baseline_notifications_none_24), contentDescription ="notification" )
   }
}

@Composable
fun BottomBar(navController: NavHostController)
{
    val screens= listOf(
        BottomBarScreen.Home,
        BottomBarScreen.WorkOut,
        BottomBarScreen.Advice,
        BottomBarScreen.Meals,
        BottomBarScreen.Friends
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if(bottomBarDestination)
    {
        BottomNavigation(modifier = Modifier
            .graphicsLayer {
                clip = true
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            }
            .wrapContentHeight()){
            screens.forEach{screen->AddItem(screen = screen, currentDestination =currentDestination , navController =navController )}
        }
    }

}
@Composable
fun RowScope.AddItem(screen: BottomBarScreen,
                     currentDestination: NavDestination?,
                     navController: NavHostController
)
{
    BottomNavigationItem(
        label={Text(text = screen.title, fontSize = 10.sp)},
        icon={ Icon(painter = painterResource(id = screen.icon), modifier = Modifier.size(20.dp),
            contentDescription ="navigation icon" )},
        selected =currentDestination?.hierarchy?.any{it.route==screen.route} == true
        , unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled)
        , onClick = { navController.navigate(screen.route) {
            popUpTo(navController.graph.findStartDestination().id)
            launchSingleTop=true
        } })

}
