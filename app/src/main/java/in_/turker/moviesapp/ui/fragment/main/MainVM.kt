package in_.turker.moviesapp.ui.fragment.main

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import in_.turker.moviesapp.R
import in_.turker.moviesapp.base.BaseViewModel
import in_.turker.moviesapp.data.model.main.Result
import in_.turker.moviesapp.data.repository.MovieRepository
import in_.turker.moviesapp.utils.ApiState
import in_.turker.moviesapp.utils.MOVIE_ID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */

@HiltViewModel
class MainVM @Inject constructor(
    myApp: Application,
    private val movieRepository: MovieRepository
) : BaseViewModel(app = myApp) {

    private val _onNowPlaying = MutableStateFlow<ApiState<List<Result>?>>(ApiState.Empty)
    val onNowPlaying: StateFlow<ApiState<List<Result>?>> = _onNowPlaying



    fun getUpcoming(): Flow<PagingData<Result>> {
        val repoItemsUiStates = movieRepository.getUpcoming()
            .map { pagingData ->
                pagingData.map { result -> result }
            }.cachedIn(viewModelScope)
        return repoItemsUiStates
    }


    fun getNowPlaying() = viewModelScope.launch {
        _onNowPlaying.value = ApiState.Loading
        movieRepository.getNowPlaying(
            scope = viewModelScope,
            onSuccess = {
                _onNowPlaying.value = ApiState.Success(it)
            }, onErrorAction = {
                _onNowPlaying.value = ApiState.Failure(it)
            })
    }


    fun goToDetail(movieId: Int) {
        Bundle().apply {
            putInt(MOVIE_ID, movieId)
            navigateFragment(R.id.action_global_fragmentDetail, this)
        }
    }
}