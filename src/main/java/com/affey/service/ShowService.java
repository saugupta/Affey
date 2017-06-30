package com.affey.service;

import java.util.List;

import com.affey.model.Show;
import com.affey.rest.pojo.ShowReturnPojo;
import com.affey.service.ShowFilters.ShowFiltersBuilder;

public interface ShowService {

	public ShowReturnPojo createShowAndSeats(Show show, Long theatreId, Long movieId);
	
	public Show getShow(Long showId);

	public boolean deleteShow(String movieName , List<String> theatreNames);
	
	public ShowFiltersBuilder newFiltersBuilder();
	
	public List<Show> listShows(ShowFilters showFilters);
}
