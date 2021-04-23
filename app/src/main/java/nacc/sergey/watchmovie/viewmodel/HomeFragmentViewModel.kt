package nacc.sergey.watchmovie.viewmodel

//это «условно» обычный класс, мы вызываем метод inject в блоке init.
//Если бы у нас был бы Android-класс (Activity, Fragment и т.д.), то inject следовало бы вызывать, например, в onCreate.

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nacc.sergey.watchmovie.App
import nacc.sergey.watchmovie.data.entity.Film
import nacc.sergey.watchmovie.domain.Interactor
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {

    val showProgressBar: MutableLiveData<Boolean> = MutableLiveData()

    //Инициализируем интерактор(инжектим)
    @Inject
    lateinit var interactor: Interactor
    val filmsListLiveData: LiveData<List<Film>>

    init {
        App.instance.dagger.inject(this)    //вызваем метод inject на компоненте, передав туда ссылку на наш класс
        filmsListLiveData = interactor.getFilmsFromDB()
        getFilms()
    }

    fun getFilms() {
        showProgressBar.postValue(true)
        interactor.getFilmsFromApi(1, object : ApiCallback {
            override fun onSuccess() {
                showProgressBar.postValue(false)
            }
            override fun onFailure() {
                showProgressBar.postValue(false)
            }
        })
    }

    interface ApiCallback {
        fun onSuccess()
        fun onFailure()
    }
}