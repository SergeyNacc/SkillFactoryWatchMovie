package nacc.sergey.watchmovie.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import nacc.sergey.watchmovie.API
import nacc.sergey.watchmovie.data.*
import nacc.sergey.watchmovie.data.entity.Film
import nacc.sergey.watchmovie.data.entity.TmdbResults
import nacc.sergey.watchmovie.data.preferenes.PreferenceProvider
import nacc.sergey.watchmovie.utils.Converter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {

    val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    var progressBarState = Channel<Boolean>(Channel.CONFLATED)

    fun getFilmsFromApi(page: Int) {
        //Показываем ProgressBar
        scope.launch {
            progressBarState.send(true)
        }

        //Метод getDefaultCategoryFromPreferences() будет получать при каждом запросе нужный нам список фильмов
        retrofitService.getFilms(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResults> {

            override fun onResponse(call: Call<TmdbResults>, response: Response<TmdbResults>) {

                //При успехе мы вызываем метод передаем onSuccess и в этот коллбэк список фильмов
                val list = Converter.convertApiListToDtoList(response.body()?.tmdbFilms)

                //Кладем фильмы в бд
                //В случае успешного ответа выключаем ProgressBar
                scope.launch {
                    repo.putToDb(list)
                    progressBarState.send(false)
                }
            }

            override fun onFailure(call: Call<TmdbResults>, t: Throwable) {
                //В случае провала выключаем ProgressBar
                scope.launch {
                    progressBarState.send(false)
                }
            }
        })
    }

    //Метод для получения настроек
    fun getDefaultCategoryFromPreferences() = preferences.geDefaultCategory()

    //Метод для сохранения настроек
    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }

    fun getFilmsFromDB(): Flow<List<Film>> = repo.getAllFromDB()

}