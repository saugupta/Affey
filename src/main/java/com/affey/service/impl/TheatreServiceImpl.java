package com.affey.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.affey.model.Point;
import com.affey.model.Seat;
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
	
	public boolean bookTheSeats(int theatreId, Point[] locations, int customerId) {
		Arrays.sort(locations);
		Theatre theatre= theatres.get(theatreId);
		boolean bookingPossible=true;
		List<Seat> bookedSeats= new ArrayList<Seat>();
		for(int i=0;i<locations.length;i++){
			Seat seat= theatre.getSeat(locations[i]);
			if(seat.isReserved()){
				bookingPossible=false;
				break;
			}
			synchronized(seat){
				if(seat.isReserved()){
					bookingPossible=false;
					break;
				}
				seat.setReserved(true);
				seat.setCustomer(customerId);
				bookedSeats.add(seat);
				LOGGER.info(seat+" booked.");
			}
		}
		if(!bookingPossible){
			for(Seat seat: bookedSeats){
				seat.setReserved(false);
				LOGGER.info(seat+" unbooked due to non atomic complete transaction.");
			}
			return false;
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
