package in_.turker.moviesapp.data.pagingdatasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import in_.turker.moviesapp.data.model.Result
import in_.turker.moviesapp.network.MovieService

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */

class UpcomingPagingDataSource(
    private val movieService: MovieService
) :
    PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = movieService.getUpcoming(page)
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.results.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

}
