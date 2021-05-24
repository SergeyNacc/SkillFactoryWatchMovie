package nacc.sergey.watchmovie.viewmodel

//это «условно» обычный класс, мы вызываем метод inject в блоке init.
//Если бы у нас был бы Android-класс (Activity, Fragment и т.д.), то inject следовало бы вызывать, например, в onCreate.

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import nacc.sergey.watchmovie.App
import nacc.sergey.watchmovie.data.entity.Film
import nacc.sergey.watchmovie.domain.Interactor
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {

    //Инициализируем интерактор(инжектим)
    @Inject
    lateinit var interactor: Interactor
    val filmsListData: Flow<List<Film>>
    val showProgressBar: Channel<Boolean>

    init {
        App.instance.dagger.inject(this)    //вызваем метод inject на компоненте, передав туда ссылку на наш класс
        showProgressBar = interactor.progressBarState
        filmsListData = interactor.getFilmsFromDB()
        getFilms()
    }

    fun getFilms() {
        interactor.getFilmsFromApi(1)
    }
}