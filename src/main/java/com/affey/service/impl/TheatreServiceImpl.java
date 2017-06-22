package com.affey.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.affey.model.Seat;
import com.affey.model.Point;
import com.affey.model.Theatre;
import com.affey.service.TheatreService;
import com.affey.util.AffeyException;


@Service
public class TheatreServiceImpl implements  TheatreService{
	protected static final Logger LOGGER = LoggerFactory
		      .getLogger(TheatreServiceImpl.class);
	private Map<Integer ,Theatre> theatres;
	
	@PostConstruct
	  public void init() { 
		Seat seat[][] = new Seat[3][3];
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				seat[i][j]= new Seat(i,j);
		Theatre tOne= new Theatre(1, 3, seat);
		theatres= new HashMap<>();
		theatres.put(1, tOne);
	}
	
	public boolean bookTheSeat(int theatreId, Point location, int customerId) {
		
		Theatre theatre= theatres.get(theatreId);
		Seat seat= theatre.getSeat(location);
		if(seat.isReserved())
			return false;
		synchronized(seat){
			if(seat.isReserved())
				return false;
			seat.setReserved(true);
			seat.setCustomer(customerId);
			LOGGER.info(seat+" booked.");
		}
		return true;
	}
	
	public boolean doTheatreExist(int id){
		return (theatres.containsKey(id));
	}
	
	public boolean isSeatValid(int id,Point point ){
		if(!doTheatreExist(id))
			throw new AffeyException("Theatre "+id +" does not exist.");
		int size=theatres.get(id).getSize();
		if(point.getX()>size|| point.getX()<0|| point.getY()<0|| point.getY()>size)
			return false;
		return true;
		
	}
}
