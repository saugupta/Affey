package com.affey.model;

import java.util.Date;
import java.util.List;

import com.affey.rest.pojo.ShowReturnPojo;
import com.affey.service.ShowFilters;

public interface ShowDAOService {

	public Long createShow(Show show, Long theatreId, Long movieId);
	
	public Show getShow(Long showId);
	
	public List<Show> getAllShows();
	
	public List<Show> getShowsForTheatre(Theatre theatre);
	
	public List<Show> list(Date startTime);

	public List<Seat> listSeats(Show show);
	
	public List<Long> listConflictingShows(Show show, long theatreId);

	public ShowReturnPojo createShowAndSeats(Show show, Long theatreId, Long movieId);

	public List<Show> listShows(ShowFilters showFilters);
	
}

