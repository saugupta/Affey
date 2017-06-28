package com.affey.model;

import java.util.List;

import com.affey.service.MovieFilters;

public interface MovieDAOService {

	public Long addMovie(Movie movie);
	public Movie getMovie(Long movieId);
	public Movie getMovie(String movieName);
	public List<Movie> listMovies(MovieFilters movieFilters);
}
