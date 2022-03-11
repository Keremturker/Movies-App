package in_.turker.moviesapp.ui.fragment.main

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import in_.turker.moviesapp.base.BaseViewModel
import in_.turker.moviesapp.data.model.Result
import in_.turker.moviesapp.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */

@HiltViewModel
class MainVM @Inject constructor(
    myApp: Application,
    private val repository: MovieRepository
) : BaseViewModel(app = myApp) {

    fun getUpcoming(): Flow<PagingData<Result>> {
        val repoItemsUiStates = repository.getUpcoming()
            .map { pagingData ->
                pagingData.map { result -> result }
            }.cachedIn(viewModelScope)
        return repoItemsUiStates
    }
}