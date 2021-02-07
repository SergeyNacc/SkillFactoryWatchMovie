package nacc.sergey.watchmovie

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavigation()

        //Запускаем фрагмент при старте
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment())
            .addToBackStack(null)
            .commit()

    }

    fun launchDetailsFragment(film:Film) {
        val bundle = Bundle()   //Создаем "посылку"
        bundle.putParcelable("film",film)   //Кладем наш фильм в "посылку"
        val fragment = DetailsFragment()     //Кладем фрагмент с деталями в перменную
        fragment.arguments = bundle     //Прикрепляем нашу "посылку" к фрагменту

        //Запускаем фрагмент
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()

    }

    private fun initNavigation() {

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.favorites -> {
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_placeholder, FavoritesFragment())
                            .addToBackStack(null)
                            .commit()
                    true
                }
                R.id.watch_later -> {
                    Toast.makeText(this, "Посмотреть позже", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.selections -> {
                    Toast.makeText(this, "Подборки", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}