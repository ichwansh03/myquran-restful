package com.ichwan.rest.quranapi.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ichwan.rest.quranapi.domain.repository.ChapterRepository
import com.ichwan.rest.quranapi.models.ChapterResponse
import com.ichwan.rest.quranapi.utils.NetworkState
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

class ChapterViewModels: ViewModel() {

    private val repository: ChapterRepository = ChapterRepository()

    private var chapterResponse: ChapterResponse? = null
    private var _chapterResponse = MutableLiveData<NetworkState<ChapterResponse?>>()
    var liveChapterResponse: LiveData<NetworkState<ChapterResponse?>> = _chapterResponse

    fun getChapter() {
        viewModelScope.launch {
            _chapterResponse.postValue(NetworkState.Loading)
            val response = repository.getChapter("id")
            _chapterResponse.postValue(handlerChapterResponse(response))
        }
    }

    private fun handlerChapterResponse(response: Response<ChapterResponse>): NetworkState<ChapterResponse?> {
        return if (response.isSuccessful) {
            response.body()?.let {
                if (chapterResponse == null) chapterResponse = it
                else {
                    val oldChapter = chapterResponse?.chapters
                    val newChapter = it.chapters
                    oldChapter?.addAll(newChapter)
                }
            }

            NetworkState.Success(chapterResponse ?: response.body())
        } else NetworkState.Error(
            try {
                response.errorBody()?.string()?.let {
                    JSONObject(it).get("status_message")
                }
            } catch (e: JSONException) {
                e.localizedMessage
            } as String
        )
    }
}