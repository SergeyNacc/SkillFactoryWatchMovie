package nacc.sergey.watchmovie


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import nacc.sergey.watchmovie.databinding.FragmentHomeBinding
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private lateinit var binding: FragmentHomeBinding

    private val filmsDataBase = listOf(
            Film("The Witcher", R.drawable.the_witcher, "Geralt of Rivia, a solitary monster hunter, struggles to find his place in a world where people often prove more wicked than beasts.", 9.3f),
            Film("Harry Potter and the Sorcerer's Stone", R.drawable.harry_potter, "An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world.", 9.7f),
            Film("Home Alone", R.drawable.home_alone, "An eight-year-old troublemaker must protect his house from a pair of burglars when he is accidentally left home alone by his family during Christmas vacation.", 9.5f),
            Film("The Gentlemen", R.drawable.the_gentlemen, "An American expat tries to sell off his highly profitable marijuana empire in London, triggering plots, schemes, bribery and blackmail in an attempt to steal his domain out from under him.", 7.8f),
            Film("The Wolf of Wall Street", R.drawable.the_wolf_of_wall_street, "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.", 8.2f),
            Film("Avengers: Endgame", R.drawable.avengers_endgame, "After the devastating events of Мстители: Война бесконечности (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.", 8.1f),
            Film("The Lord of the Rings: The Fellowship of the Ring", R.drawable.the_lord_of_the_rings_the_fellowshipofthe_ring, "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.", 8.9f),
            Film("The Shawshank Redemption", R.drawable.the_shawshank_redemption, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", 7.7f)
    )

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

        AnimationHelper.performFragmentCircularRevealAnimation(binding.homeFragmentRoot, requireActivity(), 1)

        //находим наш RV
        initRecyckler()

        //Кладем нашу БД в RV
        filmsAdapter.addItems(filmsDataBase)

        initSearchView()
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