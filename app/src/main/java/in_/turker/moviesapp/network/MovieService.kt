package in_.turker.moviesapp.network

import in_.turker.moviesapp.data.model.detail.MovieDetail
import in_.turker.moviesapp.data.model.main.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */
interface MovieService {
    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("per_page") itemsPerPage: Int
    ): Movie

    @GET("movie/now_playing")
    suspend fun getNowPlaying(): Movie

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): MovieDetail


}