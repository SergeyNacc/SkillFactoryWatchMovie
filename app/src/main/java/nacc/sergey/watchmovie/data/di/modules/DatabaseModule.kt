package nacc.sergey.watchmovie.data.di.modules

//здесь у нас будут создаваться инстансы всего того,
//что относится к слою Model (то есть данным для всего приложения).

import android.content.Context
import dagger.Module
import dagger.Provides
import nacc.sergey.watchmovie.data.MainRepository
import nacc.sergey.watchmovie.data.db.DatabaseHelper
import javax.inject.Singleton


@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabaseHelper(context: Context) = DatabaseHelper(context)

    @Provides
    @Singleton
    fun provideRepository(databaseHelper: DatabaseHelper) = MainRepository(databaseHelper)
}