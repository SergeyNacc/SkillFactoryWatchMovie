package nacc.sergey.watchmovie.utils

import nacc.sergey.watchmovie.data.Entity.TmdbFilm
import nacc.sergey.watchmovie.domain.Film

object Converter {
    fun convertApiListToDtoList(list: List<TmdbFilm>?): List<Film> {
        val result = mutableListOf<Film>()
        list?.forEach {
            result.add(
                Film(
                title = it.title,
                poster = it.posterPath,
                description = it.overview,
                rating = it.voteAverage,
                isInFavorites = false
                )
            )
        }
        return result
    }
}