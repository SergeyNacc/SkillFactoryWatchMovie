package nacc.sergey.watchmovie

import android.app.Application
import nacc.sergey.watchmovie.data.di.AppComponent
import nacc.sergey.watchmovie.data.di.DaggerAppComponent



class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate(){
        super.onCreate()
        instance = this

        //Создаем компонент
        dagger = DaggerAppComponent.create()
    }

    companion object {
        lateinit var instance: App
            private set
    }

}