package nacc.sergey.watchmovie.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import nacc.sergey.watchmovie.data.dao.FilmDao
import nacc.sergey.watchmovie.data.entity.Film


@Database(entities = [Film::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}