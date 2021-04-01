package nacc.sergey.watchmovie.data.di.modules

//тут у нас будет создаваться наш Interactor

import dagger.Module
import dagger.Provides
import nacc.sergey.watchmovie.data.MainRepository
import nacc.sergey.watchmovie.data.TmdbApi
import nacc.sergey.watchmovie.domain.Interactor
import javax.inject.Singleton


@Module
class DomainModule {

    @Provides
    @Singleton

//создаем объект Interactor в методе provideInteractor, в параметры которого нам приходят MainRepository и TmdbApi.
//А вот Interactor будем инжектить в наши классы

    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi) = Interactor(repo = repository, retrofitService = tmdbApi)
}