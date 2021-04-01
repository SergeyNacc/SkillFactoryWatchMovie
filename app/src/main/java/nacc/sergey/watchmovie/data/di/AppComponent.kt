package nacc.sergey.watchmovie.data.di

import dagger.Component
import nacc.sergey.watchmovie.data.di.modules.DatabaseModule
import nacc.sergey.watchmovie.data.di.modules.DomainModule
import nacc.sergey.watchmovie.data.di.modules.RemoteModule
import nacc.sergey.watchmovie.viewmodel.HomeFragmentViewModel
import javax.inject.Singleton


@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    modules = [
    RemoteModule::class,
    DatabaseModule::class,
    DomainModule::class
    ]
)

interface AppComponent {

    //метод для того, чтобы внедрять зависимости в HomeFragmentViewModel
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)

}