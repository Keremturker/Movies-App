package in_.turker.moviesapp.network

import in_.turker.moviesapp.data.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */
interface MovieService {
    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key") apiKey: String,
        @Query("per_page") itemsPerPage: Int
    ): Movie
}