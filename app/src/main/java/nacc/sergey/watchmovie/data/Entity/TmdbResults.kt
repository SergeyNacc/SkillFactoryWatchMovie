package nacc.sergey.watchmovie.data.Entity

import com.google.gson.annotations.SerializedName
import nacc.sergey.watchmovie.data.Entity.TmdbFilm

data class TmdbResults (
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val tmdbFilms: List<TmdbFilm>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)