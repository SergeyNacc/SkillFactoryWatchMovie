package nacc.sergey.watchmovie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {

    private lateinit var film: Film

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFilmsDetails()

        //обработка кнопки, находится фильм в Избранном или нет
        details_fab_favorites.setOnClickListener{
            if (film.isInFavorites) {
                details_fab_favorites.setImageResource(R.drawable.ic_baseline_favorite_24)
                film.isInFavorites = true
            } else {
                details_fab_favorites.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                film.isInFavorites = false
            }
        }

        // создаем реализацию кнопки «Поделится».
        details_fab_share.setOnClickListener {
            val intent = Intent()   //создаём интент
            intent.action = Intent.ACTION_SEND   //Укзываем action с которым он запускается

            //Кладем данные о нашем фильме
            intent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Check out this film: ${film.title} \n\n ${film.description}"
            )

            //Указываем MIME тип, чтобы система знала, какое приложения предложить
            intent.type = "text/plain"
            //Запускаем наше активити
            startActivity(Intent.createChooser(intent, "Share To:"))

        }
    }

    private fun setFilmsDetails() {
        val film = arguments?.get("film") as Film  //Получаем наш фильм из переданного бандла

        details_toolbar.title = film.title  //Устанавливаем заголовок
        details_poster.setImageResource(film.poster)  //Устанавливаем картинку
        details_description.text = film.description   //Устанавливаем описание

        //логика установки нужной иконки в запуске фрагмента
        details_fab_favorites.setImageResource(
                if (film.isInFavorites) R.drawable.ic_baseline_favorite_24
                else R.drawable.ic_baseline_favorite_border_24
        )
    }
}