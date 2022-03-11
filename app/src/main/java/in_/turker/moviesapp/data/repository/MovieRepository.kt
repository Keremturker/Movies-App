package in_.turker.moviesapp.data.repository

import androidx.paging.PagingData
 import in_.turker.moviesapp.data.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */
interface MovieRepository {
    fun getNowPlaying(): Flow<PagingData<Result>>
}