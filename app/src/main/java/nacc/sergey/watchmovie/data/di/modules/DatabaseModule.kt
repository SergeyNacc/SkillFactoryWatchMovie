package nacc.sergey.watchmovie.data.di.modules

//здесь у нас будут создаваться инстансы всего того,
//что относится к слою Model (то есть данным для всего приложения).

import dagger.Module
import dagger.Provides
import nacc.sergey.watchmovie.data.MainRepository
import javax.inject.Singleton


@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideRepository() = MainRepository()  //возвращает объект MainRepository, этот объект будет нужен нам в интеракторе.
}