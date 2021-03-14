package nacc.sergey.watchmovie.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import nacc.sergey.watchmovie.*
import nacc.sergey.watchmovie.databinding.FragmentFavoritesBinding
import nacc.sergey.watchmovie.domain.Film
import nacc.sergey.watchmovie.view.rv_adapters.FilmListRecyclerAdapter
import nacc.sergey.watchmovie.view.rv_adapters.TopSpacingItemDecoration
import nacc.sergey.watchmovie.utils.AnimationHelper
import nacc.sergey.watchmovie.view.MainActivity


class FavoritesFragment : Fragment() {
    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Получаем список при транзакции фрагмента
        val favoritesList: List<Film> = emptyList()

        AnimationHelper.performFragmentCircularRevealAnimation(
            binding.favoritesFragmentRoot,
            requireActivity(),
            2
        )

        binding.favoritesRecycler.apply {
            filmsAdapter = FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
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

        //Кладем нашу БД в RV
        filmsAdapter.addItems(favoritesList)
    }
}