package com.affey.service.impl;

import com.affey.service.MovieFilters;
import com.affey.service.MovieFilters.MovieFiltersBuilder;

public class MovieFiltersBuilderImpl implements MovieFilters, MovieFiltersBuilder{

	private String movieName;
	private String director;
	private String[] actors;
	
	@Override
	public String getMovieName() {
		return movieName;
	}

	@Override
	public String getMovieDirector() {
		return director;
	}

	@Override
	public String[] getMovieActors() {
		return actors;
	}

	@Override
	public MovieFiltersBuilder setMovieName(String movieName) {
		this.movieName=movieName;
		return this;
	}

	@Override
	public MovieFiltersBuilder setMovieDirector(String directorName) {
		this.director=directorName;
		return this;
	}

	@Override
	public MovieFiltersBuilder setMovieActors(String[] movieActors) {
		this.actors=movieActors;
		return this;
	}

	@Override
	public MovieFilters build() {
		return this;
	}

}
