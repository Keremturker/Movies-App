package in_.turker.moviesapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import in_.turker.moviesapp.base.BaseRepository
import in_.turker.moviesapp.data.model.Result
import in_.turker.moviesapp.data.pagingdatasource.UpcomingPagingDataSource
import in_.turker.moviesapp.network.APIClientImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */
class MovieRepository @Inject
constructor(private val apiServiceImpl: APIClientImpl) : BaseRepository() {

    suspend fun getNowPlaying(
        scope: CoroutineScope,
        onSuccess: ((List<Result>?) -> Unit),
        onErrorAction: ((String?) -> Unit)
    ) =
        sendRequest(
            scope = scope,
            client = { apiServiceImpl.apiCollect.getNowPlaying() },
            onSuccess = {
                onSuccess(it.results)
            },
            onErrorAction = {
                onErrorAction(it)
            }
        )

    fun getUpcoming(): Flow<PagingData<Result>> {
        return Pager(config = PagingConfig(pageSize = networkPageSize), pagingSourceFactory = {
            UpcomingPagingDataSource(apiServiceImpl.apiCollect)
        }).flow
    }

}