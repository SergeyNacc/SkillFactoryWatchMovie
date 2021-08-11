package nacc.sergey.watchmovie.data

import retrofit2.Call
import nacc.sergey.watchmovie.data.Entity.TmdbResults
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {
    @GET("3/movie/popular")
    fun getFilms(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<TmdbResults>
}