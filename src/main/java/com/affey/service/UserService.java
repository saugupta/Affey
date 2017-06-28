package com.affey.service;

import java.util.List;

import com.affey.model.User;

public interface UserService {
	  public User create(User user);

	  public User get(String userName);

	  public List<User> list();

	  public User update(String userName, User user);

	  public boolean delete(String userName);

	  public User setEnabled(String userName,boolean enabled);
}
