package codes.zaak.kotlinmvvm.repository

import codes.zaak.kotlinmvvm.repository.db.MovieDao
import codes.zaak.kotlinmvvm.repository.model.Movie
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MovieRepository(val movieDao: MovieDao) {

    fun getMovies(): Observable<List<Movie>> {
        return movieDao.getMovies()
                .filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} movies from DB...")
                }
    }

    fun storeMovies(movies: List<Movie>) {
        Observable.fromCallable { movieDao.insertAll(movies) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.d("Inserted ${movies.size} movies from API in DB...")
                }
    }
}