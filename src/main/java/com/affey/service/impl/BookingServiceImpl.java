package com.affey.service.impl;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.affey.model.Point;
import com.affey.model.SeatDAOService;
import com.affey.model.Theatre;
import com.affey.service.BookingService;

/**
 * @author saugupta
 *
 */
@Service
public class BookingServiceImpl implements BookingService{// implements BookingService{

private Map<Integer ,Theatre> theatres;
//	

	@Autowired
	SeatDAOService seatDAOService;
	
	@PostConstruct
	  public void init() { 
//		Seat seat[][] = new Seat[3][3];
//		for(int i=0;i<3;i++)
//			for(int j=0;j<3;j++)
//				seat[i][j]= new Seat(i,j);
//		Theatre tOne= new Theatre(1, 3, seat);
//		theatres= new HashMap<>();
//		theatres.put(1, tOne);
	}
//	
	public boolean bookTheSeats(int theatreId, Point[] locations, int customerId) {
//		Arrays.sort(locations);
//		Theatre theatre= theatres.get(theatreId);
//		boolean bookingPossible=true;
//		List<Seat> bookedSeats= new ArrayList<Seat>();
//		for(int i=0;i<locations.length;i++){
//			Seat seat= theatre.getSeat(locations[i]);
//			if(seat.isReserved()){ 
//				bookingPossible=false;
//				break;
//			}
//			synchronized(seat){
//				if(seat.isReserved()){
//					bookingPossible=false;
//					break;
//				}
//				seat.setReserved(true);
//				seat.setCustomer(customerId);
//				bookedSeats.add(seat);
//				LOGGER.info(seat+" booked.");
//			}
//		}
//		if(!bookingPossible){
//			for(Seat seat: bookedSeats){
//				seat.setReserved(false);
//				LOGGER.info(seat+" unbooked due to non atomic complete transaction.");
//			}
//			return false;
//		}
//		return true;
		return true;
	}
//	
	public boolean doTheatreExist(int id){
//		return (theatres.containsKey(id));
		return true;
	}
//	
	public boolean isSeatValid(int id,Point point ){
//		if(!doTheatreExist(id))
//			throw new AffeyException("Theatre "+id +" does not exist.");
//		int size=theatres.get(id).getSize();
//		if(point.getX()>size|| point.getX()<0|| point.getY()<0|| point.getY()>size)
//			return false;
//		return true;
//	
		return true;
	}
	@Override
	public boolean bookTheSeats(Long[] seats, String userName) {
		return seatDAOService.bookTheSeats(seats,userName);
		
	}
	@Override
	public boolean isSeatFree(long id) {
		// TODO Auto-generated method stub
		return false;
	}
}
