package nacc.sergey.watchmovie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nacc.sergey.watchmovie.domain.Film
import nacc.sergey.watchmovie.domain.Interactor
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeFragmentViewModel : ViewModel(), KoinComponent {
    val filmsListLiveData: MutableLiveData<List<Film>> = MutableLiveData()

    //Инициализируем интерактор
    private var interactor: Interactor by inject()

    init {
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