package com.affey.service;

import java.util.List;

import com.affey.model.Movie;
import com.affey.service.MovieFilters.MovieFiltersBuilder;

public interface MovieService {
	public Movie addMovie(Movie movie);
	public Movie getMovie(Long movieId);
	public MovieFiltersBuilder newFiltersBuilder();
	public List<Movie> listMovies(MovieFilters movieFilters);
}
