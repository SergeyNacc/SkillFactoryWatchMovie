package nacc.sergey.watchmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setFilmsDetails()
    }

    private fun setFilmsDetails() {
        val film = intent.extras?.get("film") as Film  //Получаем наш фильм из переданного бандла

        details_toolbar.title = film.title  //Устанавливаем заголовок
        details_poster.setImageResource(film.poster)  //Устанавливаем картинку
        details_description.text = film.description   //Устанавливаем описание
    }
}