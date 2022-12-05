package com.example.fit.ui.Prepare

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fit.ui.theme.FitTheme
import com.example.fit.ui.theme.LightGrey
import com.example.fit.viewmodel.InforUserModel
import com.example.fit.viewmodel.ModelLogin

@Composable
fun TimeWorkout(navController: NavController)
{
    val viewModel = hiltViewModel<InforUserModel>()
    var stage by remember {
        mutableStateOf(3)
    }
    var question by remember {
        mutableStateOf("How experienced are you lifting weight ?")
    }
   var times :MutableList<String> by remember {
       mutableStateOf(mutableListOf("3 days a week","4 days a week","5 days a week","6 days a week","Every day"))
   }
    var choose = remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        stepInfor(stage = stage)
        Spacer(modifier = Modifier.height(30.dp))
        Text("Step "+stage.toString(), color = Color.Gray, fontSize = 15.sp)
        Text(text = question, color = Color.Black, fontSize = 20.sp,textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(15.dp))
        for(value in times)
        {
            boxTime(choose=choose.value,time=value,chooseOnClick={newValue-> choose.value=newValue})
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {viewModel.updateSingleFieldPrepare("timeworkout",choose)}, modifier = Modifier.fillMaxWidth(0.8f), shape = AbsoluteRoundedCornerShape(50, 100, 10, 50)) {
            Text("Next", color = Color.White)
        }
    }
}

@Composable
fun boxTime(choose:String,time:String,chooseOnClick:(String)->Unit)
{
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(vertical = 10.dp)
        .clickable {
            chooseOnClick(time)
        }
        .let {
            if (choose === time) it.background(
                color = Color.Yellow,
                shape = RoundedCornerShape(10.dp)
            )
            else it.background(
                color = MaterialTheme.colorScheme.LightGrey,
                shape = RoundedCornerShape(10.dp)
            )
        }
        .padding(10.dp, 20.dp)
    ) {
        Text(text = time,color=Color.Black, fontSize = 20.sp)

    }
}
