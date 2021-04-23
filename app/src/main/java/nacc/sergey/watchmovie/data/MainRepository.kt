package nacc.sergey.watchmovie.data


import androidx.lifecycle.LiveData
import nacc.sergey.watchmovie.data.dao.FilmDao
import nacc.sergey.watchmovie.data.entity.Film
import java.util.concurrent.Executors


class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
        //Запросы в бд должны быть в отдельном потоке
        Executors.newSingleThreadExecutor().execute {
            filmDao.insertAll(films)
        }
    }

    fun getAllFromDB(): LiveData<List<Film>> = filmDao.getCachedFilms()
}