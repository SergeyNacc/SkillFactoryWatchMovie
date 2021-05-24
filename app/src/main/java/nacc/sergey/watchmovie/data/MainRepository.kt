package nacc.sergey.watchmovie.data


import kotlinx.coroutines.flow.Flow
import nacc.sergey.watchmovie.data.dao.FilmDao
import nacc.sergey.watchmovie.data.entity.Film


class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
            filmDao.insertAll(films)
    }

    fun getAllFromDB(): Flow<List<Film>> = filmDao.getCachedFilms()
}