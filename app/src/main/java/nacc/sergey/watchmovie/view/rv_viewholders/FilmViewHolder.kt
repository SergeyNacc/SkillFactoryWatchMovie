package nacc.sergey.watchmovie.view.rv_viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.film_item.view.*
import nacc.sergey.watchmovie.data.ApiConstants
import nacc.sergey.watchmovie.domain.Film


//В конструктор класс передается layout, который мы создали(film_item.xml)
class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    //Привязываем view из layout к переменным
    private val title = itemView.title
    private val poster = itemView.poster
    private val description = itemView.description

    //Вот здесь мы находим в верстке наш прогресс бар для рейтинга
    private val ratingDonut = itemView.rating_donut

    //В этом методе кладем данные из film в наши view
    fun bind(film: Film) {
        //Устанавливаем заголовок
        title.text = film.title
        //Указываем контейнер, в котором будет "жить" наша картинка
        Glide.with(itemView)
            //Загружаем ресурс
            .load(ApiConstants.IMAGES_URL + "w342" + film.poster)
            //Центруем изображение
            .centerCrop()
            //Указываем ImageView, куда будем загружать изображение
            .into(poster)
        //Устанавливаем описание
        description.text = film.description
        //Устанавливаем рэйтинг
        ratingDonut.setProgress((film.rating * 10).toInt())
    }
}