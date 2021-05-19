package nacc.sergey.watchmovie.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import java.net.URL
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DetailsFragmentViewModel : ViewModel() {

    //Помечаем функцию как suspend, потому как у нас будет логика показа прогрессбара,
    // а также тоста в конце, и нам необходима прерывающаяся функция,
    // которая будет возвращать объект Bitmap.
    suspend fun loadWallpaper(url: String): Bitmap {

        //используем метод suspendCoroutine, чтобы получить доступ к объекту Continuation
        return suspendCoroutine {

            //загружаем файл из Сети.
            //В URL из представления мы будем передавать адрес картинки.
            val url = URL(url)
            val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())

            //чтобы suspend функция не «потерялась» и продолжила свою работу,
            //нужно вызывать метод resume и ещё возвращать bitmap.
            it.resume(bitmap)
        }
    }
}