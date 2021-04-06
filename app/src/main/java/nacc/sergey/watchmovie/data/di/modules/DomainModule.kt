package nacc.sergey.watchmovie.data.di.modules

//тут у нас будет создаваться наш Interactor

import android.content.Context
import dagger.Module
import dagger.Provides
import nacc.sergey.watchmovie.data.MainRepository
import nacc.sergey.watchmovie.data.TmdbApi
import nacc.sergey.watchmovie.data.preferenes.PreferenceProvider
import nacc.sergey.watchmovie.domain.Interactor
import javax.inject.Singleton


@Module
//Передаем контекст для SharedPreferences через конструктор
class DomainModule (val context: Context) {

    //Нам нужно контекст как-то провайдить, создаем такой метод
    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    //экземпляр SharedPreferences
    fun providePreferences(context: Context) = PreferenceProvider(context)

//создаем объект Interactor в методе provideInteractor, в параметры которого нам приходят MainRepository и TmdbApi.
//А вот Interactor будем инжектить в наши классы
    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi, preferenceProvider: PreferenceProvider) =
            Interactor(repo = repository, retrofitService = tmdbApi, preferences = preferenceProvider)
}