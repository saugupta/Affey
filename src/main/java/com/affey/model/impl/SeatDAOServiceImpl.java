package com.affey.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.affey.model.Seat;
import com.affey.model.SeatDAOService;
import com.affey.model.Show;
import com.affey.util.AffeyException;

@Repository
public class SeatDAOServiceImpl implements SeatDAOService{
	
	 private final SessionFactory sessionFactory;
	 
	  @Autowired
	  public SeatDAOServiceImpl(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
	  }

	@Override
	@Transactional
	public List<Long> createSeats(Long totalSeats, Show show) {
		List<Long> seats= new ArrayList<Long>();
		final Session session = sessionFactory.getCurrentSession();
		ShowDTO showDTO = (ShowDTO) session.createCriteria(ShowDTO.class).add(Restrictions.eq("showId", show.getShowId())).uniqueResult();
	  for(int i=0;i<totalSeats;i++){
		 final SeatDTO seatDTO = new SeatDTO();
		  seatDTO.setShow(showDTO);
		 seats.add((long) session.save(seatDTO)); 
	  }
		return seats;
	}

	@Override
	@Transactional
	public List<Seat> getAllSeats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean bookTheSeats(Long[] seats, String userName) {
		Session session = sessionFactory.getCurrentSession();
		UserDTO userDTO = (UserDTO) session.get(UserDTO.class, userName);
		 if (userDTO == null) {
		    	throw new AffeyException("No User found with userName:"+ userName);
		    }
		
		for(int i=0;i<seats.length;i++){
			SeatDTO seatDTO= (SeatDTO) session.get(SeatDTO.class, seats[i]);
			if(seatDTO==null)
				throw new AffeyException("No Seat found with id:"+ seats[i]);
			if(seatDTO.getReserved())
				throw new AffeyException("Seat already reserved:"+ seats[i]);
			seatDTO.setReserved(true);
			seatDTO.setUser(userDTO);
		}
		return true;
	}
}