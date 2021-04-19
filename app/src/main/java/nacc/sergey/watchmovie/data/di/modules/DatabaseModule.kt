package nacc.sergey.watchmovie.data.di.modules

//здесь у нас будут создаваться инстансы всего того,
//что относится к слою Model (то есть данным для всего приложения).

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import nacc.sergey.watchmovie.data.MainRepository
import nacc.sergey.watchmovie.data.dao.FilmDao
import nacc.sergey.watchmovie.data.db.AppDatabase
import javax.inject.Singleton


@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideFilmDao(context: Context) = Room.databaseBuilder(
            context, AppDatabase::class.java, "film_db").build().filmDao()

    @Provides
    @Singleton
    fun provideRepository(filmDao: FilmDao) = MainRepository(filmDao)
}