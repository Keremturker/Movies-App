package in_.turker.moviesapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import in_.turker.moviesapp.data.model.Result
import in_.turker.moviesapp.data.pagingdatasource.UpcomingPagingDataSource
import in_.turker.moviesapp.network.APIClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val apiClient: APIClient
) : MovieRepository {

    override fun getUpcoming(): Flow<PagingData<Result>> {
        return Pager(config = PagingConfig(pageSize = NETWORK_PAGE_SIZE), pagingSourceFactory = {
            UpcomingPagingDataSource(apiClient.apiCollect)
        }).flow
    }




    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}