package com.affey.model;

import java.util.List;

public interface UserDAOService {

	public String create(User user);

	  public User get(String userName);

	  public List<User> list();

	  public User update(String userName,User user);

	  public boolean delete(String  userName);

	  public User setEnabled(String userName, boolean enabled);

	  
}
