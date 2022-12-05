package com.example.fit.viewmodel

import androidx.lifecycle.*
import com.example.fit.Respository.UserInforRespository
import com.example.fit.data.StateApplication.Resource
import com.example.fit.data.component.User.user_infor

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async


import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class InforUserModel @Inject constructor(private val inforrespository:UserInforRespository):ViewModel() {
   private var _datainforflow = MutableLiveData<Resource<user_infor>>(null)
   private var _prepareinforflow = MutableLiveData<Resource<Boolean>>(null)
   private var _inforuserflow=MutableLiveData<Resource<Boolean>>(null)
   val datainforflow: LiveData<Resource<user_infor>> = _datainforflow
   val prepareinforflow:LiveData<Resource<Boolean>> =_prepareinforflow
   val inforuserflow:LiveData<Resource<Boolean>> = _inforuserflow
   fun <T> updateSingleFieldPrepare(field: String,value:T)
   {
      viewModelScope.async(Dispatchers.IO){
         val result =inforrespository.updateSingleFieldPrepare(field,value)
         withContext(Dispatchers.Main)
         {
            _prepareinforflow.postValue(result.asLiveData(viewModelScope.coroutineContext).value)
         }
      }
   }
   fun getdatausercurrent()
   {
      viewModelScope.async(Dispatchers.IO) {
          val result =  inforrespository.getdatausercurrent()
         withContext(Dispatchers.Main)
         {
            _datainforflow.value=result.asLiveData(viewModelScope.coroutineContext).value
         }
      }
   }
   fun addUserInfor(userInfor: user_infor)
   {
      viewModelScope.async (Dispatchers.IO){
         val result = inforrespository.addUserInfor(userInfor)
         withContext(Dispatchers.Main)
         {
            _prepareinforflow.value=result.asLiveData(viewModelScope.coroutineContext).value
         }
      }
   }
   fun deleteInfor(id:String)
   {
      viewModelScope.async(Dispatchers.IO) {
         val result=inforrespository.deleteInfor(id)
         withContext(Dispatchers.Main)
         {
            _prepareinforflow.value=result.asLiveData(viewModelScope.coroutineContext).value
         }
      }
   }
   fun updateInfor(userInfor: user_infor)
   {
      viewModelScope.async (Dispatchers.IO){
         val result = inforrespository.updateInfor(userInfor)
         withContext(Dispatchers.Main)
         {
            _prepareinforflow.value = result.asLiveData(viewModelScope.coroutineContext).value
         }
      }
   }
}