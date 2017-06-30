package com.affey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.affey.model.SeatDAOService;
import com.affey.model.Show;
import com.affey.model.ShowDAOService;
import com.affey.rest.pojo.ShowReturnPojo;
import com.affey.service.ShowFilters;
import com.affey.service.ShowFilters.ShowFiltersBuilder;
import com.affey.service.ShowService;
import com.affey.service.TheatreService;
import com.affey.util.AffeyException;

@Service
public class ShowServiceImpl implements ShowService {

	@Autowired
	TheatreService theatreService;

	@Autowired
	ShowDAOService showDAOService;

	@Autowired
	SeatDAOService seatDAOService;
	
	@Override
	public ShowReturnPojo createShowAndSeats(Show show, Long theatreId, Long movieId) {
		//check if the time does not clashes with the other shows in the theatre
	
		validateTimeConflictswithOtherShows(show, theatreId);

		return showDAOService.createShowAndSeats(show, theatreId,movieId);
	}

	@Override
	public boolean deleteShow(String movieName, List<String> theatreNames) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Show getShow(Long showId) {
		return showDAOService.getShow(showId);
	}

	void validateTimeConflictswithOtherShows(Show show, Long theatreId){
		List<Long> shows = showDAOService.listConflictingShows(show, theatreId);
		if(shows.size()!=0)
			 throw new AffeyException(
				        "Conflicting Shows exists at the time of this show.Please select another time window.");
	}
	void validateShow(Show show){
	//	 To validate start time and end time of the show does not clashes with the other shows
//		 if (!Validators.patternValidator(show.getStartTime(), "[a-z0-9 ]+")) {
//		      throw new AffeyException("Movie name should contain a-z,0-9 and Space only.");
//		    }
		  
		   
	}

	@Override
	public ShowFiltersBuilder newFiltersBuilder() {
		return new ShowFiltersBuilderImpl();
	}

	@Override
	public List<Show> listShows(ShowFilters showFilters) {
		return showDAOService.listShows(showFilters);
	}
}
