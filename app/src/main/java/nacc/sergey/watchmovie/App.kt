package nacc.sergey.watchmovie

import android.app.Application
import nacc.sergey.watchmovie.data.di.DI
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
class App : Application() {

//    lateinit var repo: MainRepository
//    lateinit var interactor: Interactor
//    lateinit var retrofitService: TmdbApi

    override fun onCreate(){
        super.onCreate()
        startKoin {
            //Прикрепляем контекст
            androidContext(this@App)
            //(Опционально) подключаем зависимость
            androidLogger()
            //Инициализируем модули
            modules(listOf(DI.mainModule))
        }

//        //Инициализируем экземпляр App, через который будем получать доступ к остальным переменным
//        instance = this
//
//        //Инициализируем репозиторий
//        repo = MainRepository()
//
//        //Создаем кастомный клиент
//        val okHttpClient = OkHttpClient.Builder()
//        //таймауты для медленного интрнета
//            .callTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//        //логгер
//            .addInterceptor(HttpLoggingInterceptor().apply {
//                if (BuildConfig.DEBUG) {
//                    level = HttpLoggingInterceptor.Level.BASIC
//                }
//            })
//            .build()
//
//        //Создаем ретрофит
//        val retrofit = Retrofit.Builder()
//
//        //Указываем базовый URL из констант
//            .baseUrl(ApiConstants.BASE_URL)
//        //Добавляем конвертер
//            .addConverterFactory(GsonConverterFactory.create())
//        //Добавляем кастомный клиент
//            .client(okHttpClient)
//            .build()
//
//        //Создаем сам сервис с методами для запросов
//        retrofitService = retrofit.create(TmdbApi::class.java)
//
//        //Инициализируем интерактор
//        interactor = Interactor(repo, retrofitService)
//    }
//
//    companion object {
//        //Здесь статически хранится ссылка на экземпляр App
//        lateinit var instance: App
//        //Приватный сеттер, чтобы нельзя было в эту переменную присвоить что-либо другое
//        private set
    }

}