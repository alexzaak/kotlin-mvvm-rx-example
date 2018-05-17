package codes.zaak.kotlinmvvm

import android.app.Application
import android.arch.persistence.room.Room
import codes.zaak.kotlinmvvm.repository.MovieRepository
import codes.zaak.kotlinmvvm.repository.db.AppDatabase
import codes.zaak.kotlinmvvm.repository.model.Movie
import codes.zaak.kotlinmvvm.viewmodel.MovieListViewModel
import timber.log.Timber

class App : Application() {

    companion object {
        private lateinit var movieRepository: MovieRepository
        private lateinit var movieListViewModel: MovieListViewModel
        private lateinit var appDatabase: AppDatabase

        fun injectMovieListViewModel() = movieListViewModel
    }

    private var mMoviesList = listOf(Movie(title = "Iron Man", year = 2008, rating = 9),
            Movie(title = "The Incredible Hulk", year = 2008, rating = 6),
            Movie(title = "Iron Man 2", year = 2010, rating = 7),
            Movie(title = "Thor", year = 2011, rating = 2),
            Movie(title = "Captain America: The First Avenger", year = 2011, rating = 5),
            Movie(title = "Marvel's The Avengers", year = 2012, rating = 4),
            Movie(title = "Iron Man 3", year = 2013, rating = 5),
            Movie(title = "Thor: The Dark World", year = 2013, rating = 6),
            Movie(title = "Captain America: The Winter Soldier", year = 2014, rating = 7),
            Movie(title = "Guardians of the Galaxy", year = 2014, rating = 10),
            Movie(title = "Avengers: Age of Ultron", year = 2015, rating = 6),
            Movie(title = "Ant-Man", year = 2015, rating = 1),
            Movie(title = "Captain America: Civil War", year = 2016, rating = 4),
            Movie(title = "Doctor Strange", year = 2016, rating = 7),
            Movie(title = "Guardians of the Galaxy Vol. 2", year = 2016, rating = 8),
            Movie(title = "Spider-Man: Homecoming", year = 2017, rating = 4),
            Movie(title = "Thor: Ragnarok", year = 2017, rating = 7),
            Movie(title = "Black Panther", year = 2018, rating = 3),
            Movie(title = "Avengers: Infinity War", year = 2018, rating = 8))

    override fun onCreate() {
        super.onCreate()

        Timber.uprootAll()
        Timber.plant(Timber.DebugTree())

        appDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "movie-mvvm-db").build()

        movieRepository = MovieRepository(appDatabase.movieDao())
        movieRepository.storeMovies(mMoviesList)
        movieListViewModel = MovieListViewModel(movieRepository)
    }
}