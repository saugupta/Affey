package com.affey.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.affey.model.Movie;
import com.affey.model.Theatre;
import com.affey.model.TheatreDAOService;
import com.affey.rest.pojo.TheatrePojo;
import com.affey.service.TheatreService;
import com.affey.util.AffeyException;
import com.affey.util.Validators;


@Service
public class TheatreServiceImpl implements  TheatreService{
	
	@Autowired
	TheatreDAOService theatreDAOService;
	
	protected static final Logger LOGGER = LoggerFactory
		      .getLogger(TheatreServiceImpl.class);

	@Override
	public Theatre createTheatre(Theatre theatre) {
		validateTheatre(theatre);
		long theatreId= theatreDAOService.createTheatre(theatre);
		return theatreDAOService.getTheatre(theatreId);
	}

	@Override
	public Theatre getTheatre(Long theatreId) {
		return theatreDAOService.getTheatre(theatreId);
	}
	void validateTheatre(Theatre theatre){
//		 if (!Validators.patternValidator(theatre.getTheatreName(), "[A-Za-z0-9 ]+")) {
//		      throw new AffeyException("Movie name should contain a-z,0-9 and Space only.");
//		    }
	}

	@Override
	public Theatre updateTheatre(Long theatreId, Theatre theatre) {
		 theatreDAOService.updateTheatre(theatreId, theatre);
		 return theatreDAOService.getTheatre(theatreId);
	}
}
