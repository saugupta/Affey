package com.affey.service;

import java.util.List;

import com.affey.model.Show;
import com.affey.rest.pojo.ShowReturnPojo;

public interface ShowService {

	public ShowReturnPojo createShowAndSeats(Show show, Long theatreId, Long movieId);
	
	public Show getShow(Long showId);

	public boolean deleteShow(String movieName , List<String> theatreNames);
	
}
