package nacc.sergey.watchmovie.view.fragments


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import nacc.sergey.watchmovie.databinding.FragmentHomeBinding
import nacc.sergey.watchmovie.data.entity.Film
import nacc.sergey.watchmovie.view.rv_adapters.FilmListRecyclerAdapter
import nacc.sergey.watchmovie.view.rv_adapters.TopSpacingItemDecoration
import nacc.sergey.watchmovie.utils.AnimationHelper
import nacc.sergey.watchmovie.view.MainActivity
import nacc.sergey.watchmovie.viewmodel.HomeFragmentViewModel
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private lateinit var binding: FragmentHomeBinding

    //Возвращаем БД с фильмами. Инициализируем (лениво) ViewModel
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentViewModel::class.java)
    }

    //переменная, куда будем класть нашу БД из ViewModel, чтобы у нас не сломался поиск
    private var filmsDataBase = listOf<Film>()

        //Используем backing field
        set(value) {
            //Если придет такое же значение, то мы выходим из метода
            if (field == value) return
            //Если пришло другое значение, то кладем его в переменную
            field = value
            //Обновляем RV адаптер
            filmsAdapter.addItems(field)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(
            binding.homeFragmentRoot,
            requireActivity(),
            1
        )

        //находим наш RV
        initRecyckler()

        //Кладем нашу БД в RV
        viewModel.filmsListLiveData.observe(viewLifecycleOwner, Observer<List<Film>> {
            filmsDataBase = it
            filmsAdapter.addItems(it)
        })

        initPullToRefresh()
        initSearchView()

        viewModel.showProgressBar.observe(viewLifecycleOwner, Observer<Boolean> {
            binding.progressBar.isVisible = it
        })
    }

    private fun initPullToRefresh() {
        //Вешаем слушатель, чтобы вызвался pull to refresh
        binding.pullToRefresh.setOnRefreshListener {
            //Чистим адаптер(items нужно будет сделать паблик или создать для этого публичный метод)
            filmsAdapter.items.clear()
            //Делаем новый запрос фильмов на сервер
            viewModel.getFilms()
            //Убираем крутящиеся колечко
            binding.pullToRefresh.isRefreshing = false
        }
    }

    //появление ввода при нажатии в любое место строки Поиск
    private fun initSearchView() {
        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
        }

        //Подключаем слушатель изменений введенного текста в поиск
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            //Этот метод отрабатывает при нажатии кнопки "поиск" на софт клавиатуре
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            //Этот метод отрабатывает на каждое изменение текста
            override fun onQueryTextChange(newText: String): Boolean {

                //Если ввод пуст то вставляем в адаптер всю БД
                if (newText.isEmpty()) {
                    filmsAdapter.addItems(filmsDataBase)
                    return true
                }
                //Фильтруем список на поиск подходящих сочетаний
                val result = filmsDataBase.filter {

                    //Чтобы все работало правильно, нужно запросы и имя фильма приводить к нижнему регистру
                    it.title.toLowerCase(Locale.getDefault()).contains(newText.toLowerCase(Locale.getDefault()))
                }
                //Добавляем в адаптер
                filmsAdapter.addItems(result)
                return true
            }
        })
    }

    private fun initRecyckler() {
        binding.mainRecycler.apply {
            filmsAdapter =
                    FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                        override fun click(film: Film) {
                            (requireActivity() as MainActivity).launchDetailsFragment(film)
                        }
                    })

            //Присваиваем адаптер
            adapter = filmsAdapter
            //Присвоим layoutmanager
            layoutManager = LinearLayoutManager(requireContext())
            //Применяем декоратор для отступов
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
    }
}