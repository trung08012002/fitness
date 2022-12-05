package com.example.fit.ui.Tab

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.relay.compose.BoxScopeInstanceImpl.align
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs:List<TabItem>,pagerState:PagerState)
{
    val scope= rememberCoroutineScope()
    TabRow(
        // Our selected tab is our current page
        selectedTabIndex = pagerState.currentPage,
        // Override the indicator, using the provided pagerTabIndicatorOffset modifier
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        // Add tabs for all of our pages
        tabs.forEachIndexed { index, tab ->
          Tab(selected = pagerState.currentPage==index, onClick = {scope.launch {
              pagerState.animateScrollToPage(index)
          }}) {
              Column() {
                  Text(text = tab.title, textAlign = TextAlign.Center)
                  Divider(color = Color.Black, thickness = 1.dp)
              }

          }
        }
    }
}
