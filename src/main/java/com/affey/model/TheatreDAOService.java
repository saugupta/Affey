package com.affey.model;


public interface TheatreDAOService {
	
	public long createTheatre(Theatre theatre);
	
	public Theatre getTheatre(Long theatreId);
	public Theatre getTheatre(String theatreName);
	public Theatre updateTheatre(Long theatreId, Theatre theatre);
}
