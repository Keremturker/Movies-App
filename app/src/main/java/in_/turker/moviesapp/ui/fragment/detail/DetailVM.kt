package in_.turker.moviesapp.ui.fragment.detail

import android.app.Application
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import in_.turker.moviesapp.base.BaseViewModel
import in_.turker.moviesapp.data.model.detail.MovieDetail
import in_.turker.moviesapp.data.repository.MovieRepository
import in_.turker.moviesapp.utils.ApiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */

@HiltViewModel
class DetailVM @Inject constructor(
    myApp: Application,
    private val movieRepository: MovieRepository
) : BaseViewModel(app = myApp) {

    private val _onMovieDetail = MutableStateFlow<ApiState<MovieDetail?>>(ApiState.Empty)
    val onMovieDetail: StateFlow<ApiState<MovieDetail?>> = _onMovieDetail


    fun getMovieDetail(movieId: Int) = viewModelScope.launch {
        _onMovieDetail.value = ApiState.Loading
        movieRepository.getMovieDetail(
            scope = viewModelScope,
            movieId = movieId,
            onSuccess = {
                _onMovieDetail.value = ApiState.Success(it)
            }, onErrorAction = {
                _onMovieDetail.value = ApiState.Failure(it)
            })
    }
}