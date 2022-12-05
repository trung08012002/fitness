package com.example.fit.ui.BottomBar.Home



import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape

import androidx.compose.ui.res.painterResource


import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.fit.R

import com.example.fit.ui.theme.FitTheme

import com.example.fit.ui.theme.yellowdeep
import com.google.relay.compose.RowScopeInstanceImpl.align

const val CALENDAR_ROWS=6
const val CALENDAR_COLUMNS=7
@Composable
fun Calendar(
    modifier: Modifier=Modifier,
    calendarInput:List<String>,
    daysarray:List<String>,
    week:List<String>,
    onDayClick:(String)->Unit,
    chooseday:String,
    strokeWidth:Float=15f,
    month:String,
    year:String,
    day:String,
    popup:Boolean,
    onPopupClick:(Boolean)->Unit
){
    Surface(
        modifier = modifier,
        shape = RectangleShape,
        color=MaterialTheme.colorScheme.surface,
        shadowElevation = 0.5.dp,
    ) {
    Column(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
            .wrapContentHeight())
        {
            Row(modifier.align(Alignment.Center)){
                Icon(painter = painterResource(id = R.drawable.ic_baseline_calendar_month_24) , contentDescription ="calendar")
                Text(text = month+" "+day)
            }
            Icon(painter = painterResource(id = R.drawable.ic_baseline_cancel_24), contentDescription ="cancel",modifier=Modifier
                .align(Alignment.CenterEnd))
        }

      Row(modifier = modifier.fillMaxWidth())
      {
          Row(modifier = modifier.border(0.5.dp, Color.Black).weight(1f)) {
              Icon(
                  painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                  contentDescription = "arrow back"
              )
              Text(
                  text = month,
                  fontWeight = FontWeight.SemiBold,
                  color = Color.White,
                  fontSize = 15.sp
              )
              Icon(
                  painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
                  contentDescription = "arrow forward"
              )
          }
          Row(modifier = modifier.border(0.5.dp, Color.Black).weight(1f)) {
              Icon(
                  painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                  contentDescription = "arrow back"
              )
              Text(
                  text = month,
                  fontWeight = FontWeight.SemiBold,
                  color = Color.White,
                  fontSize = 15.sp
              )
              Icon(
                  painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
                  contentDescription = "arrow forward"
              )
          }

      }

      LazyVerticalGrid(columns = GridCells.Fixed(CALENDAR_COLUMNS),
          contentPadding = PaddingValues(10.dp),
          content = {
              items(daysarray.size){
                  index->Text(text =daysarray[index], modifier = modifier.padding(4.dp))
              }
              items(calendarInput.size)
              { index ->
                  Text(text = calendarInput[index],
                      modifier = modifier
                          .padding(2.dp)
                          .let {
                              if (chooseday == calendarInput[index]) {
                                  it.background(color = MaterialTheme.colorScheme.yellowdeep)
                              } else {
                                  it.background(color = Color.White)
                              }
                          }
                          .padding(2.dp)
                          .clickable { onDayClick(calendarInput[index]) },
                      color = Color.Black,
                      fontSize = 15.sp,
                      fontWeight = FontWeight.SemiBold,
                      textAlign = TextAlign.Center
                  )

              }
          }
      )


  }
  }
}



