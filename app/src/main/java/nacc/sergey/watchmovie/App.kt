package nacc.sergey.watchmovie

import android.app.Application
import nacc.sergey.watchmovie.data.di.AppComponent
import nacc.sergey.watchmovie.data.di.DaggerAppComponent
import nacc.sergey.watchmovie.data.di.modules.DatabaseModule
import nacc.sergey.watchmovie.data.di.modules.DomainModule
import nacc.sergey.watchmovie.data.di.modules.RemoteModule


class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate(){
        super.onCreate()
        instance = this

        //Создаем компонент
        dagger = DaggerAppComponent.builder()
                .remoteModule(RemoteModule())
                .databaseModule(DatabaseModule())
                .domainModule(DomainModule(this))
                .build()
    }

    companion object {
        lateinit var instance: App
            private set
    }

}