package com.affey.model.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.affey.model.User;
import com.affey.model.UserDAOService;
import com.affey.util.AffeyException;

@Repository
public class UserDAOServiceImpl implements UserDAOService{
	
	  private final SessionFactory sessionFactory;

	  @Autowired
	  private PasswordEncoder encoder;
	  
	  private static final String CUSTOMER_ROLE = "CUSTOMER";

	  private static final String ADMIN_ROLE = "ADMIN";
	  
	  @Autowired
	  public UserDAOServiceImpl(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
	  }

	  @Override
	  @Transactional
	  public String create(User user) {
	    final Session session = sessionFactory.getCurrentSession();
	    final UserDTO dto = new UserDTO(user.getUserName(),encoder.encode(user.getPassword()), user.getEmailId());
	    final String response = (String) session.save(dto);
	    final UserRoleDTO trDto = new UserRoleDTO(user.getUserName(), CUSTOMER_ROLE);
	    session.save(trDto);
	    return response;
	  }

	  @Override
	  @Transactional
	  public User get(String userName) {
	    final Session session = sessionFactory.getCurrentSession();
	    return (User) session.get(UserDTO.class, userName);
	  }

	  @Override
	  @Transactional
	  public List<User> list() {
	    final Session session = sessionFactory.getCurrentSession();
	    return list(session, Collections.<String, Object> emptyMap());
	  }

	  @Override
	  @Transactional
	  public User update(String userName, User user) {
	    final Session session = sessionFactory.getCurrentSession();
	    final UserDTO dto = (UserDTO) session.get(UserDTO.class, userName);
	    if(dto!=null){
		    if (StringUtils.isNotBlank(user.getPassword())) {
		    	dto.setPassword(encoder.encode(user.getPassword()));
		    }
		    if (StringUtils.isNotBlank(user.getEmailId())) {
		    	dto.setEmailId(user.getEmailId());
		    }
		    // No need to save/ update dto as it is Persistent entity
		    // Any change made to such entity is going to be detected and propagated to the database (during the Session flush-time)
		    // session.save(dto);
		     return dto;
	    }
	    	throw new AffeyException(
			    		"No user found with userName:"+ userName);
	    }
	  
	  @Override
	  @Transactional
	  public User setEnabled(String userName, boolean enabled) {
		  final Session session = sessionFactory.getCurrentSession();
		    final UserDTO dto = (UserDTO) session.get(UserDTO.class, userName);
		    final List<UserRoleDTO> userRoles = getUserRoles(session, userName);
		      if (isAdminUser(userRoles)) {
		        throw new AffeyException(
		            "Status of a user with admin privelege cannot be enabled/disabled");
		      }
		    dto.setEnabled(enabled);
		    session.save(dto);
		    return dto;
	  }

	  private boolean isAdminUser(final List<UserRoleDTO> userRoles) {
	    for (UserRoleDTO userRole : userRoles) {
	      if (userRole.getRoleName().equals(ADMIN_ROLE)) {
	        return true;
	      }
	    }
	    return false;
	  }

	  @Override
	  @Transactional
	  public boolean delete(String userName) {
	    final Session session = sessionFactory.getCurrentSession();
	    final User user = (User) session.get(UserDTO.class, userName);
	    if (user != null) {
	      final List<UserRoleDTO> userRoles = getUserRoles(session, userName);
	      if (isAdminUser(userRoles)) {
	        throw new AffeyException(
	            "Admin user can not be deleted.");
	      }

	      session.delete(user);
	      for (UserRoleDTO userRole : userRoles) {
	        session.delete(userRole);
	      }
	      
	      return true;
	    }
	    throw new AffeyException(
	    		"No user found with userName:"+ userName);
	  }

	  protected User get(Session session, Map<String, Object> filters) {
	    final Criteria criteria = session.createCriteria(UserDTO.class);
	    for (Map.Entry<String, Object> filter : filters.entrySet()) {
	      criteria.add(Restrictions.eq(filter.getKey(), filter.getValue()));
	    }

	    return (UserDTO) criteria.uniqueResult();
	  }

	  @SuppressWarnings("unchecked")
	  protected List<User> list(Session session, Map<String, Object> filters) {
	    final Criteria criteria = session.createCriteria(UserDTO.class);
	    for (Map.Entry<String, Object> filter : filters.entrySet()) {
	      criteria.add(Restrictions.eq(filter.getKey(), filter.getValue()));
	    }

	    return criteria.addOrder(Order.asc("userName"))
	        .setProjection(getDTOProjections())
	        .setResultTransformer(Transformers.aliasToBean(UserDTO.class)).list();
	  }

	  protected ProjectionList getDTOProjections() {
	    return Projections.projectionList()
	        .add(Projections.property("userName"), "userName")
	        .add(Projections.property("enabled"), "enabled")
	    	.add(Projections.property("emailId"),"emailId");
	  }

	  @SuppressWarnings("unchecked")
	  private List<UserRoleDTO> getUserRoles(Session session, String userName) {
	    final Criteria criteria = session.createCriteria(UserRoleDTO.class);
	    criteria.add(Restrictions.eq("userName", userName));

	    return criteria.setProjection(getUserRoleDTOProjections())
	        .setResultTransformer(Transformers.aliasToBean(UserRoleDTO.class)).list();
	  }

	  protected ProjectionList getUserRoleDTOProjections() {
	    return Projections.projectionList()
	        .add(Projections.property("rowId"), "rowId")
	        .add(Projections.property("userName"), "userName")
	        .add(Projections.property("roleName"), "roleName");
	  }
}