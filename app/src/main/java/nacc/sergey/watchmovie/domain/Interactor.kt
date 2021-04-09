package nacc.sergey.watchmovie.domain

import nacc.sergey.watchmovie.API
import nacc.sergey.watchmovie.data.*
import nacc.sergey.watchmovie.data.Entity.TmdbResults
import nacc.sergey.watchmovie.data.preferenes.PreferenceProvider
import nacc.sergey.watchmovie.utils.Converter
import nacc.sergey.watchmovie.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {

//    передаём коллбэк из вью модели, чтобы реагировать на то, когда фильмы будут получены
//    и страницу, которую нужно загрузить (это для пагинации)
    fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getFilms(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResults> {

            override fun onResponse(call: Call<TmdbResults>, response: Response<TmdbResults>) {
                //При успехе мы вызываем метод передаем onSuccess и в этот коллбэк список фильмов
                val list = Converter.convertApiListToDtoList(response.body()?.tmdbFilms)

                //Кладем фильмы в бд
                list.forEach{
                    repo.putToDb(film = it)
                }
                callback.onSuccess(list)
            }

            override fun onFailure(call: Call<TmdbResults>, t: Throwable) {
                //В случае провала вызываем другой метод коллбека
                callback.onFailure()
            }
        })
    }

    //Метод для получения настроек
    fun getDefaultCategoryFromPreferences() = preferences.geDefaultCategory()

    //Метод для сохранения настроек
    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }

    fun getFilmsFromDB(): List<Film> = repo.getAllFromDB()

}