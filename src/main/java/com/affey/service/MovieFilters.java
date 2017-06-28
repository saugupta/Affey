package com.affey.service;

public interface MovieFilters {

	public String getMovieName();
	public String getMovieDirector();
	public String[] getMovieActors();
	
	public static interface MovieFiltersBuilder{
		
		public MovieFiltersBuilder setMovieName(String movieName);
		public MovieFiltersBuilder setMovieDirector(String directorName);
		public MovieFiltersBuilder  setMovieActors(String[] movieActors);
		public MovieFilters build();
	}
}
