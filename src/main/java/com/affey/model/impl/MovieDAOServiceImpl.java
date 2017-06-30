package com.affey.model.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import com.affey.model.Movie;
import com.affey.model.MovieDAOService;
import com.affey.service.MovieFilters;

@Repository
public class MovieDAOServiceImpl implements MovieDAOService{

	 private SessionFactory sessionFactory;

	  @Autowired
	  public MovieDAOServiceImpl(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
	  }

	@Override
	@Transactional
	public Long addMovie(Movie movie) {
		final Session session = sessionFactory.getCurrentSession();
	    final Movie dto = new MovieDTO(movie);
	    return  (long) session.save(dto);
	}

	@Override
	@Transactional
	public Movie getMovie(Long movieId) {
		 final Session session = sessionFactory.getCurrentSession();
		    final Map<String, Object> map = new HashMap<String, Object>();
		    map.put("movieId",movieId );
		    return get(session, map);
	}
	protected Movie get(Session session, Map<String, Object> filters) {
	    final Criteria criteria = session.createCriteria(MovieDTO.class);
	    for (Map.Entry<String, Object> filter : filters.entrySet()) {
	      criteria.add(Restrictions.eq(filter.getKey(), filter.getValue()));
	    }

	    MovieDTO dto = (MovieDTO) criteria.uniqueResult();
	    return dto;
	  }

	@Override
	@Transactional
	public Movie getMovie(String movieName) {
		 final Session session = sessionFactory.getCurrentSession();
		    final Map<String, Object> map = new HashMap<String, Object>();
		    map.put("movieName",movieName );
		    return get(session, map);
	}

	@Override
	@Transactional
	public List<Movie> listMovies(MovieFilters movieFilters) {
		 final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria =session.createCriteria(MovieDTO.class);
		if(movieFilters.getMovieDirector()!=null)
			criteria.add(Restrictions.eq("director", movieFilters.getMovieDirector()));
		if(movieFilters.getMovieName()!=null)
			criteria.add(Restrictions.eq("movieName",movieFilters.getMovieName()));
		final List<Movie> list = criteria.addOrder(Order.asc("movieId")).list();
		return list;
	}
}
