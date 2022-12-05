package com.example.fit.viewmodel

import android.net.Uri
import android.provider.MediaStore.Video
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.*
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.example.fit.Media.EntityCourse
import com.example.fit.Media.MediaRepository
import com.example.fit.Media.VideoItem
import com.example.fit.Media.VideoUri
import com.example.fit.data.StateApplication.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CourseModel  @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    val player: Player,
    val respository: MediaRepository,
    val datarespository:DataRespository
): ViewModel() {
    var currentCourseId= mutableStateOf(0)
    private var videoUris = savedStateHandle.getStateFlow("videoUris"
        , emptyList<VideoUri>()
    )
    private var  _videoList:MutableLiveData<Resource<List<EntityCourse>>> = MutableLiveData<Resource<List<EntityCourse>>>()
    var videoList :LiveData<Resource<List<EntityCourse>>>? = _videoList

    private var _currentcourse:MutableLiveData<Resource<EntityCourse>> = MutableLiveData<Resource<EntityCourse>> ()
    var currentcourse:LiveData<Resource<EntityCourse>>? = _currentcourse

    val _visibleBottomTopBar:MutableLiveData<Boolean> = datarespository.getVisibleBottomTopBar()
    val visibleBottomTopBar:LiveData<Boolean> = _visibleBottomTopBar
    init {
        viewModelScope.launch(Dispatchers.IO) {
            respository.getallcourse().collect {
                _videoList.postValue(it)
                withContext(Dispatchers.Main)
                {
                    if(_videoList.value is Resource.Success)
                    {
                        for(i in (_videoList?.value as Resource.Success<List<EntityCourse>>).result)
                        {

                            addVideoUri(VideoUri(i.IdCourse,i.VideoUrl.toUri()))

                        }
                    }
                }

            }
        }
        savedStateHandle.get<Int>("currentCourseId")?.let{
            setCourseId(it)
        }


        player.prepare()
    }
    var videoItems:MutableLiveData<List<VideoItem>> = MutableLiveData<List<VideoItem>>()
    fun makeBottomTopBarInvisible()
    {
        datarespository.visibleTopBottomBar?.postValue(false)
        Log.d("visibleviewmodeli",visibleBottomTopBar.value.toString())
    }
    fun makeBottomTopBarVisible()
    {
        datarespository.visibleTopBottomBar?.postValue(true)
        Log.d("visibleviewmodelv",visibleBottomTopBar.value.toString())
    }
    fun getcurrentvideo()
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            respository.getcoursebyid(currentCourseId.value).collect{
                _currentcourse.postValue(it)
            }
        }
    }
    fun setCourseId(id:Int)
    {
        this.currentCourseId.value=id
        savedStateHandle.set("currentCourseId",id)
    }
    fun addVideoUri(videoUri: VideoUri)
    {
        savedStateHandle["videoUris"]=videoUris.value+videoUri
        videoItems.postValue(videoItems.value?.plus(
            VideoItem(contentUri = videoUri.uri,
                MediaItem.Builder().
                setUri(videoUri.uri).build(),videoUri.id)
        )?: listOf(
            VideoItem(contentUri = videoUri.uri,
            MediaItem.fromUri(videoUri.uri),videoUri.id)
        )
        )
        player.addMediaItem(MediaItem.fromUri(videoUri.uri))
    }
    fun playVideo(uri: Uri)
    {
       player.setMediaItem(
           videoItems.value?.find { it.contentUri == uri }?.mediaItem?:return
       )
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}

class visibleTopBottomBar(
    val visible:Boolean=true
)
{
    fun copy(visible: Boolean=this.visible)=visibleTopBottomBar(visible)
}