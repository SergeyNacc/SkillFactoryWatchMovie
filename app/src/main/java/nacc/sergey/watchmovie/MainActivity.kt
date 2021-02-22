package nacc.sergey.watchmovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import nacc.sergey.watchmovie.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Инициализируем объект
        binding = ActivityMainBinding.inflate(layoutInflater)
        //Передаем его в метод
        setContentView(binding.root)

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

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val tag = "home"
                    val fragment = checkFragmentExistence(tag)

                    /*В первом параметре, если фрагмент не найден и метод вернул null, то с помощью
                    элвиса мы вызываем создание нового фрагмента*/
                    changeFragment(fragment?: HomeFragment(), tag)
                    true
                }
                R.id.watch_later -> {
                    val tag = "watch_later"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment?: WatchLaterFragment(), tag)
                    true
                }
                R.id.selections -> {
                    val tag = "selections"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment?: SelectionsFragment(), tag)
                    true
                }
                R.id.favorites -> {
                    val tag = "favorites"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment?: FavoritesFragment(), tag)
                    true
                }
                else -> false
            }
        }
    }

    //Ищем фрагмент по тэгу, если он есть, то возвращаем его, если нет - то null
    private fun checkFragmentExistence(tag: String): Fragment? = supportFragmentManager.findFragmentByTag(tag)

    //запуск фрагмента
    private fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_placeholder, fragment, tag)
                .addToBackStack(null)
                .commit()
    }

}