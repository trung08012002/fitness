package com.example.fit.ui.Prepare


import android.util.Log
import android.widget.Button
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fit.R
import com.example.fit.ui.theme.FitTheme
import com.google.relay.compose.RowScopeInstanceImpl.align


@Composable
fun weightheight()
{
    val configuration=LocalConfiguration.current
    val screenHeight by remember {
        mutableStateOf(configuration.screenHeightDp)
    }
    val screenWidth by remember {
        mutableStateOf(configuration.screenWidthDp)
    }
    val widthdivide by remember {
        mutableStateOf(((screenWidth).toFloat()/39))
    }
    var chooseNumberweight by remember {
    mutableStateOf(100)
     }
    var chooseNumberheight by remember {
        mutableStateOf(100)
    }

    var stage by remember {
        mutableStateOf(2)
    }
    val questionWeight by remember {
        mutableStateOf("What's your weight?")
    }
    val questionHeight by remember {
        mutableStateOf("What's your height?")
    }
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)) {
            IconButton(onClick = {}, modifier = Modifier.weight(0.1f)) {
               Icon(painterResource(R.drawable.arrowback100) , contentDescription ="arrowback" )
            }
            Row(modifier = Modifier
                .weight(0.9f, true)
                .fillMaxHeight(),
                horizontalArrangement = Arrangement.Center,
             verticalAlignment = Alignment.CenterVertically
                )
            {
                (1..6).forEach {i->
                    if(i<stage)
                    {
                        Spacer(modifier = Modifier
                            .height(3.dp)
                            .width(20.dp)
                            .background(color = Color.Black, shape = RoundedCornerShape(5.dp)))
                    }
                    else if(i==stage)
                    {
                        Spacer(modifier = Modifier
                            .height(3.dp)
                            .width(60.dp)
                            .background(color = Color.Black, shape = RoundedCornerShape(5.dp)))
                    }
                    else
                    {
                        Spacer(modifier = Modifier
                            .height(3.dp)
                            .width(20.dp)
                            .background(color = Color.Gray, shape = RoundedCornerShape(5.dp)))
                    }

                }

            }
        }
        Spacer(modifier = Modifier.height(100.dp))
        Text("Step 2", color = Color.Gray, fontSize = 15.sp)
        weightInfor(question = questionWeight,items = (100..250).toList(), heightLine =20f , heightLineMiddle =25f,widthdivide=widthdivide, onChooseNumberChange = {newNumber->chooseNumberweight=newNumber})

        heightInfor(
            question = questionHeight,
            items = (100..250).toList(),
            heightLine = 20f,
            heightLineMiddle = 25f,
            widthdivide = widthdivide,
            onChooseNumberChange = {newNumber->chooseNumberheight=newNumber}
        )
        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(0.8f), shape = AbsoluteRoundedCornerShape(50, 100, 10, 50)) {
         Text("Next", color = Color.White)
        }
    }

}

@Composable
fun weightInfor(question:String,items: List<Int>,heightLine: Float,heightLineMiddle: Float,widthdivide:Float,onChooseNumberChange:(Int)->Unit) {
    var numberLine by remember{ mutableStateOf(5) }

    val listState = rememberLazyListState()
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp), horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = question, color = Color.Black, fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(30.dp))
        Box(modifier =Modifier.fillMaxWidth())
        {
            LazyRow(state = listState,horizontalArrangement = Arrangement.spacedBy(widthdivide.dp)) { // here
                items(items=items,key={it})
                {
                    drawLine(number = it.toString(), heightLine = heightLine, heightLineMiddle = heightLineMiddle, numberLine = numberLine,widthdivide=widthdivide)

                }
            }
            Icon(painterResource(id = R.drawable.arrow)  , contentDescription ="Arrow",modifier= Modifier
                .align(Alignment.Center)
                .padding(4.dp)
                .size(40.dp),tint=Color.Yellow)
            LaunchedEffect(listState) {
                snapshotFlow { listState.firstVisibleItemIndex}
                    .collect {
                        onChooseNumberChange(it)
                    }
            }
        }

    }

}

@Composable
fun heightInfor(question:String,items: List<Int>,heightLine: Float,heightLineMiddle: Float,widthdivide:Float,onChooseNumberChange:(Int)->Unit) {
    var numberLine by remember{ mutableStateOf(5) }

    val listState = rememberLazyListState()
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp), horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = question, color = Color.Black, fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(30.dp))
        Box(modifier =Modifier.fillMaxWidth())
        {
            LazyRow(state = listState,horizontalArrangement = Arrangement.spacedBy(widthdivide.dp)) { // here
                items(items=items,key={it})
                {
                    drawLine(number = it.toString(), heightLine = heightLine, heightLineMiddle = heightLineMiddle, numberLine = numberLine,widthdivide=widthdivide)

                }
            }
            Icon(painterResource(id = R.drawable.arrow)  , contentDescription ="Arrow",modifier= Modifier
                .align(Alignment.Center)
                .padding(4.dp)
                .size(40.dp),tint=Color.Yellow)
            LaunchedEffect(listState) {
                snapshotFlow { listState.firstVisibleItemIndex}
                    .collect {
                        onChooseNumberChange(it)
                    }
            }
        }

    }
}

@Composable
fun drawLine(number: String, heightLine: Float, heightLineMiddle: Float, numberLine: Int,widthdivide:Float) {

    Column(modifier = Modifier.wrapContentWidth()) {
        Text(
            text = number,
            color = Color.Black, fontSize = 6.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(widthdivide.dp)
        )
        {
            (1..numberLine).forEach { i ->
                if (i != (numberLine / 2) + 1) {
                    Divider(
                        color = Color.Gray, thickness = .3.dp, modifier = Modifier
                            .height(heightLine.dp)
                            .width(1.dp)
                    )

                } else {
                    Divider(
                        color = Color.Gray,
                        thickness = .5.dp,
                        modifier = Modifier
                            .height(heightLineMiddle.dp)
                            .width(1.dp)
                    )


                }
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    FitTheme {
        weightheight()

    }
}