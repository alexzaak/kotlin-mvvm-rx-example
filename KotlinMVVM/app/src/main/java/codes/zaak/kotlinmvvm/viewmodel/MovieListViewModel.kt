package codes.zaak.kotlinmvvm.viewmodel

import codes.zaak.kotlinmvvm.repository.MovieRepository
import codes.zaak.kotlinmvvm.viewmodel.model.MovieList
import io.reactivex.Observable
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MovieListViewModel(val randomUserRepo: MovieRepository) {
    fun getMovies(): Observable<MovieList> {

        return randomUserRepo.getMovies()
                .debounce(400, TimeUnit.MILLISECONDS)
                .map {
                    Timber.d("Mapping movies to Model...")
                    MovieList(it.take(20), "Top 20 Movies")
                }
                .onErrorReturn {
                    MovieList(emptyList(), "An error occurred", it)
                }
    }
}