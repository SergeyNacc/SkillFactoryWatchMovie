package nacc.sergey.watchmovie.data.di

import nacc.sergey.watchmovie.BuildConfig
import nacc.sergey.watchmovie.data.ApiConstants
import nacc.sergey.watchmovie.data.MainRepository
import nacc.sergey.watchmovie.data.TmdbApi
import nacc.sergey.watchmovie.domain.Interactor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DI {
    val mainModule = module {

        //создаем репозиторий
        single { MainRepository() }

        //Создаем объект для получения данных из сети
        single<TmdbApi> {
            val okHttpClient = OkHttpClient.Builder()

            //Настраиваем таймауты для медленного интернета
                .callTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)

            //добавляем логгер
                .addInterceptor(HttpLoggingInterceptor().apply {
                    if (BuildConfig.DEBUG) {
                        level = HttpLoggingInterceptor.Level.BASIC
                    }
                })
                .build()

            //Создаем ретрофит
            val retrofit = Retrofit.Builder()

            //Указываем базовый URL из констант
                .baseUrl(ApiConstants.BASE_URL)
            //Добавляем конвертер
                .addConverterFactory(GsonConverterFactory.create())
            //Добавляем кастомный клиент
                .client(okHttpClient)
                .build()
            //Создаем сам сервис с методами для запросов
            retrofit.create(TmdbApi::class.java)
        }

        //Создаем интерактор
        single { Interactor(get(), get()) }
    }

}