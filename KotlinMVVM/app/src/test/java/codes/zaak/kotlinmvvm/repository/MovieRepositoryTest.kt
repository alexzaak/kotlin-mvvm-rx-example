package codes.zaak.kotlinmvvm.repository

import codes.zaak.kotlinmvvm.repository.db.MovieDao
import codes.zaak.kotlinmvvm.repository.model.Movie
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import java.util.*

class MovieRepositoryTest {

    lateinit var movieRepository: MovieRepository
    lateinit var movieDao: MovieDao

    private fun testMovie() = Movie(UUID.randomUUID().toString(),"Bulliwood Bulli Film", 1999, 1)

    @Before
    fun setup() {
        movieDao = mock()
        `when`(movieDao.getMovies()).thenReturn(Single.just(emptyList()))
        movieRepository = MovieRepository(movieDao)
    }

    @Test
    fun testEmptyDatabase() {
        `when`(movieDao.getMovies()).thenReturn(Single.just(emptyList()))

        movieRepository.getMovies().test()
                .assertValueCount(0)
    }

    @Test
    fun testMovieInDatabase() {
        val testMovieList = listOf(testMovie())

        `when`(movieDao.getMovies()).thenReturn(Single.just(testMovieList))

        movieRepository.getMovies().test()
                .assertValueCount(1)
                .assertValue { it.size == 1 }
                .assertValue(testMovieList)
    }

    @Test
    fun testMultipleMoviesInDatabase() {
        val testMovieList = listOf(testMovie(), testMovie(), testMovie())

        `when`(movieDao.getMovies()).thenReturn(Single.just(testMovieList))

        movieRepository.getMovies().test()
                .assertValueCount(1)
                .assertValue { it.size == 3 }
                .assertValue(testMovieList)
    }
}