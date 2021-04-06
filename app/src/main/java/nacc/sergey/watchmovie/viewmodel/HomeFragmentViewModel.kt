package nacc.sergey.watchmovie.viewmodel

//это «условно» обычный класс, мы вызываем метод inject в блоке init.
//Если бы у нас был бы Android-класс (Activity, Fragment и т.д.), то inject следовало бы вызывать, например, в onCreate.

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nacc.sergey.watchmovie.App
import nacc.sergey.watchmovie.domain.Film
import nacc.sergey.watchmovie.domain.Interactor
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    val filmsListLiveData: MutableLiveData<List<Film>> = MutableLiveData()

    //Инициализируем интерактор(инжектим)
    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)    //вызваем метод inject на компоненте, передав туда ссылку на наш класс
        getFilms()
    }

    fun getFilms() {
        interactor.getFilmsFromApi(1, object : ApiCallback {
            override fun onSuccess(films: List<Film>) {
                filmsListLiveData.postValue(films)
            }
            override fun onFailure() {
            }
        })
    }

    interface ApiCallback {
        fun onSuccess(films: List<Film>)
        fun onFailure()
    }
}