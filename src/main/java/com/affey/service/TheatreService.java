package com.affey.service;

import com.affey.model.Point;
import com.affey.model.Theatre;
import com.affey.rest.pojo.TheatrePojo;

public interface TheatreService {
	
	public Theatre createTheatre(Theatre theatre);
	public Theatre getTheatre(Long theatreId);
	public Theatre updateTheatre(Long theatreId, Theatre theatre);
}
