package com.affey.model.impl;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.affey.model.Theatre;
import com.affey.model.TheatreDAOService;
import com.affey.model.User;
import com.affey.util.AffeyException;

@Repository
public class TheatreDAOServiceImpl implements TheatreDAOService{

	 private SessionFactory sessionFactory;

	  @Autowired
	  public TheatreDAOServiceImpl(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
	  }
	  
	@Override
	@Transactional
	public long createTheatre(Theatre theatre) {
		final Session session = sessionFactory.getCurrentSession();
	    final Theatre dto = new TheatreDTO(theatre);
	    return  (long) session.save(dto);
	}

	@Override
	@Transactional
	public Theatre getTheatre(Long theatreId) {
		 final Session session = sessionFactory.getCurrentSession();
		    final Map<String, Object> map = new HashMap<String, Object>();
		    map.put("theatreId",theatreId );
		    return get(session, map);
	}

	@Override
	@Transactional
	public Theatre getTheatre(String theatreName) {
		 final Session session = sessionFactory.getCurrentSession();
		    final Map<String, Object> map = new HashMap<String, Object>();
		    map.put("theatreName",theatreName );
		    return get(session, map);
	}
	
	protected Theatre get(Session session, Map<String, Object> filters) {
	    final Criteria criteria = session.createCriteria(TheatreDTO.class);
	    for (Map.Entry<String, Object> filter : filters.entrySet()) {
	      criteria.add(Restrictions.eq(filter.getKey(), filter.getValue()));
	    }

	    TheatreDTO dto = (TheatreDTO) criteria.uniqueResult();
	    return dto;
	  }

	  @Override
	  @Transactional
	  public Theatre updateTheatre(Long theatreId, Theatre theatre) {
	    final Session session = sessionFactory.getCurrentSession();
	   // final SeatDTO dtoseat = (SeatDTO) session.get(SeatDTO.class, theatreId);
		  	    final TheatreDTO dto = (TheatreDTO) session.get(TheatreDTO.class, theatreId);
	    if(dto!=null){
	    	dto.setTheatreName(theatre.getTheatreName());
 	    	// No need to save dto as it is persistent Entity and Any change made to such entity is going to be detected and propagated to the database (during the Session flush-time)
	    	//session.save(dto);
	    	return dto;
	    } 
	    throw new AffeyException(
	    		"No theatre found with id:"+ theatreId);
	  }
}
