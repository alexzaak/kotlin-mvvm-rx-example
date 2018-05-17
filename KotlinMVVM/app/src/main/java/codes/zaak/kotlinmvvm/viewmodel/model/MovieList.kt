package codes.zaak.kotlinmvvm.viewmodel.model

import codes.zaak.kotlinmvvm.repository.model.Movie

data class MovieList(val movies: List<Movie>, val message: String, val error:Throwable? = null)