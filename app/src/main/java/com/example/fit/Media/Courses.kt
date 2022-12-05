package com.example.fit.ui.Tab



import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.fit.Media.EntityCourse
import com.example.fit.Media.VideoUri
import com.example.fit.R
import com.example.fit.data.StateApplication.Resource
import com.example.fit.ui.Login.Graph
import com.example.fit.ui.navigation.DetailScreen
import com.example.fit.ui.navigation.Screen
import com.example.fit.ui.theme.FitTheme
import com.example.fit.viewmodel.CourseModel
import com.google.relay.compose.RowScopeInstanceImpl.align
import dagger.hilt.android.AndroidEntryPoint


@Composable
fun CourseScreen(navController: NavController)
{
    val  viewModel= hiltViewModel<CourseModel>()
    val activity= LocalContext.current as Activity
    activity.requestedOrientation= ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    val statevideoList = viewModel.videoList?.observeAsState()
    when(statevideoList?.value)
    {
        is Resource.Success->{
            Courses(Modifier, (statevideoList?.value as Resource.Success<List<EntityCourse>>).result,
                "Join exclusive courses","Designed by your favorite trainer",viewModel,navController)
        }
        is Resource.Loading->{

        }
        is Resource.Failure->{

        }
        else->{

        }
    }

}

@Composable
fun Courses(modifier: Modifier,listcourse: List<EntityCourse>,title:String,description:String,viewModel:CourseModel,navController: NavController)
{
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(3.dp)
        .wrapContentHeight()
        .clipScrollableContainer(orientation = Orientation.Horizontal))
    {
        Row(modifier = modifier
            .fillMaxWidth()
            .padding(0.dp, 3.dp)
            .wrapContentHeight()) {
            Column(modifier = modifier
                .weight(0.7f)
                .wrapContentHeight(),  verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(title,color= Color.Black, fontSize =18.sp, fontWeight = FontWeight.W800)
                Text(description, fontSize = 14.sp)
            }


            Button(onClick = { /*TODO*/ },modifier=modifier.weight(0.1f).height(50.dp)) {
                Icon(painter = painterResource(R.drawable.plus90), contentDescription ="plus icon", modifier = Modifier.size(40.dp))
            }
        }
        ScrollList1(listcourse,viewModel,navController)
    }

}


@Composable
fun  ScrollList(list:List<EntityCourse>,viewModel:CourseModel,navController: NavController)
{
    val state = rememberScrollState()
    LazyRow(modifier = Modifier
        .verticalScroll(state)
        .fillMaxWidth()
        .wrapContentHeight())
   {
    items(list.size){
        item-> AsyncImage(model = list[item].ImgUrl,
        contentDescription = "course gym",
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)

            .padding(10.dp)
            .clickable {
                viewModel.setCourseId(list[item].IdCourse)
                viewModel.makeBottomTopBarInvisible()
                navController.navigate(DetailScreen.VIDEO.route)
            })
    }
   }
}

//@Composable
//fun <T> AsyncImage(
//    load: suspend () -> T,
//    painterFor: @Composable (T) -> Painter,
//    contentDescription: String,
//    modifier: Modifier = Modifier,
//    contentScale: ContentScale = ContentScale.Fit,
//) {
//    val image: T? by produceState<T?>(null) {
//        value = withContext(Dispatchers.IO) {
//            try {
//                load()
//            } catch (e: IOException) {
//                // instead of printing to console, you can also write this to log,
//                // or show some error placeholder
//                e.printStackTrace()
//                null
//            }
//        }
//    }
//
//    if (image != null) {
//        Image(
//            painter = painterFor(image!!),
//            contentDescription = contentDescription,
//            contentScale = contentScale,
//            modifier = modifier.width(200.dp)
//        )
//    }
//}
//
//fun loadImageBitmap(filePath: String):ImageBitmap
//{
//    val sd = Environment. getExternalStorageDirectory()
//    var options: BitmapFactory.Options?
//    val path="${sd}/$filePath"
//    val image= File(path)
//    Log.d("filelon",image.exists().toString())
//    try{
//
//        val imgBitmap=BitmapFactory.decodeFile(image.absolutePath)
//        return imgBitmap.asImageBitmap()
//    }
//    catch(e:OutOfMemoryError)
//    {
//
//        options = BitmapFactory.Options()
//        options!!.inSampleSize = 2
//        return BitmapFactory.decodeFile(image.absolutePath,options).asImageBitmap()
//
//    }
//
//
//}

@Composable
fun  ScrollList1(list:List<EntityCourse>,viewModel:CourseModel,navController: NavController)
{
    val state = rememberScrollState()
    LazyVerticalGrid(
        columns=object :GridCells{
            override fun Density.calculateCrossAxisCellSizes(
                availableSize: Int,
                spacing: Int
            ): List<Int> {
                val firstColumn = (availableSize - spacing) * 1/2
                val secondColumn = availableSize - spacing - firstColumn
                return listOf(firstColumn, secondColumn)
            }
        },
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(24.dp)
    )
    {
        list.forEachIndexed{
                index,item->
            if(index==0)
            {
                item(key=index,span={ GridItemSpan(maxLineSpan) }, content = {AsyncImage(model = item.ImgUrl,
                    contentDescription = "course gym",
                    modifier = Modifier
                        .clickable {
                            viewModel.setCourseId(list[index].IdCourse)
                            viewModel.makeBottomTopBarInvisible()
                            navController.navigate(DetailScreen.VIDEO.route)
                        })})
            }
            else{
                item(key=index,span = { GridItemSpan(1)  }, content = {AsyncImage(model = item.ImgUrl,
                    contentDescription = "course gym",
                    modifier = Modifier
                        .clickable {
                            viewModel.setCourseId(list[index].IdCourse)
                            viewModel.makeBottomTopBarInvisible()
                            navController.navigate(DetailScreen.VIDEO.route)
                        })})
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController= rememberNavController()
    FitTheme {


    }
}
