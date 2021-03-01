package nacc.sergey.watchmovie.data

import nacc.sergey.watchmovie.R
import nacc.sergey.watchmovie.domain.Film

class MainRepository {
    val filmsDataBase = listOf(
        Film("The Witcher", R.drawable.the_witcher, "Geralt of Rivia, a solitary monster hunter, struggles to find his place in a world where people often prove more wicked than beasts.", 9.3f),
        Film("Harry Potter and the Sorcerer's Stone", R.drawable.harry_potter, "An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world.", 9.7f),
        Film("Home Alone", R.drawable.home_alone, "An eight-year-old troublemaker must protect his house from a pair of burglars when he is accidentally left home alone by his family during Christmas vacation.", 9.5f),
        Film("The Gentlemen", R.drawable.the_gentlemen, "An American expat tries to sell off his highly profitable marijuana empire in London, triggering plots, schemes, bribery and blackmail in an attempt to steal his domain out from under him.", 7.8f),
        Film("The Wolf of Wall Street", R.drawable.the_wolf_of_wall_street, "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.", 8.2f),
        Film("Avengers: Endgame", R.drawable.avengers_endgame, "After the devastating events of Мстители: Война бесконечности (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.", 8.1f),
        Film("The Lord of the Rings: The Fellowship of the Ring", R.drawable.the_lord_of_the_rings_the_fellowshipofthe_ring, "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.", 8.9f),
        Film("The Shawshank Redemption", R.drawable.the_shawshank_redemption, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", 7.7f)
    )
}