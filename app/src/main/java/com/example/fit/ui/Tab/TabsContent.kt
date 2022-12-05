package com.example.fit.ui.Tab

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs:List<TabItem>,pagerState: PagerState)
{
    HorizontalPager(
        count = tabs.size,
        state = pagerState,
    ) { page ->
       tabs[page].screen()
    }
}