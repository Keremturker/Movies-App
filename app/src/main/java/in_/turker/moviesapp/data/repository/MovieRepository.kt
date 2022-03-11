package in_.turker.moviesapp.data.repository

import androidx.paging.PagingData
 import in_.turker.moviesapp.data.model.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */
interface MovieRepository {
     fun getUpcoming(): Flow<PagingData<Result>>
}