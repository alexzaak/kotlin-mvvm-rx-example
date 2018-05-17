package codes.zaak.kotlinmvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import codes.zaak.kotlinmvvm.App
import codes.zaak.kotlinmvvm.R
import codes.zaak.kotlinmvvm.viewmodel.model.MovieList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.movielist_fragment.*
import timber.log.Timber
import java.net.ConnectException

class MovieListFragment: BaseFragment() {

    val movieListViewModel = App.injectMovieListViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movielist_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        subscribe(movieListViewModel.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("Received Model with ${it.movies.size} movies.")
                    showMovie(it)
                }, {
                    Timber.w(it)
                    showError()
                }))

    }

    fun showMovie(data: MovieList) {
        if (data.error == null) {
            list.adapter = CustomAdapter(context, R.layout.list_item, data.movies)
        } else if (data.error is ConnectException) {
            Timber.d("Error on loading movies.")
        } else {
            showError()
        }
    }

    fun showError() {
        Toast.makeText(context, "An error occurred :(", Toast.LENGTH_SHORT).show()
    }
}