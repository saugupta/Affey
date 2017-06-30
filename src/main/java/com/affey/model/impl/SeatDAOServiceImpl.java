package com.affey.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.affey.model.Seat;
import com.affey.model.SeatDAOService;
import com.affey.model.Show;
import com.affey.service.SeatFilters;
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
		Session session = sessionFactory.getCurrentSession();
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

	List<Criterion> getCriteria(SeatFilters seatFilters){
		final List<Criterion> criteria = new ArrayList<Criterion>();
		if(seatFilters.getShowId()!=null)
			criteria.add(Restrictions.eq("sw.showId", seatFilters.getShowId()));
		if(seatFilters.getTheatreId()!=null)
			criteria.add(Restrictions.eq("tr.theatreId",seatFilters.getTheatreId()));
		if(seatFilters.getUser_name()!=null)
			criteria.add(Restrictions.eq("ur.userName",seatFilters.getUser_name()));
		if(seatFilters.isReserved()!=null)
			criteria.add(Restrictions.eq("reserved",seatFilters.isReserved()));	
		return criteria;
	}
	@Override
	@Transactional
	public List<Seat> listSeats(SeatFilters seatFilters) {
		 final Session session = sessionFactory.getCurrentSession();
		 return list(session, getCriteria(seatFilters));
	}
	@SuppressWarnings("unchecked")
	  protected List<Seat> list(Session session, List<Criterion> filters) {
		final Criteria criteria = session.createCriteria(SeatDTO.class)
	       .createAlias("show", "sw")
		       .createAlias("user", "ur",JoinType.LEFT_OUTER_JOIN)
	        .createAlias("show.theatre", "tr");
	    for (Criterion criterion : filters) {
	      criteria.add(criterion);
	    }
	    final List<Seat> list = criteria
        .addOrder(Order.asc("seatId"))
       // .setResultTransformer(Transformers.aliasToBean(SeatDTO.class))
	        .list();

	    return list;
	  }

	@Override
	@Transactional
	public List<? extends Seat> unReserveSeats(SeatFilters seatFilters) {
		 final Session session = sessionFactory.getCurrentSession();
		 final Criteria criteria = session.createCriteria(SeatDTO.class)
			       .createAlias("show", "sw")
				       .createAlias("user", "ur",JoinType.LEFT_OUTER_JOIN)
			        .createAlias("show.theatre", "tr");
			    for (Criterion criterion : getCriteria(seatFilters)) {
			      criteria.add(criterion);
			    }
			  @SuppressWarnings("unchecked")
			final List<SeatDTO> seats = criteria
					  						.addOrder(Order.asc("seatId"))
					  						.list();
		 for(SeatDTO seat: seats){
			 seat.setReserved(false);
			 seat.setUser(null);
		 }
		 return seats;
	//	 return new ArrayList<Seat>(seats);
	}
}