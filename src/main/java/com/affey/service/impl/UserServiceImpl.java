package com.affey.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.affey.ConfigService;
import com.affey.model.User;
import com.affey.model.UserDAOService;
import com.affey.service.UserService;
import com.affey.util.AffeyException;

@Service
public class UserServiceImpl implements UserService{
	  
	  @Autowired
	  private UserDAOService userDAOService;
	 
	  @Autowired
	  private ConfigService configService;

	  @Override
	  public User create(User user) {
		  if(userDAOService.get(user.getUserName())!=null){
		      throw new AffeyException(
		        "A user with same name already registered. Please try a different name");
		    }
		  validateUser(user);	  
	    userDAOService.create(user);
	    return userDAOService.get(user.getUserName());
	  }
	  void validateUser(User user){
//		    if (!Validators.patternValidator(user.getUserName(), "[a-z0-9-]+")) {
//	      throw new RuntimeException(
//	          "User name should contain a-z,0-9 and hyphen only.");
//	    }
//	    /* Match the password using following rules:
//	     *  At least one upper case English letter, (?=.*?[A-Z])
//		 *	At least one lower case English letter, (?=.*?[a-z])
//	 	 *	At least one digit, (?=.*?[0-9])
//		 *	At least one special character, (?=.*?[#?!@$%^&*-])	
//		 *	Minimum eight in length .{8,} (with the anchors)
//	     */
//	    if (!Validators.patternValidator(user.getPassword(), "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")) {
//		      throw new RuntimeException(
//		          "Password should contain a-z,0-9 and hyphen only.");
//		    }
	
	  }
	  @Override
	  public User get(String userName) {
	    return userDAOService.get(userName);
	  }

	  @Override
	  public List<User> list() {
	    return userDAOService.list();
	  }

	  @Override
	  public User update(String userName, User user) {
	     userDAOService.update(userName, user);
	     return userDAOService.get(userName);
	  }

	  @Override
	  public boolean delete(String userName) {
	      return userDAOService.delete(userName);
	  }

	  @Override
	  public User setEnabled(String userName, boolean enabled) {
	    return userDAOService.setEnabled(userName, enabled);
	  }
}
