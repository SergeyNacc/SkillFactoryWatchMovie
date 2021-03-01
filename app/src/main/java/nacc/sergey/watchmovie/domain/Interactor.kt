package nacc.sergey.watchmovie.domain

import nacc.sergey.watchmovie.data.MainRepository

class Interactor(val repo: MainRepository) {
    fun getFilmsDB(): List<Film> = repo.filmsDataBase
}