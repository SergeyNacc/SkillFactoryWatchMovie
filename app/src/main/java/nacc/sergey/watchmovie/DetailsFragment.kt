package nacc.sergey.watchmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    private fun setFilmsDetails() {
        val film = intent.extras?.get("film") as Film  //Получаем наш фильм из переданного бандла

        details_toolbar.title = film.title  //Устанавливаем заголовок
        details_poster.setImageResource(film.poster)  //Устанавливаем картинку
        details_description.text = film.description   //Устанавливаем описание
    }
}