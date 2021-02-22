package nacc.sergey.watchmovie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import nacc.sergey.watchmovie.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var film: Film
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFilmsDetails()

        //обработка кнопки, находится фильм в Избранном или нет
        binding.detailsFabFavorites.setOnClickListener{
            if (film.isInFavorites) {
                binding.detailsFabFavorites.setImageResource(R.drawable.ic_baseline_favorite_24)
                film.isInFavorites = true
            } else {
                binding.detailsFabFavorites.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                film.isInFavorites = false
            }
        }

        // создаем реализацию кнопки «Поделится».
        binding.detailsFabShare.setOnClickListener {
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

        binding.detailsToolbar.title = film.title  //Устанавливаем заголовок
        binding.detailsPoster.setImageResource(film.poster)  //Устанавливаем картинку
        binding.detailsDescription.text = film.description   //Устанавливаем описание

        //логика установки нужной иконки в запуске фрагмента
        binding.detailsFabFavorites.setImageResource(
                if (film.isInFavorites) R.drawable.ic_baseline_favorite_24
                else R.drawable.ic_baseline_favorite_border_24
        )
    }
}