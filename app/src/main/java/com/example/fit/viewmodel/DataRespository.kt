package com.example.fit.viewmodel

import androidx.lifecycle.MutableLiveData


class DataRespository {
    var visibleTopBottomBar: MutableLiveData<Boolean>? = null
   fun getVisibleBottomTopBar():MutableLiveData<Boolean>
   {
      if(visibleTopBottomBar==null)
      {
          visibleTopBottomBar=MutableLiveData(true)
      }
       return visibleTopBottomBar!!
   }
}
