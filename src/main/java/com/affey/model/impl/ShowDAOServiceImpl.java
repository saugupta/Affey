package com.affey.model.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.affey.model.Seat;
import com.affey.model.Show;
import com.affey.model.ShowDAOService;
import com.affey.model.Theatre;
import com.affey.rest.pojo.ShowReturnPojo;
import com.affey.util.AffeyException;

@Repository
public class ShowDAOServiceImpl implements ShowDAOService{

	 private final SessionFactory sessionFactory;

	  @Autowired
	  public ShowDAOServiceImpl(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
	  }
	 
	@Override
	@Transactional 
	public Show getShow(Long showId) {
		 final Session session = sessionFactory.getCurrentSession();
		    final Map<String, Object> map = new HashMap<String, Object>();
		    map.put("showId",showId );
		    return get(session, map);
	}

	@Override
	@Transactional
	public List<Long> listConflictingShows(Show show, long theatreId) {
		 final Session session = sessionFactory.getCurrentSession();
		 final TheatreDTO theatreDTO = (TheatreDTO) session.get(TheatreDTO.class, theatreId);
		    			if(theatreDTO==null){
					      throw new AffeyException(
					        "No theatre Exists with given id:"+ theatreId);
					    }
		 final Criteria criteria = session.createCriteria(ShowDTO.class);
		 Criterion c1 = null, c2 = null;
		 if (show.getStartTime() != null) {
		      c1= Restrictions.gt("startTime",show.getStartTime());
		    }
		 if (show.getEndTime() != null) {
		      c2= Restrictions.lt("startTime",show.getEndTime());
		    }
		 Criterion c3 = null, c4 = null;
		 if (show.getEndTime() != null) {
		      c3= Restrictions.lt("endTime",show.getEndTime());
		    }
		 if (show.getEndTime() != null) {
		      c4= Restrictions.gt("endTime",show.getStartTime());
		    } 
		 criteria.add(Restrictions.or(Restrictions.and(c1,c2),Restrictions.and(c3,c4)));
		 criteria.add(Restrictions.eq("theatre",theatreDTO));
		 return (List<Long>) criteria.setProjection(Projections.property("showId")).list();
	}
	
	@Override
	@Transactional 
	public ShowReturnPojo createShowAndSeats(Show show,Long theatreId, Long movieId) {
		final Session session = sessionFactory.getCurrentSession();
		TheatreDTO theatreDTO = (TheatreDTO) session.createCriteria(TheatreDTO.class).add(Restrictions.eq("theatreId", theatreId)).uniqueResult();
	    if (theatreDTO == null) {
	    	throw new AffeyException("No Theatre found with id:"+ theatreId);
	    }
	    MovieDTO movieDTO = (MovieDTO) session.createCriteria(MovieDTO.class).add(Restrictions.eq("movieId", movieId)).uniqueResult();
	    if (movieDTO == null) {
	    	throw new AffeyException("No Movie found with id:"+ movieId);
	    }
		final ShowDTO showDTO= new ShowDTO(show);
		showDTO.setTheatre(theatreDTO);
		showDTO.setMovie(movieDTO);
		session.save(showDTO);
		 // Now add seats for the show
		List<Long> seats= new ArrayList<Long>();
		for(int i=0;i<show.getTotalSeats();i++){
		  final SeatDTO seatDTO = new SeatDTO();
		  seatDTO.setShow(showDTO);
		  Long seatId=(long)session.save(seatDTO);
		  seats.add(seatId); 
		}
		ShowReturnPojo showReturnPojo= new ShowReturnPojo(showDTO);
		showReturnPojo.setSeats(seats);
		return showReturnPojo;	
	}
	
	@Override
	@Transactional 
	public Long createShow(Show show,Long theatreId, Long movieId) {
		final Session session = sessionFactory.getCurrentSession();
		TheatreDTO theatreDTO = (TheatreDTO) session.createCriteria(TheatreDTO.class).add(Restrictions.eq("theatreId", theatreId)).uniqueResult();
	    if (theatreDTO == null) {
	    	throw new AffeyException("No Theatre found with id:"+ theatreId);
	    }
	    MovieDTO movieDTO = (MovieDTO) session.createCriteria(MovieDTO.class).add(Restrictions.eq("movieId", movieId)).uniqueResult();
	    if (movieDTO == null) {
	    	throw new AffeyException("No Movie found with id:"+ movieId);
	    }
	final ShowDTO showDTO= new ShowDTO(show);
	showDTO.setTheatre(theatreDTO);
	showDTO.setMovie(movieDTO);
	 return  (long) session.save(showDTO);
	}

 
	protected Show get(Session session, Map<String, Object> filters) {
	    final Criteria criteria = session.createCriteria(ShowDTO.class);
	    for (Map.Entry<String, Object> filter : filters.entrySet()) {
	      criteria.add(Restrictions.eq(filter.getKey(), filter.getValue()));
	    }

	    ShowDTO dto = (ShowDTO) criteria.uniqueResult();
	    return dto;
	  }

	@Override
	public List<Show> list(Date startTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seat> listSeats(Show show) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Show> getAllShows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Show> getShowsForTheatre(Theatre theatre) {
		// TODO Auto-generated method stub
		return null;
	}


}
